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
abstract class ClsDeduccionesPersonales {

    //Atributos
    private int vgn_idEmpleado;
    private double vgn_monto;

    //Constructores
    public ClsDeduccionesPersonales(int vgn_idEmpleado, double vgn_monto) {
        this.vgn_idEmpleado = vgn_idEmpleado;
        this.vgn_monto = vgn_monto;
    }

    public ClsDeduccionesPersonales() {
        vgn_idEmpleado = 0;
        vgn_monto = 0;
    }
    
    //Propiedades

    public int getVgn_idEmpleado() {
        return vgn_idEmpleado;
    }

    public void setVgn_idEmpleado(int vgn_idEmpleado) {
        this.vgn_idEmpleado = vgn_idEmpleado;
    }

    public double getVgn_monto() {
        return vgn_monto;
    }

    public void setVgn_monto(double vgn_monto) {
        this.vgn_monto = vgn_monto;
    }
    
}
