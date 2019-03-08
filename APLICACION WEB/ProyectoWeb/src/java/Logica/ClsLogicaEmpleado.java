/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADEmpleados;
import Entidades.ClsEmpleados;
import Entidades.ClsRetorno;
import java.sql.ResultSet;

/**
 *
 * @author Thomas Chevez
 */
public class ClsLogicaEmpleado {

    public ClsRetorno GuardarEmpleado(ClsEmpleados pvo_Empleado) throws Exception {
        //Variables
        String vlc_Mensaje = "";
        ClsRetorno vlo_Retorno;
        ClsADEmpleados vlo_LogicaEmpleado;

        //Inicio
        try {
            vlo_LogicaEmpleado = new ClsADEmpleados();
            vlo_Retorno = vlo_LogicaEmpleado.GuardarEmpleado(pvo_Empleado);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }

    public ResultSet ListaEmpleados(String pvc_ValorFiltrado) throws Exception {
        //Variables
        ResultSet vlo_RS = null;
        ClsADEmpleados vlo_ADEmpleados;

        //Inicio
        try {
            vlo_ADEmpleados = new ClsADEmpleados();
            vlo_RS = vlo_ADEmpleados.ListaEmpledos(pvc_ValorFiltrado);
        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }

    public ClsEmpleados ObtenerEmpleado(int pvn_idEmpleado) throws Exception {
        //Variables
        ClsEmpleados vlo_Empleado = new ClsEmpleados();
        ClsADEmpleados vlo_ADEmpleados;

        //Inicio
        try {
            vlo_ADEmpleados = new ClsADEmpleados();
            vlo_Empleado = vlo_ADEmpleados.RetornarEmpledo(pvn_idEmpleado);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Empleado;
    }

    public String EliminarEmpleado(int pvn_idEmpleado) throws Exception {
        //Variables
        ClsADEmpleados vlo_ADEmpleados;
        String vlc_Mensaje = "";
        //Inicio
        try {
            vlo_ADEmpleados = new ClsADEmpleados();
            vlc_Mensaje = vlo_ADEmpleados.EliminarEmpleado(pvn_idEmpleado);
        } catch (Exception e) {
            throw e;
        }

        return vlc_Mensaje;
    }
}
