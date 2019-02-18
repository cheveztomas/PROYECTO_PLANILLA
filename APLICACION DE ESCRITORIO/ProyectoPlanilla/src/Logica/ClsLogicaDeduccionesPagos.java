/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ClsADDeducionesPagos;
import Entidades.ClsDeduccionesPagos;
import Entidades.ClsRetorno;

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
}
