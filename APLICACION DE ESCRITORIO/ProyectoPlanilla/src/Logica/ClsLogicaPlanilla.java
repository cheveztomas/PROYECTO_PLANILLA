/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADPlanilla;
import Entidades.ClsPlanilla;
import Entidades.ClsRetorno;

/**
 *
 * @author Thomas Chevez
 */
public class ClsLogicaPlanilla {

    public ClsRetorno GenerarPlanilla(ClsPlanilla pvo_Planilla) throws Exception {
        //Variables
        ClsADPlanilla vlo_ADPlanilla;
        ClsRetorno vlo_Retorno = new ClsRetorno();

        //Inicio
        try {
            vlo_ADPlanilla = new ClsADPlanilla();
            vlo_Retorno = vlo_ADPlanilla.GenerarPlanilla(pvo_Planilla);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }
}
