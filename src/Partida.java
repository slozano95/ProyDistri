
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ale
 */
public class Partida {

<<<<<<< Updated upstream
    Partida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

=======
    private List<Equipo> equipos;

    private Date duracion;
    public int id;
>>>>>>> Stashed changes
    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Partida(int id) {
        this.id = id;
        equipos = new ArrayList<Equipo>();
    }
<<<<<<< Updated upstream
    
    private List<Equipo> equipos;

    private int duracion;
    public int id;
    public String[][] matriz = {
            {" ","","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"," "},
            {"|"," "," "," "," "," "," "," "," ","|"," "," "," "," "," ","|"," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",},
            {"|"," "," "," "," "," "," "," "," ","|"," "," "," "," "," ","|"," "," "," "," "," "," "," "," "," "," ",},
            {" ","","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"," "},
        };
    public void obstaculizar(){
        int numero = (int) (Math.random() * 10) + 1;
        System.out.println("OBS:"+numero);
        for (int i = 0; i < numero/2; i++) {
            int coord1 = (int) (Math.random() * 16) + 2;
            int coord2 = (int) (Math.random() * 16) + 2;
            matriz[coord1][coord2] = "*";
            matriz[coord1+1][coord2] = "*";
            matriz[coord1+2][coord2] = "*";
        }
        for (int i = 0; i < numero/2; i++) {
            int coord1 = (int) (Math.random() * 16) + 2;
            int coord2 = (int) (Math.random() * 16) + 2;
            matriz[coord1][coord2] = "*";
            matriz[coord1][coord2+1] = "*";
            matriz[coord1][coord2+2] = "*";

        }
    }

=======

>>>>>>> Stashed changes
}
