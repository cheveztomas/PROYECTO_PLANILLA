/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import Configuracion.ClsConexion;

/**
 *
 * @author Thomas Chevez
 */
public class ClsADEmpleados {
    private Connection vgo_Connection;
    
    public ClsADEmpleados(){
        Connection=new ClsConexion();
    }
}
