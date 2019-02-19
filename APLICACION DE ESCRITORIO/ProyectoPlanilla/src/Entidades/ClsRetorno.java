/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Thomas Chevez
 */
public class ClsRetorno {

    //Atributos
    private String vgc_Mensaje;
    private int vgc_ID;

    //Constructor
    public ClsRetorno(String vgc_Mensaje, int vgc_ID) {
        this.vgc_Mensaje = vgc_Mensaje;
        this.vgc_ID = vgc_ID;
    }

    public ClsRetorno() {
        vgc_ID = 0;
        vgc_Mensaje = "";
    }
    
    //Propiedades
    public String getVgc_Mensaje() {
        return vgc_Mensaje;
    }

    public void setVgc_Mensaje(String vgc_Mensaje) {
        this.vgc_Mensaje = vgc_Mensaje;
    }

    public int getVgc_ID() {
        return vgc_ID;
    }

    public void setVgc_ID(int vgc_ID) {
        this.vgc_ID = vgc_ID;
    }
    
}
