/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author tomas
 */
public class ClsPlanilla {

    //Atributos
    int vgn_idPlanilla;
    java.sql.Date vgf_Fecha;
    boolean vgb_borradoLogico;

    //Constructores
    public ClsPlanilla(int vgn_idPlanilla, Date vgf_Fecha, boolean vgb_borradoLogico) {
        this.vgn_idPlanilla = vgn_idPlanilla;
        this.vgf_Fecha = vgf_Fecha;
        this.vgb_borradoLogico = vgb_borradoLogico;
    }

    public ClsPlanilla() throws ParseException {
        vgb_borradoLogico = false;
        //Se guarda la fecha actual en la clase.
        java.util.Date fecha = new java.util.Date();
        String fechaString = new SimpleDateFormat("yyyyMMdd").format(fecha);
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = (java.util.Date) formato.parse(fechaString);
        vgf_Fecha = new java.sql.Date(parsed.getTime());
        vgn_idPlanilla = 0;
    }
    
    //Propiedades

    public int getVgn_idPlanilla() {
        return vgn_idPlanilla;
    }

    public void setVgn_idPlanilla(int vgn_idPlanilla) {
        this.vgn_idPlanilla = vgn_idPlanilla;
    }

    public Date getVgf_Fecha() {
        return vgf_Fecha;
    }

    public void setVgf_Fecha(Date vgf_Fecha) {
        this.vgf_Fecha = vgf_Fecha;
    }

    public boolean isVgb_borradoLogico() {
        return vgb_borradoLogico;
    }

    public void setVgb_borradoLogico(boolean vgb_borradoLogico) {
        this.vgb_borradoLogico = vgb_borradoLogico;
    }
    
}
