/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Configuracion.ClsConexion;
import Entidades.ClsPlanilla;
import Entidades.ClsRetorno;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author tomas
 */
public class ClsADPlanilla {

    //Atributos
    Connection vgo_Connection;

    //Metodos
    public ClsADPlanilla() {
        //Se instancia la conexion
        ClsConexion vlo_ClsConexion = new ClsConexion();
        try {
            //Se guarda en una variable global la conexion
            vgo_Connection = vlo_ClsConexion.ClsConexionBD();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public ClsRetorno GenerarPlanilla(ClsPlanilla pvo_Planilla) throws SQLException {
        //Variables
        ResultSet vlo_RS;
        CallableStatement vlo_CS;
        ClsRetorno vlo_Retorno = new ClsRetorno();

        //Inicio
        try {
            vgo_Connection.setAutoCommit(false);
            vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_PLANILLA(?,?)}");
            vlo_CS.setInt(1, pvo_Planilla.getVgn_idPlanilla());
            vlo_CS.setDate(2, pvo_Planilla.getVgf_Fecha());
            vlo_CS.registerOutParameter(1, Types.INTEGER);
            vlo_CS.registerOutParameter(2, Types.VARCHAR);
            vlo_CS.executeUpdate();
            vlo_Retorno.setVgc_ID(vlo_CS.getInt(1));
            vlo_Retorno.setVgc_Mensaje(vlo_CS.getString(2));
            vgo_Connection.commit();
        } catch (Exception e) {
            vgo_Connection.rollback();
        }
        return vlo_Retorno;
    }
}
