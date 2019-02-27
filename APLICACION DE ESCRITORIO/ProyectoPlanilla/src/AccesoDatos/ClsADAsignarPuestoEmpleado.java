/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Configuracion.ClsConexion;
import Entidades.ClsAsignarPuestoEmpleado;
import Entidades.ClsRetorno;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author Thomas Chevez
 */
public class ClsADAsignarPuestoEmpleado {

    //Atributos
    private Connection vgo_Connection;

    //Metodos
    public ClsADAsignarPuestoEmpleado() {
        //Se instancia la conexion
        ClsConexion vlo_ClsConexion = new ClsConexion();
        try {
            //Se guarda en una variable global la conexion
            vgo_Connection = vlo_ClsConexion.ClsConexionBD();
        } catch (Exception e) {
            throw e;
        }
    }

    public ClsRetorno AsignarPuestoEmpleado(ClsAsignarPuestoEmpleado pvo_AsignarPuestoEmpleado) {
        //Variables
        CallableStatement vlo_CS;
        ClsRetorno vlo_Retorno;
        
        //Inicio
        try {
            
        } catch (Exception e) {
        }
    }
}
