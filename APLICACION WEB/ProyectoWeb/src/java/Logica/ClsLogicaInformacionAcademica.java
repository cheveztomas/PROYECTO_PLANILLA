/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADInformacionAcademica;
import Entidades.ClsInformacionAcademica;
import Entidades.ClsRetorno;
import java.sql.ResultSet;

/**
 *
 * @author Thomas Chevez
 */
public class ClsLogicaInformacionAcademica {

    public ClsRetorno GuardarInformacionAcademica(ClsInformacionAcademica pvo_InformacionAcademica) throws Exception {
        //Variables
        ClsADInformacionAcademica vlo_InformacionAcademica;
        ClsRetorno vlo_Retorno;

        //Inicio
        try {
            vlo_InformacionAcademica = new ClsADInformacionAcademica();
            vlo_Retorno = vlo_InformacionAcademica.GuardarInformacionAcademica(pvo_InformacionAcademica);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }

    public ClsRetorno EliminarInformacionAcademica(int pvn_idInformacionA) throws Exception {
        //Variables
        ClsRetorno vlo_Retorno;
        ClsADInformacionAcademica vlo_ADInformacionAcademica;
        //Inicio
        try {
            vlo_ADInformacionAcademica = new ClsADInformacionAcademica();
            vlo_Retorno = vlo_ADInformacionAcademica.EliminarInformacionAcademica(pvn_idInformacionA);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }

    public ResultSet ListaInformacionAcademica(String pvc_Condicion, int pvn_idInofrmacionAcademica) throws Exception {
        //Variables
        ResultSet vlo_RS;
        ClsADInformacionAcademica vloADInformacionAcademica;

        //Inicio
        try {
            vloADInformacionAcademica = new ClsADInformacionAcademica();
            vlo_RS = vloADInformacionAcademica.ListaInformacionAcademica(pvc_Condicion, pvn_idInofrmacionAcademica);
        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }

    public ClsInformacionAcademica ObtenerInformacionAcademica(int pvn_idInformacionAcademica) throws Exception {
        //Variables
        ClsInformacionAcademica vlo_InformacionAcademica;
        ClsADInformacionAcademica vlo_ADInformacionAcademica;

        //Inicio
        try {
            vlo_ADInformacionAcademica = new ClsADInformacionAcademica();
            vlo_InformacionAcademica = vlo_ADInformacionAcademica.ObtenerInformacionAcademica(pvn_idInformacionAcademica);
        } catch (Exception e) {
            throw e;
        }
        return vlo_InformacionAcademica;
    }
}
