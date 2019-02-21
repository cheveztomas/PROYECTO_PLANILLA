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
import java.sql.Types;

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

    public ClsRetorno GuardarPuesto(ClsPuestos pvo_Puestos) throws Exception {
        //Variables
        ClsRetorno vlo_Retorno = new ClsRetorno();
        CallableStatement vlo_CS;

        //Inicio
        try {
            //Preparo el llamado del procedimiento almacenado.
            vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_PUESTOS(?,?,?,?,?)}");

            //Se setean los valores de la entidad.
            vlo_CS.setInt(1, pvo_Puestos.getVgn_iPuesto());
            vlo_CS.setString(2, pvo_Puestos.getVgc_NombrePuesto());
            vlo_CS.setInt(3, pvo_Puestos.getVgn_CategoriaPuesto());
            vlo_CS.setDouble(4, pvo_Puestos.getVgn_SalarioBase());
            vlo_CS.setString(5, vlo_Retorno.getVgc_Mensaje());

            //Se establese los parametros de salida.
            vlo_CS.registerOutParameter(1, Types.INTEGER);
            vlo_CS.registerOutParameter(5, Types.VARCHAR);

            //Se ejecuta el procedimento.
            vlo_CS.executeUpdate();
            
            //Se almacenan los datos valores de salida en el objeto de retorno
            
        } catch (Exception e) {
            throw e;
        }
        return vlo_Retorno;
    }
}
