/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADPuestos;
import Entidades.ClsPuestos;
import Entidades.ClsRetorno;
import java.sql.ResultSet;

/**
 *
 * @author Thomas Chevez
 */
public class ClsLogicaPuestos {

    public ClsRetorno GuardarPuesto(ClsPuestos pvo_Puesto) throws Exception {
        //Variables
        ClsADPuestos vlo_ADPuestos;
        ClsRetorno vlo_Retorno = new ClsRetorno();

        //Inicio
        try {
            vlo_ADPuestos = new ClsADPuestos();
            vlo_Retorno = vlo_ADPuestos.GuardarPuesto(pvo_Puesto);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }

    public ResultSet ListaPuestos(String pvc_Condicion) throws Exception {
        //Variables
        ResultSet vlo_RS;
        ClsADPuestos vlo_ADPuestos;

        //Inicio
        try {
            vlo_ADPuestos = new ClsADPuestos();
            vlo_RS = vlo_ADPuestos.ListaProductos(pvc_Condicion);
        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }

    public ClsRetorno EliminarPuesto(int pvn_idPuesto) throws Exception {
        //Variables
        ClsRetorno vlo_Retorno = new ClsRetorno();
        ClsADPuestos vlo_ADRetorno;

        //Inicio
        try {
            vlo_ADRetorno = new ClsADPuestos();
            vlo_Retorno = vlo_ADRetorno.EliminarPuesto(pvn_idPuesto);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }

    public ClsPuestos ObtenerPuesto(int pvn_idPuesto) throws Exception {
        //Variables
        ClsPuestos vlo_Puestos = new ClsPuestos();
        ClsADPuestos vlo_ADPuestos;

        //Inicio
        try {
            vlo_ADPuestos = new ClsADPuestos();
            vlo_Puestos = vlo_ADPuestos.ObtenerPuesto(pvn_idPuesto);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Puestos;
    }

    public ClsPuestos ObtenerPuestosIdEmpleado(int pvn_idEmpleado) throws Exception {
        //Variables
        ClsPuestos vlo_Puesto;
        ClsADPuestos vlo_ADPuestos;

        //Inicio
        try {
            vlo_ADPuestos = new ClsADPuestos();
            vlo_Puesto = vlo_ADPuestos.ObtenerPuestoIdEmpleado(pvn_idEmpleado);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Puesto;
    }
}
