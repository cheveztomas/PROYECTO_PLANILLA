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
abstract class ClsDesglose {

    //Atributos
    private int vgn_id, vgn_idDetallePLanilla;
    private String vgc_Concepto;
    private double vgn_Porcentaje;
    private double vgn_Monto;

    //Constructores
    public ClsDesglose(int vgn_id, int vgn_idDetallePLanilla, String vgc_Concepto, double vgn_Porcentaje, double vgn_Monto) {
        this.vgn_id = vgn_id;
        this.vgn_idDetallePLanilla = vgn_idDetallePLanilla;
        this.vgc_Concepto = vgc_Concepto;
        this.vgn_Porcentaje = vgn_Porcentaje;
        this.vgn_Monto = vgn_Monto;
    }

    public ClsDesglose() {
        vgc_Concepto = "";
        vgn_Monto = 0;
        vgn_Porcentaje = 0;
        vgn_id = 0;
        vgn_idDetallePLanilla = 0;
    }
    
    //Propiedades
    public int getVgn_id() {
        return vgn_id;
    }

    public void setVgn_id(int vgn_id) {
        this.vgn_id = vgn_id;
    }

    public int getVgn_idDetallePLanilla() {
        return vgn_idDetallePLanilla;
    }

    public void setVgn_idDetallePLanilla(int vgn_idDetallePLanilla) {
        this.vgn_idDetallePLanilla = vgn_idDetallePLanilla;
    }

    public String getVgc_Concepto() {
        return vgc_Concepto;
    }

    public void setVgc_Concepto(String vgc_Concepto) {
        this.vgc_Concepto = vgc_Concepto;
    }

    public double getVgn_Porcentaje() {
        return vgn_Porcentaje;
    }

    public void setVgn_Porcentaje(double vgn_Porcentaje) {
        this.vgn_Porcentaje = vgn_Porcentaje;
    }

    public double getVgn_Monto() {
        return vgn_Monto;
    }

    public void setVgn_Monto(double vgn_Monto) {
        this.vgn_Monto = vgn_Monto;
    }
    
}
