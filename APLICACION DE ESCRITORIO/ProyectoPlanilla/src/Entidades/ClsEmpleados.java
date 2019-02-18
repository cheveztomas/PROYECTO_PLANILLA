/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Thomas Chevez
 */
public class ClsEmpleados {

    //Atributos
    private int vgn_idEmpleado;
    private String vgc_cedula, vgc_nombre, vgc_primerApellido, vgc_segundoApellido, vgc_telefono, vgc_correo, vgc_numeroCuenta;
    private java.sql.Date vgf_fechaContratacion;
    private double vgn_prestamo;
    private double vgn_pension;
    private boolean vgb_borradoLogico;

    //Constructores
    public ClsEmpleados(int vgn_idEmpleado, String vgc_cedula, String vgc_nombre, String vgc_primerApellido, String vgc_segundoApellido, String vgc_telefono, String vgc_correo, String vgc_numeroCuenta, java.sql.Date vgf_fechaContratacion, double vgn_prestamo, double vgn_pension, boolean vgb_borradoLogico) {
        this.vgn_idEmpleado = vgn_idEmpleado;
        this.vgc_cedula = vgc_cedula;
        this.vgc_nombre = vgc_nombre;
        this.vgc_primerApellido = vgc_primerApellido;
        this.vgc_segundoApellido = vgc_segundoApellido;
        this.vgc_telefono = vgc_telefono;
        this.vgc_correo = vgc_correo;
        this.vgc_numeroCuenta = vgc_numeroCuenta;
        this.vgf_fechaContratacion = vgf_fechaContratacion;
        this.vgn_prestamo = vgn_prestamo;
        this.vgn_pension = vgn_pension;
        this.vgb_borradoLogico = vgb_borradoLogico;
    }

    public ClsEmpleados() throws ParseException {
        vgb_borradoLogico = false;
        vgc_cedula = "";
        vgc_correo = "";
        vgc_nombre = "";
        vgc_numeroCuenta = "";
        vgc_primerApellido = "";
        vgc_segundoApellido = "";
        vgc_telefono = "";
        //Se guarda la fecha actual en la clase.
        java.util.Date fecha = new java.util.Date();
        String fechaString = new SimpleDateFormat("yyyyMMdd").format(fecha);
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        Date parsed = (Date) formato.parse(fechaString);
        vgf_fechaContratacion = new java.sql.Date(parsed.getTime());
        vgn_idEmpleado = 0;
        vgn_pension = 0;
        vgn_prestamo = 0;
    }
    
    //Propiedades

    public int getVgn_idEmpleado() {
        return vgn_idEmpleado;
    }

    public void setVgn_idEmpleado(int vgn_idEmpleado) {
        this.vgn_idEmpleado = vgn_idEmpleado;
    }

    public String getVgc_cedula() {
        return vgc_cedula;
    }

    public void setVgc_cedula(String vgc_cedula) {
        this.vgc_cedula = vgc_cedula;
    }

    public String getVgc_nombre() {
        return vgc_nombre;
    }

    public void setVgc_nombre(String vgc_nombre) {
        this.vgc_nombre = vgc_nombre;
    }

    public String getVgc_primerApellido() {
        return vgc_primerApellido;
    }

    public void setVgc_primerApellido(String vgc_primerApellido) {
        this.vgc_primerApellido = vgc_primerApellido;
    }

    public String getVgc_segundoApellido() {
        return vgc_segundoApellido;
    }

    public void setVgc_segundoApellido(String vgc_segundoApellido) {
        this.vgc_segundoApellido = vgc_segundoApellido;
    }

    public String getVgc_telefono() {
        return vgc_telefono;
    }

    public void setVgc_telefono(String vgc_telefono) {
        this.vgc_telefono = vgc_telefono;
    }

    public String getVgc_correo() {
        return vgc_correo;
    }

    public void setVgc_correo(String vgc_correo) {
        this.vgc_correo = vgc_correo;
    }

    public String getVgc_numeroCuenta() {
        return vgc_numeroCuenta;
    }

    public void setVgc_numeroCuenta(String vgc_numeroCuenta) {
        this.vgc_numeroCuenta = vgc_numeroCuenta;
    }

    public java.sql.Date getVgf_fechaContratacion() {
        return vgf_fechaContratacion;
    }

    public void setVgf_fechaContratacion(java.sql.Date vgf_fechaContratacion) {
        this.vgf_fechaContratacion = vgf_fechaContratacion;
    }

    public double getVgn_prestamo() {
        return vgn_prestamo;
    }

    public void setVgn_prestamo(double vgn_prestamo) {
        this.vgn_prestamo = vgn_prestamo;
    }

    public double getVgn_pension() {
        return vgn_pension;
    }

    public void setVgn_pension(double vgn_pension) {
        this.vgn_pension = vgn_pension;
    }

    public boolean isVgb_borradoLogico() {
        return vgb_borradoLogico;
    }

    public void setVgb_borradoLogico(boolean vgb_borradoLogico) {
        this.vgb_borradoLogico = vgb_borradoLogico;
    }
    
}
