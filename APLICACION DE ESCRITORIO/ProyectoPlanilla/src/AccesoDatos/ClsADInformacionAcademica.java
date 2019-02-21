/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Configuracion.ClsConexion;
import Entidades.ClsInformacionAcademica;
import Entidades.ClsRetorno;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

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

    public ClsRetorno GuardarInformacionAcademica(ClsInformacionAcademica pvo_inInformacionAcademica) {
        //variables
        ClsRetorno vlo_Retorno = new ClsRetorno();
        CallableStatement vlo_CS;

        //Inicio
        try {
            //Se invoca el procedimiento almacenado de la base de datos.
            vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_INFORMACION_ACADEMICA(?,?,?,?,?,?)}");
            
            //Se setean los valores al procedimiento.
            vlo_CS.setInt(1, pvo_inInformacionAcademica.getVgn_idInformacionA());
            vlo_CS.setInt(2, pvo_inInformacionAcademica.getVgn_idEmpleado());
            vlo_CS.setString(3, pvo_inInformacionAcademica.getVgc_especialidad());
            vlo_CS.setString(4, pvo_inInformacionAcademica.getVgc_informacion());
            vlo_CS.setString(5, vlo_Retorno.getVgc_Mensaje());
            
            //Se establecen los valroes de salida.
            vlo_CS.registerOutParameter(1, Types.INTEGER);
            vlo_CS.registerOutParameter(5, Types.VARCHAR);
        } catch (Exception e) {
        }

        return vlo_Retorno;
    }
}
