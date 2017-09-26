
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Santiago
 */
public class GUConnectionHandler extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    int pS = 0;
    GU gui;
    public GUConnectionHandler(Socket aClientSocket,int apS,GU agui) {
        try {
            clientSocket = aClientSocket;
            pS = apS;
            gui = agui;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    } // end Connection

    public void run() {
        try {	                                      // an echo server
            String data = in.readUTF();   // read a line of data from the stream
            System.out.println("RECIBI DE SERVIDOR: " + data);
            if(data.equalsIgnoreCase("ERROR_PARTIDA_NO_EXISTE")){
                JOptionPane optionPane = new JOptionPane("No se ha encontrado una partida con ese ID",JOptionPane.WARNING_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error!");
                dialog.setAlwaysOnTop(true); // to show top of all other application
                dialog.setVisible(true); // to visible the dialog
            }
            else if(data.contains("TABLERO_")){
                String[] datos = data.split("_");
                gui.tablero = datos[1];
                gui.dibujarTablero();
                System.out.println("RECIBI TABLERO"+datos[1]);
                clientSocket.close();
            }
            else if(data.equalsIgnoreCase("TURNO")){
                clientSocket.close();
            }
            if(data.contains("ERROR_")){
                
            }
            if(data.contains("MENSAJE_")){
                
            }
            if(data.equalsIgnoreCase("TURNO")){
                
            }
            else if(data.contains("PARTIDA_")){
                String[] datos = data.split("_");
                gui.idPartida = Integer.parseInt(datos[1]);
                gui.setIdPartida();
                System.out.println("partida "+datos[1]);
                clientSocket.close();
            }

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                System.out.println("CLOSE?");
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("closefail:" + e.getMessage());
                /*close failed*/
            }
        }
    } // end run
} // end class Connection 