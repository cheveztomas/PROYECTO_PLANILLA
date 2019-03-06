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
public class ClsPuestos {

    //Atributos
    private int vgn_iPuesto, vgn_CategoriaPuesto;
    private double vgn_SalarioBase;
    private String vgc_NombrePuesto;
    private boolean vgb_borradoLogico;

    //Constructores
    public ClsPuestos(int vgn_iPuesto, int vgn_CategoriaPuesto, double vgn_SalarioBase, String vgc_NombrePuesto, boolean vgb_borradoLogico) {
        this.vgn_iPuesto = vgn_iPuesto;
        this.vgn_CategoriaPuesto = vgn_CategoriaPuesto;
        this.vgn_SalarioBase = vgn_SalarioBase;
        this.vgc_NombrePuesto = vgc_NombrePuesto;
        this.vgb_borradoLogico = vgb_borradoLogico;
    }

    public ClsPuestos() {
        vgb_borradoLogico = false;
        vgc_NombrePuesto = "";
        vgn_CategoriaPuesto = 0;
        vgn_SalarioBase = 0;
        vgn_iPuesto = 0;
    }
    
    //Propiedades

    public int getVgn_iPuesto() {
        return vgn_iPuesto;
    }

    public void setVgn_iPuesto(int vgn_iPuesto) {
        this.vgn_iPuesto = vgn_iPuesto;
    }

    public int getVgn_CategoriaPuesto() {
        return vgn_CategoriaPuesto;
    }

    public void setVgn_CategoriaPuesto(int vgn_CategoriaPuesto) {
        this.vgn_CategoriaPuesto = vgn_CategoriaPuesto;
    }

    public double getVgn_SalarioBase() {
        return vgn_SalarioBase;
    }

    public void setVgn_SalarioBase(double vgn_SalarioBase) {
        this.vgn_SalarioBase = vgn_SalarioBase;
    }

    public String getVgc_NombrePuesto() {
        return vgc_NombrePuesto;
    }

    public void setVgc_NombrePuesto(String vgc_NombrePuesto) {
        this.vgc_NombrePuesto = vgc_NombrePuesto;
    }

    public boolean isVgb_borradoLogico() {
        return vgb_borradoLogico;
    }

    public void setVgb_borradoLogico(boolean vgb_borradoLogico) {
        this.vgb_borradoLogico = vgb_borradoLogico;
    }
    
}
