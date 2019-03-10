/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADAsignarPuestoEmpleado;
import Entidades.ClsAsignarPuestoEmpleado;
import Entidades.ClsRetorno;

/**
 *
 * @author Thomas Chevez
 */
public class ClsLogicaAsignarPuestoEmpleado {

    public ClsRetorno AsignarPuestoEmpleado(ClsAsignarPuestoEmpleado pvo_AsignarPuestoEmpleado) throws Exception {
        //Variables
        ClsRetorno vlo_Retorno;
        ClsADAsignarPuestoEmpleado vlo_ADAsignarPuestoEmpleado;

        //Inicio
        try {
            vlo_ADAsignarPuestoEmpleado = new ClsADAsignarPuestoEmpleado();
            vlo_Retorno = vlo_ADAsignarPuestoEmpleado.AsignarPuestoEmpleado(pvo_AsignarPuestoEmpleado);

        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }
}
