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
public class ClsDeduccionesPagos {

    //Atributos
    private int vgn_idDeduccionPago;
    private String vgc_DeduccionGeneral, vgc_DeduccionDetallada, vgc_tipo;
    private double vgn_Monto;
    private boolean vgc_EsDeduccion;

    //Constructores
    public ClsDeduccionesPagos(int vgn_idDeduccionPago, String vgc_DeduccionGeneral, String vgc_DeduccionDetallada, String vgc_tipo, double vgn_Monto, boolean vgc_EsDeduccion) {
        this.vgn_idDeduccionPago = vgn_idDeduccionPago;
        this.vgc_DeduccionGeneral = vgc_DeduccionGeneral;
        this.vgc_DeduccionDetallada = vgc_DeduccionDetallada;
        this.vgc_tipo = vgc_tipo;
        this.vgn_Monto = vgn_Monto;
        this.vgc_EsDeduccion = vgc_EsDeduccion;
    }

    public ClsDeduccionesPagos() {
        vgc_DeduccionDetallada = "";
        vgc_DeduccionGeneral = "";
        vgc_EsDeduccion = false;
        vgc_tipo = "";
        vgn_Monto = 0;
        vgn_idDeduccionPago = 0;
    }
    
    //Propiedades

    public int getVgn_idDeduccionPago() {
        return vgn_idDeduccionPago;
    }

    public void setVgn_idDeduccionPago(int vgn_idDeduccionPago) {
        this.vgn_idDeduccionPago = vgn_idDeduccionPago;
    }

    public String getVgc_DeduccionGeneral() {
        return vgc_DeduccionGeneral;
    }

    public void setVgc_DeduccionGeneral(String vgc_DeduccionGeneral) {
        this.vgc_DeduccionGeneral = vgc_DeduccionGeneral;
    }

    public String getVgc_DeduccionDetallada() {
        return vgc_DeduccionDetallada;
    }

    public void setVgc_DeduccionDetallada(String vgc_DeduccionDetallada) {
        this.vgc_DeduccionDetallada = vgc_DeduccionDetallada;
    }

    public String getVgc_tipo() {
        return vgc_tipo;
    }

    public void setVgc_tipo(String vgc_tipo) {
        this.vgc_tipo = vgc_tipo;
    }

    public double getVgn_Monto() {
        return vgn_Monto;
    }

    public void setVgn_Monto(double vgn_Monto) {
        this.vgn_Monto = vgn_Monto;
    }

    public boolean isVgc_EsDeduccion() {
        return vgc_EsDeduccion;
    }

    public void setVgc_EsDeduccion(boolean vgc_EsDeduccion) {
        this.vgc_EsDeduccion = vgc_EsDeduccion;
    }
    
}
