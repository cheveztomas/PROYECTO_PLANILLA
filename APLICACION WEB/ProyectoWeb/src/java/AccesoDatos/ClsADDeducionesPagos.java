/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Configuracion.ClsConexion;
import Entidades.ClsDeduccionesPagos;
import Entidades.ClsPension;
import Entidades.ClsPrestamo;
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
public class ClsADDeducionesPagos {

    //Atributos
    Connection vgo_Conexion;

    //Metodos
    public ClsADDeducionesPagos() {
        //Variables
        ClsConexion vlo_ClsConexion = new ClsConexion();

        //Inicio
        try {
            vgo_Conexion = vlo_ClsConexion.ClsConexionBD();
        } catch (Exception e) {
            throw e;
        }
    }

    public ClsRetorno GuardarDeduccionesPagos(ClsDeduccionesPagos pvo_DeduccionesPagos) throws Exception {
        //Variables
        String vlc_Mensaje = "";
        CallableStatement vlo_CS;
        ClsRetorno vlo_retorno = new ClsRetorno();

        //Inicio
        //return vlc_Mensaje;
        try {
            vlo_CS = vgo_Conexion.prepareCall("{call SP_GUARDAR_DEDUCCIONES_PAGOS(?,?,?,?,?,?,?)}");
            vlo_CS.setInt(1, pvo_DeduccionesPagos.getVgn_idDeduccionPago());
            vlo_CS.setString(2, pvo_DeduccionesPagos.getVgc_DeduccionGeneral());
            vlo_CS.setString(3, pvo_DeduccionesPagos.getVgc_DeduccionDetallada());
            vlo_CS.setBoolean(4, pvo_DeduccionesPagos.isVgc_EsDeduccion());
            vlo_CS.setString(5, pvo_DeduccionesPagos.getVgc_tipo());
            vlo_CS.setDouble(6, pvo_DeduccionesPagos.getVgn_Monto());
            vlo_CS.setString(7, vlo_retorno.getVgc_Mensaje());
            vlo_CS.registerOutParameter(1, Types.INTEGER);
            vlo_CS.registerOutParameter(7, Types.VARCHAR);

            //Se ejecuta el procedimento almacenado
            vlo_CS.executeUpdate();

            //Se almacena los valores retornados en la entidad.
            vlo_retorno.setVgc_ID(vlo_CS.getInt(1));
            vlo_retorno.setVgc_Mensaje(vlo_CS.getString(7));
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Conexion = null;
        }
        return vlo_retorno;
    }

    public ResultSet ListaDeduccionesPagos(String pvc_Condicion) throws Exception {
        //Variables
        ResultSet vlo_RS;
        Statement vlo_Statement;
        String vlc_Sentencia = "SELECT ID_DEDUCCION_PAGO, DEDUCCION_GENERAL,DEDUCCION_DETALLADA, ES_DEDUCCION, TIPO, MONTO FROM DEDUCCIONES_PAGOS WHERE DEDUCCION_GENERAL LIKE '%" + pvc_Condicion + "%'";

        //Inicio
        try {
            vlo_Statement = vgo_Conexion.createStatement();
            vlo_RS = vlo_Statement.executeQuery(vlc_Sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Conexion = null;
        }
        return vlo_RS;
    }

    public ClsRetorno Eliminar(int pvn_idDeduccionPago) throws Exception {
        //Variables
        ClsDeduccionesPagos vlo_DeduccionesPagos = new ClsDeduccionesPagos();
        CallableStatement vlo_CS;
        ClsRetorno vlo_Retorno = new ClsRetorno();

        //Inicio
        try {
            vlo_CS = vgo_Conexion.prepareCall("{call SP_ELIMINAR_DEDDUCIONES_PAGOS(?,?)}");
            vlo_CS.setInt(1, pvn_idDeduccionPago);
            vlo_CS.setString(2, vlo_Retorno.getVgc_Mensaje());
            vlo_CS.registerOutParameter(2, Types.VARCHAR);
            vlo_Retorno.setVgc_ID(vlo_CS.executeUpdate());
            vlo_Retorno.setVgc_Mensaje(vlo_CS.getString(2));
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Conexion = null;
        }
        return vlo_Retorno;
    }

    public ClsDeduccionesPagos ObtenerDeduccionesPagos(int pvn_idDeduccionesPagos) throws Exception {
        //Variables
        ClsDeduccionesPagos vlo_DeduccionesPagos = new ClsDeduccionesPagos();
        ResultSet vlo_RS;
        Statement vlo_Statement;
        String vlc_Sentencia;

        //Inicio
        vlc_Sentencia = "SELECT ID_DEDUCCION_PAGO, DEDUCCION_GENERAL,DEDUCCION_DETALLADA, ES_DEDUCCION, TIPO, MONTO FROM DEDUCCIONES_PAGOS WHERE ID_DEDUCCION_PAGO='" + pvn_idDeduccionesPagos + "'";
        try {
            vlo_Statement = vgo_Conexion.createStatement();
            vlo_RS = vlo_Statement.executeQuery(vlc_Sentencia);
            if (vlo_RS.next()) {
                vlo_DeduccionesPagos.setVgn_idDeduccionPago(vlo_RS.getInt(1));
                vlo_DeduccionesPagos.setVgc_DeduccionGeneral(vlo_RS.getString(2));
                vlo_DeduccionesPagos.setVgc_DeduccionDetallada(vlo_RS.getString(3));
                vlo_DeduccionesPagos.setVgc_EsDeduccion(vlo_RS.getBoolean(4));
                vlo_DeduccionesPagos.setVgc_tipo(vlo_RS.getString(5));
                vlo_DeduccionesPagos.setVgn_Monto(vlo_RS.getDouble(6));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Conexion = null;
        }
        return vlo_DeduccionesPagos;
    }

    public String AgregarPension(ClsPension vlo_Pension) throws Exception {
        //Variables
        String vlc_Mensaje = "";
        CallableStatement vlo_CS;

        //Inicio
        try {
            vlo_CS = vgo_Conexion.prepareCall("{call SP_AGREGAR_PENSION(?,?,?)}");
            vlo_CS.setInt(1, vlo_Pension.getVgn_idEmpleado());
            vlo_CS.setDouble(2, vlo_Pension.getVgn_monto());
            vlo_CS.setString(3, vlc_Mensaje);
            vlo_CS.registerOutParameter(3, Types.VARCHAR);
            vlo_CS.executeUpdate();
            vlc_Mensaje = vlo_CS.getString(3);
        } catch (Exception e) {
            throw e;
        }
        return vlc_Mensaje;
    }

    public String AgregarPrestamo(ClsPrestamo vlo_Prestamo) throws Exception {
        //Variables
        String vlc_Mensaje = "";
        CallableStatement vlo_CS;

        //Inicio
        try {
            vlo_CS = vgo_Conexion.prepareCall("{call SP_AGREGAR_PRESTAMO(?,?,?)}");
            vlo_CS.setInt(1, vlo_Prestamo.getVgn_idEmpleado());
            vlo_CS.setDouble(2, vlo_Prestamo.getVgn_monto());
            vlo_CS.setString(3, vlc_Mensaje);
            vlo_CS.registerOutParameter(3, Types.VARCHAR);
            vlo_CS.executeUpdate();
            vlc_Mensaje = vlo_CS.getString(3);
        } catch (Exception e) {
            throw e;
        }
        return vlc_Mensaje;
    }

    public ResultSet ObtenerPrestamoPension(int pvn_idEmpleado) throws Exception {
        //Variables
        Statement vlo_Statement;
        String vlc_Sentencia = "SELECT ID_EMPLEADO,CONCAT(NOMBRE,' ',PRIMER_APELLIDO,' ',SEGUNDO_APELLIDO) AS NOMBRE,PRESTAMO,PENSION FROM EMPLEADOS WHERE ID_EMPLEADO='" + pvn_idEmpleado + "'";
        ResultSet vlo_RS;

        //Inicio
        try {
            vlo_Statement = vgo_Conexion.createStatement();
            vlo_RS = vlo_Statement.executeQuery(vlc_Sentencia);
        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }
}
