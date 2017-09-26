
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Santiago
 */

public class ServerConnectionHandler extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    int pS = 0;
    Server s;

    public ServerConnectionHandler(Socket aClientSocket,int apS,Server aS) {
        try {
            s = aS;
            clientSocket = aClientSocket;
            pS = apS;
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
            System.out.println("RECIBI DEL CLIENTE: " + data);
            //retransmitir(data);
            
            if(data.contains("PEDIR_TABLERO")){
                //REGISTRANDO JUGADOR
                enviarTablero();
            }
            if(data.contains("MOVER_")){
                String[] datos = data.split("_");
                int idJ = Integer.parseInt(datos[1]);
                int poX = Integer.parseInt(datos[2]);
                int poY = Integer.parseInt(datos[3]);
               
                if(poX < 20 && poY < 25){
                    
                        System.out.println("MOVIENDO Jugador: "+idJ+" A ("+poX+","+poY+")");
                         for(Jugador j: s.jugadores){
                          if(j.idJugador==idJ){
                            s.matriz[j.poX][j.poY] = " ";
                            j.poX = poX;
                            j.poY = poY;
                            s.matriz[j.poX][j.poY] = ""+j.idJugador;
                        }
                    }
                    enviarTablero();
                }
                else{
                    
                    enviarDatos(s.jugadores.get(0).pEntrada, "ERROR_CoordenadasFueraDeRango" );
                }
                
                                
                
               
            }
            if(data.contains("PASAR_") || data.contains("PATEAR_")){
                
                String[] datos = data.split("_");
                int idJ = Integer.parseInt(datos[1]);
                int poX = Integer.parseInt(datos[2]);
                int poY = Integer.parseInt(datos[3]);
                String idp = "";
                boolean gol =false;
                String dir = datos[4];
                String lineaPase = s.matriz.toString().substring(idJ, idJ);
                boolean bandera = false;
                if (dir.contains("AB")){
                    
                    for(int i = poX; i < 20; i++){
                        if(s.matriz[i][poY].contains("*")){
                            bandera = true;
                            break;
                        }
                        
                        if(Pattern.matches("[0-9]+",s.matriz[i][poY] )){
                            idp +=s.matriz[i][poY];
                        }
                        if(s.matriz[i][poY-1].contains("|") || s.matriz[i][poY+1].contains("|") && poY-1!=0 && poY+1!=25 ){
                            gol=true;
                        }
                    }
                    if(bandera){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Balón detenido por el obstaculo");
                    }
                    if(gol){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_GOOOOOL");
                    }
                    else{
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Pase hecho con exito al jugador "+idp);
                        enviarDatos(s.jugadores.get(Integer.parseInt(idp)).pEntrada, "MENSAJE_Es tu turno ");
                    }
                    
                    
                }
                if (dir.contains("DER")){
                    
                    for(int i = poY; i < 25; i++){
                        if(s.matriz[poX][i].contains("*")){
                            bandera = true;
                            break;
                        }
                        
                        if(Pattern.matches("[0-9]+",s.matriz[poX][i] )){
                            idp +=s.matriz[poX][i];
                        }
                      if(s.matriz[i][poY-1].contains("|") || s.matriz[i][poY+1].contains("|") && poY-1!=0 && poY+1!=25 ){
                            gol=true;
                        }
                    }
                    if(bandera){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Balón detenido por el obstaculo");
                    }
                    if(gol){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_GOOOOOL");
                    }
                    else{
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Pase hecho con exito al jugador "+idp);
                        enviarDatos(s.jugadores.get(Integer.parseInt(idp)).pEntrada, "MENSAJE_Es tu turno ");
                    }
                    
                }           
                if (dir.contains("DIAG")){
                    
                    for(int i = poX; i < 20; i++){
                        if(s.matriz[i][i].contains("*")){
                            bandera = true;
                            break;
                        }
                        
                        if(Pattern.matches("[0-9]+",s.matriz[i][i] )){
                            idp +=s.matriz[i][i];
                        }
                           if(s.matriz[i][poY-1].contains("|") || s.matriz[i][poY+1].contains("|") && poY-1!=0 && poY+1!=25 ){
                            gol=true;
                        }
                    }
                    if(bandera){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Balón detenido por el obstaculo");
                    }
                    if(gol){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_GOOOOOL");
                    }
                    else{
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Pase hecho con exito al jugador "+idp);
                        enviarDatos(s.jugadores.get(Integer.parseInt(idp)).pEntrada, "MENSAJE_Es tu turno ");
                    }
                    
                    
                }
                if (dir.contains("IZQ")){
                    
                    for(int i = poY; i > 0; i--){
                        if(s.matriz[poX][i].contains("*")){
                            bandera = true;
                            break;
                        }
                        
                        if(Pattern.matches("[0-9]+",s.matriz[poX][i] )){
                            idp +=s.matriz[poX][i];
                        }
                       if(s.matriz[i][poY-1].contains("|") || s.matriz[i][poY+1].contains("|") && poY-1!=0 && poY+1!=25 ){
                            gol=true;
                        }
                    }
                    if(bandera){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Balón detenido por el obstaculo");
                    }
                    if(gol){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_GOOOOOL");
                    }
                    else{
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Pase hecho con exito al jugador "+idp);
                        enviarDatos(s.jugadores.get(Integer.parseInt(idp)).pEntrada, "MENSAJE_Es tu turno ");
                    }
                    
                }
                if (dir.contains("ARR")){
                    
                    for(int i = poX; i > 0; i--){
                        if(s.matriz[i][poY].contains("*")){
                            bandera = true;
                            break;
                        }
                        
                        if(Pattern.matches("[0-9]+",s.matriz[i][poY] )){
                            idp +=s.matriz[i][poY];
                        }
                       if(s.matriz[i][poY-1].contains("|") || s.matriz[i][poY+1].contains("|") && poY-1!=0 && poY+1!=25 ){
                            gol=true;
                        }
                    }
                    if(bandera){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Balón detenido por el obstaculo");
                    }
                    if(gol){
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_GOOOOOL");
                    }
                    else{
                        enviarDatos(s.jugadores.get(0).pEntrada, "MENSAJE_Pase hecho con exito al jugador "+idp);
                        enviarDatos(s.jugadores.get(Integer.parseInt(idp)).pEntrada, "MENSAJE_Es tu turno ");
                    }
                    
                }
                
                enviarTablero();
            }
            if(data.contains("CREAR_PARTIDA_")){
                String[] datos = data.split("_");
                Jugador j = new Jugador(datos[2],datos[3], datos[4]);
                Partida p = new Partida();
                p.id = (int) (Math.random() * 9999) + 1;
                System.out.println("Nuevo Jugador: "+j.toString()+" PARTIDA:"+p.id);
                
                boolean existe = false;
                for(Partida ja: s.partidas){
                    if(ja.id == p.id){
                        existe = true;
                    }
                }
                if(!existe){
                    s.partidas.add(p);
                }
                existe = false;
                for(Jugador ja: s.jugadores){
                    if(ja.idJugador==Integer.parseInt(datos[2])){
                        existe = true;
                    }
                }
                if(!existe){
                    s.jugadores.add(j);
                    s.obstaculizar();
                    enviarPartida(j.pEntrada,p.id);
                }
            }
            if(data.contains("UNIR_PARTIDA_")){
                boolean existe = false;
                String[] datos = data.split("_");
                for(Partida ja: s.partidas){
                    if(ja.id == Integer.parseInt(datos[5])){
                        Jugador j = new Jugador(datos[2],datos[3], datos[4]);
                        System.out.println("Nuevo Jugador: "+j.toString()+" PARTIDA:"+datos[5]);
                        enviarTablero();
                        existe = true;
                    }
                }
                if(!existe){
                    enviarDatos(Integer.parseInt(datos[3]), "ERROR_PARTIDA_NO_EXISTE");
                }
                
                
            }
            out.writeUTF(data);
            clientSocket.close();
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            }catch(IOException e) {/*close failed*/
            }
        }
    } // end run
    public void guardarLista(ArrayList<Jugador> l){
        this.s.jugadores = l;
    }
    public void enviarPartida(int p,int idp){
        enviarDatos(p, "PARTIDA_"+idp);
    }
    public void enviarTablero(){
        String tablero = "TABLERO_";
        for (int i = 0; i < s.matriz.length; i++) {
            for (String item : s.matriz[i]) {
                tablero += " " + item;
            }
            tablero +="\n";
        }
        System.out.println("TABLERO\n"+tablero);

        for(int i =0;i<s.jugadores.size();i++){
            System.out.println("I:_____"+i);
            System.out.println(s.jugadores.get(i).pEntrada);
            enviarDatos(s.jugadores.get(i).pEntrada, tablero);
        }
    }
    public void actualizarTablero(){
        System.out.println("ServerConnectionHandler.actualizarTablero()");
        for(int i =0;i<s.jugadores.size();i++){
            System.out.println("JUGADOR "+s.jugadores.get(i).idJugador);
            Jugador j = s.jugadores.get(i);
            s.matriz[j.poX][j.poY] = ""+j.idJugador;
        }
    }

    public void enviarDatos(int puerto,String datos) {
        System.out.println("ServerConnectionHandler.enviarDatos()");
        Socket s = null;
            try {
                int serverPort = puerto;
                s = new Socket("localhost", serverPort);
                System.out.println("ENVIANDO AL PUERTO "+serverPort);
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                out.writeUTF(datos);      	 // UTF is a string encoding
            } catch (UnknownHostException e) {
                System.out.println("Socket:" + e.getMessage());
            } catch (EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch (IOException e) {
                System.out.println("readline:" + e.getMessage());
            } finally {
                if (s != null) {
                    try {
                        s.close();
                    } catch (IOException e) {
                        System.out.println("close:" + e.getMessage());
                    }
                }
        }
    }
} // end class Connection 