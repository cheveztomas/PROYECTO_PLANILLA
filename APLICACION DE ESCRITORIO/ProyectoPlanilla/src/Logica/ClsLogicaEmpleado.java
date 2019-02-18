/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADEmpleados;
import Entidades.ClsEmpleados;
import java.sql.ResultSet;

/**
 *
 * @author Thomas Chevez
 */
public class ClsLogicaEmpleado {

    public String GuardarEmpleado(ClsEmpleados pvo_Empleado) throws Exception {
        //Variables
        String vlc_Mensaje = "";
        ClsADEmpleados vlo_LogicaEmpleado = new ClsADEmpleados();

        //Inicio
        try {
            vlc_Mensaje = vlo_LogicaEmpleado.GuardarEmpleado(pvo_Empleado);
        } catch (Exception e) {
            throw e;
        }
        return vlc_Mensaje;
    }

    public ResultSet ListaEmpleados(String pvc_ValorFiltrado) throws Exception {
        //Variables
        ResultSet vlo_RS = null;
        ClsADEmpleados vlo_ADEmpleados = new ClsADEmpleados();

        //Inicio
        try {
            vlo_RS = vlo_ADEmpleados.ListaEmpledos(pvc_ValorFiltrado);
        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }
}
