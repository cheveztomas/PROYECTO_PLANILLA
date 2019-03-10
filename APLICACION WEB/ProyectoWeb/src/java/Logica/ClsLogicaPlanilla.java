/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADPlanilla;
import Entidades.ClsPlanilla;
import Entidades.ClsRetorno;
import java.sql.ResultSet;

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

    public ResultSet ListaDetallesPLanilla(int pvn_idPlanilla) throws Exception {
        //Variables
        ResultSet vlo_RS;
        ClsADPlanilla vloADPlanilla = new ClsADPlanilla();

        //Inicio
        try {
            vlo_RS = vloADPlanilla.ListaDetallesPlanilla(pvn_idPlanilla);
        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }

    public ResultSet ListaPlanilla(String pvc_Mes, String pvc_Anio) throws Exception {
        //Varisables
        ResultSet vlo_RS;
        ClsADPlanilla vlo_ADPlanilla;

        //Inicio
        try {
            vlo_ADPlanilla = new ClsADPlanilla();
            vlo_RS = vlo_ADPlanilla.ListaPlanillas(pvc_Mes, pvc_Anio);

        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }
}
