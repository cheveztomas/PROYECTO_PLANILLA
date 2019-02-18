/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Entidades.ClsEmpleados;

/**
 *
 * @author Thomas Chevez
 */
public class ClsLogicaEmpleado {

    public String GuardarEmpleado(ClsEmpleados pvo_Empleado) {
        //Variables
        String vlc_Mensaje = "";
        ClsLogicaEmpleado vlo_LogicaEmpleado = new ClsLogicaEmpleado();

        //Inicio
        try {
            vlc_Mensaje = vlo_LogicaEmpleado.GuardarEmpleado(pvo_Empleado);
        } catch (Exception e) {
            throw e;
        }
        return vlc_Mensaje;
    }
}
