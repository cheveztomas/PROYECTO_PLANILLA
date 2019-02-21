/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Configuracion.ClsConexion;
import Entidades.ClsPuestos;
import Entidades.ClsRetorno;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author Thomas Chevez
 */
public class ClsADPuestos {

    private Connection vgo_Connection;

    public ClsADPuestos() {
        //Se instancia la conexion
        ClsConexion vlo_ClsConexion = new ClsConexion();
        try {
            //Se guarda en una variable global la conexion
            vgo_Connection = vlo_ClsConexion.ClsConexionBD();
        } catch (Exception e) {
            throw e;
        }
    }

    public ClsRetorno GuardarPuesto(ClsPuestos pvo_Puestos) {
        //Variables
        ClsRetorno vlo_Retorno = new ClsRetorno();
        CallableStatement vlo_CS;

        //Inicio
        try {
            vlo_CS = vgo_Connection.prepareCall("{call }");
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }
}
