/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Configuracion.ClsConexion;
import java.sql.Connection;

/**
 *
 * @author tomas
 */
public class ClsADInformacionAcademica {

    //Atributos
    Connection vgo_Connection;

    //Metodos
    public ClsADInformacionAcademica() {
        //Se instancia la conexion
        ClsConexion vlo_ClsConexion = new ClsConexion();
        try {
            //Se guarda en una variable global la conexion
            vgo_Connection = vlo_ClsConexion.ClsConexionBD();
        } catch (Exception e) {
            throw e;
        }
    }
}
