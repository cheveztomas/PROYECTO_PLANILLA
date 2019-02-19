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
import java.sql.Statement;
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
        ResultSet vlo_RSEmpladosInf;
        ResultSet vlo_RSPagos;
        ResultSet vlo_RSDeducciones;
        CallableStatement vlo_CS;
        ClsRetorno vlo_Retorno = new ClsRetorno();
        String vlc_SentenciaSQL;
        Statement vlo_Statement;

        //Inicio
        try {
            vgo_Connection.setAutoCommit(false);

            //Se inserta una nueva planilla.
            vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_PLANILLA(?,?)}");
            vlo_CS.setInt(1, pvo_Planilla.getVgn_idPlanilla());
            vlo_CS.setDate(2, pvo_Planilla.getVgf_Fecha());
            vlo_CS.registerOutParameter(1, Types.INTEGER);
            vlo_CS.registerOutParameter(2, Types.VARCHAR);
            vlo_CS.executeUpdate();
            vlo_Retorno.setVgc_ID(vlo_CS.getInt(1));
            vlo_Retorno.setVgc_Mensaje(vlo_CS.getString(2));

            //Se estable la sentencia sql
            vlc_SentenciaSQL = "SELECT EMPLEADOS.ID_EMPLEADO,SALARIO_BASE,PRESTAMO,PENSION,CATEGORIA_PUESTO FROM EMPLEADOS INNER JOIN EMPLEADOS_PUESTOS ON EMPLEADOS.ID_EMPLEADO = EMPLEADOS_PUESTOS.ID_EMPLEADO INNER JOIN PUESTOS ON EMPLEADOS_PUESTOS.ID_PUESTO=PUESTOS.ID_PUESTO WHERE EMPLEADOS.BORRADO_LOGICO=0";

            //Se lee toda la información de todos los usuarios
            vlo_Statement = vgo_Connection.createStatement();
            vlo_RSEmpladosInf = vlo_Statement.executeQuery(vlc_SentenciaSQL);

            //Se establese la nueva sentencia sql
            vlc_SentenciaSQL = "SELECT ID_DEDUCCION_PAGO,DEDUCCION_GENERAL,ES_DEDUCCION,TIPO,MONTO FROM DEDUCCIONES_PAGOS WHERE ES_DEDUCCION=1";

            //Se obtinen todas las deducciones existentes.
            vlo_Statement = vgo_Connection.createStatement();
            vlo_RSDeducciones = vlo_Statement.executeQuery(vlc_SentenciaSQL);

            //Se establese la nueva sentencia sql
            vlc_SentenciaSQL = "SELECT ID_DEDUCCION_PAGO,DEDUCCION_GENERAL,TIPO,MONTO FROM DEDUCCIONES_PAGOS WHERE ES_DEDUCCION=0";

            //Se obtiene la lista de pagos existentes
            vlc_SentenciaSQL = "SELECT ID_DEDUCCION_PAGO,DEDUCCION_GENERAL,TIPO,MONTO FROM DEDUCCIONES_PAGOS WHERE ES_DEDUCCION=0";

            vgo_Connection.commit();
        } catch (Exception e) {
            vgo_Connection.rollback();
        }
        return vlo_Retorno;
    }
}
