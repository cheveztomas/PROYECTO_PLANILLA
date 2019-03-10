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
import java.sql.Types;

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
    
    public ClsRetorno AsignarPuestoEmpleado(ClsAsignarPuestoEmpleado pvo_AsignarPuestoEmpleado) throws Exception {
        //Variables
        CallableStatement vlo_CS;
        ClsRetorno vlo_Retorno;

        //Inicio
        try {
            //Se setetan los valores de la entidad.
            vlo_Retorno = new ClsRetorno();
            vlo_CS = vgo_Connection.prepareCall("{call SP_ASIGANAR_PUESTO_EMPLEADO(?,?,?,?,?)}");
            vlo_CS.setInt(1, pvo_AsignarPuestoEmpleado.getVgn_idEmpleadoPuesto());
            vlo_CS.setInt(2, pvo_AsignarPuestoEmpleado.getVgn_idEmpleado());
            vlo_CS.setInt(3, pvo_AsignarPuestoEmpleado.getVgn_idPuesto());
            vlo_CS.setInt(4, pvo_AsignarPuestoEmpleado.getVgn_idInformacion());
            vlo_CS.setString(5, vlo_Retorno.getVgc_Mensaje());
            vlo_CS.registerOutParameter(5, Types.VARCHAR);
            
            //Se ejecuta el procedimiento almacenado.
            vlo_CS.executeUpdate();
            
            //Se almacena en el objeto la información necesaria.
            vlo_Retorno.setVgc_Mensaje(vlo_CS.getString(5));
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }
}
