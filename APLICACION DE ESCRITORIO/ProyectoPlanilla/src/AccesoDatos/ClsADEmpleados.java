/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import Configuracion.ClsConexion;
import Entidades.ClsEmpleados;
import java.sql.*;

/**
 *
 * @author Thomas Chevez
 */
public class ClsADEmpleados {

    private Connection vgo_Connection;

    public ClsADEmpleados() {
        //Se instancia la conexion
        ClsConexion vlo_ClsConexion = new ClsConexion();
        try {
            //Se guarda en una variable global la conexion
            vgo_Connection = vlo_ClsConexion.ClsConexionBD();
        } catch (Exception e) {
            throw e;
        }
    }

    public String GuardarEmpleado(ClsEmpleados pvo_Empleado) throws Exception {
        //Variables
        String vlc_Mensaje = "";
        //Se declara un llamado a la base de datos
        CallableStatement vlo_CS;

        try {

            //A la variable de llamado se iguala a la conexion un metodo llamdo prepare call el cual invoca una sentencia sql.
            vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?)}");

            //Se setean los valores de entrada.
            vlo_CS.setInt(1, pvo_Empleado.getVgn_idEmpleado());
            vlo_CS.setString(2, pvo_Empleado.getVgc_cedula());
            vlo_CS.setString(3, pvo_Empleado.getVgc_nombre());
            vlo_CS.setString(4, pvo_Empleado.getVgc_primerApellido());
            vlo_CS.setString(5, pvo_Empleado.getVgc_segundoApellido());
            vlo_CS.setString(6, pvo_Empleado.getVgc_telefono());
            vlo_CS.setString(7, pvo_Empleado.getVgc_correo());
            vlo_CS.setString(8, pvo_Empleado.getVgc_numeroCuenta());
            vlo_CS.setDate(9, pvo_Empleado.getVgf_fechaContratacion());
            vlo_CS.setString(10, vlc_Mensaje);

            //Se establece si un valor es de salida.
            vlo_CS.registerOutParameter(10, Types.VARCHAR);

            //Se ejecuta la sentencia con los parametros ingresados
            vlo_CS.executeUpdate();

            //Se retorna el valor del mensaje devuelto por la base de datos.
            vlc_Mensaje = vlo_CS.getString(10);
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Connection = null;
        }
        return vlc_Mensaje;
    }

    public ResultSet ListaEmpledos(String pvc_ValorFiltrado) throws Exception {
        //variables
        ResultSet vlo_RS;
        String vlc_Sentencia = "";
        Statement vlo_Statement;

        //Inicio
        //Se establese la sentencia sql.
        vlc_Sentencia = "SELECT ID_EMPLEADO,CEDULA,CONCAT(NOMBRE,' ',PRIMER_APELLIDO,' ',SEGUNDO_APELLIDO) AS NOMBRE_COMPLETO,TELEFONO, CORREO FROM EMPLEADOS WHERE NOMBRE LIKE '%"
                + pvc_ValorFiltrado + "%' OR PRIMER_APELLIDO LIKE '%"
                + pvc_ValorFiltrado + "%' OR SEGUNDO_APELLIDO LIKE '%"
                + pvc_ValorFiltrado + "%' AND BORRADO_LOGICO=0";
        try {
            //Se establese la conexión.
            vlo_Statement = vgo_Connection.createStatement();
            //Se guarda el resultado y se ejecuta la sentencia.
            vlo_RS = vlo_Statement.executeQuery(vlc_Sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            vgo_Connection = null;
        }
        return vlo_RS;
    }
}
