/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Configuracion.ClsConexion;
import Entidades.ClsDeduccionesPagos;
import java.sql.Connection;

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

    public String GuardarDeduccionesPagos(ClsDeduccionesPagos pvo_DeduccionesPagos) {
        //Variables
        String vlc_Mensaje = "";
        
        //Inicio
        
        return vlc_Mensaje;
    }
}
