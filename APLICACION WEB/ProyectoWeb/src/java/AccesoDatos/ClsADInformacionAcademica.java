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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author tomas
 */
public class ClsADInformacionAcademica {

    //Atributos
    private Connection vgo_Connection;

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

    public ClsRetorno GuardarInformacionAcademica(ClsInformacionAcademica pvo_inInformacionAcademica) throws Exception {
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

            //Se ejecuta el procedimiento alamacenado
            vlo_CS.executeUpdate();

            //Leer los parametros de salida.
            vlo_Retorno.setVgc_ID(vlo_CS.getInt(1));
            vlo_Retorno.setVgc_Mensaje(vlo_CS.getString(2));
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Connection = null;
        }

        return vlo_Retorno;
    }

    public ClsRetorno EliminarInformacionAcademica(int pvn_idInformacionA) throws Exception {
        //Varaibles
        ClsRetorno vlo_Retorno = new ClsRetorno();
        CallableStatement vlo_CS;

        //Inicio
        try {
            //se establese la conexión con la base de datos.
            vlo_CS = vgo_Connection.prepareCall("{call SP_ELIMINAR_INFORMACION_ACADEMICA(?,?)}");

            //Se setean los valores de para el procedimeinto alamacenado.
            vlo_CS.setInt(1, pvn_idInformacionA);
            vlo_CS.setString(2, vlo_Retorno.getVgc_Mensaje());
            vlo_CS.registerOutParameter(2, Types.VARCHAR);

            //Se invoca el procedimiento almacenado de eliminar información academica
            vlo_CS.executeUpdate();

            //Se guardan los valores de retorno en el objeto de retorno.
            vlo_Retorno.setVgc_ID(vlo_CS.getInt(1));
            vlo_Retorno.setVgc_Mensaje(vlo_CS.getString(2));
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Connection = null;
        }
        return vlo_Retorno;
    }

    public ResultSet ListaInformacionAcademica(String pvc_Condicion) throws Exception {
        //Variables
        ResultSet vlo_RS;
        Statement vlo_Statement;
        String vlc_Sentencia = "SELECT ID_INFORMACION_A,ID_EMPLEADO,GRADO,ESPECIALIDAD,INFORMACION FROM INFORMACION_ACADEMICA WHERE ESPECIALIDAD LIKE '%" + pvc_Condicion + "%'";

        //Inicio
        try {
            vlo_Statement = vgo_Connection.createStatement();
            vlo_RS = vlo_Statement.executeQuery(vlc_Sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Connection = null;
        }
        return vlo_RS;
    }

    public ClsInformacionAcademica ObtenerInformacionAcademica(int pvn_idInformacionAcademica) throws Exception {
        //Variables
        ResultSet vlo_RS;
        Statement vlo_Statement;
        ClsInformacionAcademica vlo_InformacionAcademica = new ClsInformacionAcademica();
        String vlc_SEnetencia = "SELECT ID_EMPLEADO,ID_INFORMACION_A, GRADO, ESPECIALIDAD, INFORMACION, BORRADO_LOGICO FROM INFORMACION_ACADEMICA WHERE ID_INFORMACION_A='" + pvn_idInformacionAcademica + "' AND BORRADO_LOGICO=0";

        //Inicio
        try {
            vlo_Statement = vgo_Connection.createStatement();
            vlo_RS = vlo_Statement.executeQuery(vlc_SEnetencia);

            if (vlo_RS.next()) {
                vlo_InformacionAcademica.setVgn_idEmpleado(vlo_RS.getInt(1));
                vlo_InformacionAcademica.setVgn_idInformacionA(vlo_RS.getInt(2));
                vlo_InformacionAcademica.setVgc_Grado(vlo_RS.getString(3));
                vlo_InformacionAcademica.setVgc_especialidad(vlo_RS.getString(4));
                vlo_InformacionAcademica.setVgc_informacion(vlo_RS.getString(5));
                vlo_InformacionAcademica.setVgb_borradoLogico(vlo_RS.getBoolean(6));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Connection = null;
        }
        return vlo_InformacionAcademica;
    }
}
