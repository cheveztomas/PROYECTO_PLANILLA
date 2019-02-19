/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author tomas
 */
public class ClsInformacionAcademica {

    //Atributos
    private int vgn_idInformacionA, vgn_idEmpleado;
    private String vgc_Grado, vgc_especialidad, vgc_informacion;
    private boolean vgb_borradoLogico;

    //Constructores
    public ClsInformacionAcademica(int vgn_idInformacionA, int vgn_idEmpleado, String vgc_Grado, String vgc_especialidad, String vgc_informacion, boolean vgb_borradoLogico) {
        this.vgn_idInformacionA = vgn_idInformacionA;
        this.vgn_idEmpleado = vgn_idEmpleado;
        this.vgc_Grado = vgc_Grado;
        this.vgc_especialidad = vgc_especialidad;
        this.vgc_informacion = vgc_informacion;
        this.vgb_borradoLogico = vgb_borradoLogico;
    }

    public ClsInformacionAcademica() {
        vgb_borradoLogico = false;
        vgc_Grado = "";
        vgc_especialidad = "";
        vgc_informacion = "";
        vgn_idEmpleado = 0;
        vgn_idInformacionA = 0;
    }
    
    //Propiedades

    public int getVgn_idInformacionA() {
        return vgn_idInformacionA;
    }

    public void setVgn_idInformacionA(int vgn_idInformacionA) {
        this.vgn_idInformacionA = vgn_idInformacionA;
    }

    public int getVgn_idEmpleado() {
        return vgn_idEmpleado;
    }

    public void setVgn_idEmpleado(int vgn_idEmpleado) {
        this.vgn_idEmpleado = vgn_idEmpleado;
    }

    public String getVgc_Grado() {
        return vgc_Grado;
    }

    public void setVgc_Grado(String vgc_Grado) {
        this.vgc_Grado = vgc_Grado;
    }

    public String getVgc_especialidad() {
        return vgc_especialidad;
    }

    public void setVgc_especialidad(String vgc_especialidad) {
        this.vgc_especialidad = vgc_especialidad;
    }

    public String getVgc_informacion() {
        return vgc_informacion;
    }

    public void setVgc_informacion(String vgc_informacion) {
        this.vgc_informacion = vgc_informacion;
    }

    public boolean isVgb_borradoLogico() {
        return vgb_borradoLogico;
    }

    public void setVgb_borradoLogico(boolean vgb_borradoLogico) {
        this.vgb_borradoLogico = vgb_borradoLogico;
    }
    
}
