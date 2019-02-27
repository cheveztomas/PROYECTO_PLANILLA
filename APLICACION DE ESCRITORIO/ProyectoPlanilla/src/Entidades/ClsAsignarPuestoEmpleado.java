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
public class ClsAsignarPuestoEmpleado {

    //Atributos
    private int vgn_idEmpleadoPuesto, vgn_idEmpleado, vgn_idPuesto, vgn_idInformacion;

    //Constructores
    public ClsAsignarPuestoEmpleado(int vgn_idEmpleadoPuesto, int vgn_idEmpleado, int vgn_idPuesto, int vgn_idInformacion) {
        this.vgn_idEmpleadoPuesto = vgn_idEmpleadoPuesto;
        this.vgn_idEmpleado = vgn_idEmpleado;
        this.vgn_idPuesto = vgn_idPuesto;
        this.vgn_idInformacion = vgn_idInformacion;
    }

    public ClsAsignarPuestoEmpleado() {
        vgn_idEmpleado = 0;
        vgn_idEmpleadoPuesto = 0;
        vgn_idInformacion = 0;
        vgn_idPuesto = 0;
    }
    
    //Propiedades

    public int getVgn_idEmpleadoPuesto() {
        return vgn_idEmpleadoPuesto;
    }

    public void setVgn_idEmpleadoPuesto(int vgn_idEmpleadoPuesto) {
        this.vgn_idEmpleadoPuesto = vgn_idEmpleadoPuesto;
    }

    public int getVgn_idEmpleado() {
        return vgn_idEmpleado;
    }

    public void setVgn_idEmpleado(int vgn_idEmpleado) {
        this.vgn_idEmpleado = vgn_idEmpleado;
    }

    public int getVgn_idPuesto() {
        return vgn_idPuesto;
    }

    public void setVgn_idPuesto(int vgn_idPuesto) {
        this.vgn_idPuesto = vgn_idPuesto;
    }

    public int getVgn_idInformacion() {
        return vgn_idInformacion;
    }

    public void setVgn_idInformacion(int vgn_idInformacion) {
        this.vgn_idInformacion = vgn_idInformacion;
    }
    
}
