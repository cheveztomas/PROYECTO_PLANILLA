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
public class ClsDetallePlanillas {

    //Atributos
    private int vgn_idDetallePlanilla, vgn_idPlanilla, vgnIdEmpleado;
    private double vgn_SalarioBruto, vgn_SararioNeto, vgn_PrimerAdelanto, vgn_AdelantoFinal;
    private boolean vgb_borradoLogico;

    //Constructores
    public ClsDetallePlanillas(int vgn_idDetallePlanilla, int vgn_idPlanilla, int vgnIdEmpleado, double vgn_SalarioBruto, double vgn_SararioNeto, double vgn_PrimerAdelanto, double vgn_AdelantoFinal, boolean vgb_borradoLogico) {
        this.vgn_idDetallePlanilla = vgn_idDetallePlanilla;
        this.vgn_idPlanilla = vgn_idPlanilla;
        this.vgnIdEmpleado = vgnIdEmpleado;
        this.vgn_SalarioBruto = vgn_SalarioBruto;
        this.vgn_SararioNeto = vgn_SararioNeto;
        this.vgn_PrimerAdelanto = vgn_PrimerAdelanto;
        this.vgn_AdelantoFinal = vgn_AdelantoFinal;
        this.vgb_borradoLogico = vgb_borradoLogico;
    }

    public ClsDetallePlanillas() {
        vgb_borradoLogico = false;
        vgnIdEmpleado = 0;
        vgn_AdelantoFinal = 0;
        vgn_PrimerAdelanto = 0;
        vgn_SalarioBruto = 0;
        vgn_SararioNeto = 0;
        vgn_idDetallePlanilla = 0;
        vgn_idPlanilla = 0;
    }
    
    //Propiedades

    public int getVgn_idDetallePlanilla() {
        return vgn_idDetallePlanilla;
    }

    public void setVgn_idDetallePlanilla(int vgn_idDetallePlanilla) {
        this.vgn_idDetallePlanilla = vgn_idDetallePlanilla;
    }

    public int getVgn_idPlanilla() {
        return vgn_idPlanilla;
    }

    public void setVgn_idPlanilla(int vgn_idPlanilla) {
        this.vgn_idPlanilla = vgn_idPlanilla;
    }

    public int getVgnIdEmpleado() {
        return vgnIdEmpleado;
    }

    public void setVgnIdEmpleado(int vgnIdEmpleado) {
        this.vgnIdEmpleado = vgnIdEmpleado;
    }

    public double getVgn_SalarioBruto() {
        return vgn_SalarioBruto;
    }

    public void setVgn_SalarioBruto(double vgn_SalarioBruto) {
        this.vgn_SalarioBruto = vgn_SalarioBruto;
    }

    public double getVgn_SararioNeto() {
        return vgn_SararioNeto;
    }

    public void setVgn_SararioNeto(double vgn_SararioNeto) {
        this.vgn_SararioNeto = vgn_SararioNeto;
    }

    public double getVgn_PrimerAdelanto() {
        return vgn_PrimerAdelanto;
    }

    public void setVgn_PrimerAdelanto(double vgn_PrimerAdelanto) {
        this.vgn_PrimerAdelanto = vgn_PrimerAdelanto;
    }

    public double getVgn_AdelantoFinal() {
        return vgn_AdelantoFinal;
    }

    public void setVgn_AdelantoFinal(double vgn_AdelantoFinal) {
        this.vgn_AdelantoFinal = vgn_AdelantoFinal;
    }

    public boolean isVgb_borradoLogico() {
        return vgb_borradoLogico;
    }

    public void setVgb_borradoLogico(boolean vgb_borradoLogico) {
        this.vgb_borradoLogico = vgb_borradoLogico;
    }
    
}
