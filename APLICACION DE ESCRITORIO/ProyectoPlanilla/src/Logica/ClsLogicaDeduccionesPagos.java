/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADDeducionesPagos;
import Entidades.ClsDeduccionesPagos;
import Entidades.ClsRetorno;
import java.sql.ResultSet;

/**
 *
 * @author Thomas Chevez
 */
public class ClsLogicaDeduccionesPagos {

    public ClsRetorno GuardarDeduccionesPagos(ClsDeduccionesPagos pvo_DeduccionesPagos) throws Exception {
        //Variables
        ClsRetorno vlo_Retorno = new ClsRetorno();
        ClsADDeducionesPagos vlo_ADDeduccionesPagos;

        //Inicio
        try {
            vlo_ADDeduccionesPagos = new ClsADDeducionesPagos();
            vlo_Retorno = vlo_ADDeduccionesPagos.GuardarDeduccionesPagos(pvo_DeduccionesPagos);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }

    public ResultSet ListaPagosDeducciones() throws Exception {
        //Variables
        ResultSet vlo_RS;
        ClsADDeducionesPagos vlo_ADDeducionesPagos;

        //Inicio
        try {
            vlo_ADDeducionesPagos = new ClsADDeducionesPagos();
            vlo_RS = vlo_ADDeducionesPagos.ListaDeduccionesPagos();
        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }

    public ClsRetorno EliminarDP(int pvn_idDedducionesPagos) throws Exception {
        //Variables
        ClsRetorno vlo_Retorno = new ClsRetorno();
        ClsADDeducionesPagos vlo_ADDeducionesPagos;

        //Inicio
        try {
            vlo_ADDeducionesPagos = new ClsADDeducionesPagos();
            vlo_Retorno = vlo_ADDeducionesPagos.Eliminar(pvn_idDedducionesPagos);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }

    public ClsDeduccionesPagos ObteDeduccionesPagos(int pvn_idDedducionesPagos) {
        //Variables
        ClsDeduccionesPagos vlo_DeduccionesPagos = new ClsDeduccionesPagos();
        ClsADDeducionesPagos vlo_ADDeducionesPagos;

        //Inicio
        try {
            vlo_ADDeducionesPagos = new ClsADDeducionesPagos();
        } catch (Exception e) {
        }
    }
}
