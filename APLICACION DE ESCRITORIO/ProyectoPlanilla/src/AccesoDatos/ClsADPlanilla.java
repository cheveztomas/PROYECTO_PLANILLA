/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Configuracion.ClsConexion;
import Entidades.ClsDeducciones;
import Entidades.ClsDetallePlanillas;
import Entidades.ClsPagos;
import Entidades.ClsPlanilla;
import Entidades.ClsRetorno;
import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

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
        ClsDetallePlanillas vlo_DetallesPlanilla;
        ClsRetorno vlo_RetornoDP = new ClsRetorno();
        ArrayList<ClsPagos> ListaPagos = new ArrayList<ClsPagos>();
        ArrayList<ClsDeducciones> ListaDeducciones = new ArrayList<ClsDeducciones>();
        ClsPagos vlo_Pagos;
        ClsDeducciones vlo_Deduciones;

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
            vlc_SentenciaSQL = "SELECT ID_DEDUCCION_PAGO,DEDUCCION_GENERAL,TIPO,MONTO,DEDUCCION_DETALLADA FROM DEDUCCIONES_PAGOS WHERE ES_DEDUCCION=1";

            //Se obtinen todas las deducciones existentes.
            vlo_Statement = vgo_Connection.createStatement();
            vlo_RSDeducciones = vlo_Statement.executeQuery(vlc_SentenciaSQL);

            //Se establese la nueva sentencia sql
            vlc_SentenciaSQL = "SELECT ID_DEDUCCION_PAGO,DEDUCCION_GENERAL,TIPO,MONTO,DEDUCCION_DETALLADA FROM DEDUCCIONES_PAGOS WHERE ES_DEDUCCION=0";

            //Se obtiene la lista de pagos.
            vlo_Statement = vgo_Connection.createStatement();
            vlo_RSPagos = vlo_Statement.executeQuery(vlc_SentenciaSQL);

            //Este ciclo recorre la tabla que contiene la incformación del empleado.
            while (vlo_RSEmpladosInf.next()) {
                //Variables Auxiliares
                int vln_idEmpleado = vlo_RSEmpladosInf.getInt(1);
                double vln_salarioBase = vlo_RSEmpladosInf.getDouble(2);
                double vln_prestamo = vlo_RSEmpladosInf.getDouble(3);
                double vln_pension = vlo_RSEmpladosInf.getDouble(4);
                int vln_categoriaPuesto = vlo_RSPagos.getInt(5);
                double vln_Desglose = 0;
                vlo_Pagos = new ClsPagos();
                vlo_Deduciones = new ClsDeducciones();
                vlo_DetallesPlanilla = new ClsDetallePlanillas();

                //Se guarda como valor principal el salario base.
                vlo_DetallesPlanilla.setVgn_SalarioBruto(vln_salarioBase);

                //Se recorre la tabla de pagos.
                while (vlo_RSPagos.next()) {
                    //Se verifica que el puesto y la deduccion tengan la misma categoria.
                    if (vlo_RSEmpladosInf.getInt(5) == Integer.parseInt(vlo_RSPagos.getString(5))) {
                        //Se verifica si es un porcentaje lo que se debe calcular.
                        if (vlo_RSPagos.getString(3).equals("POR")) {
                            vln_Desglose = vlo_DetallesPlanilla.getVgn_SalarioBruto() + (vln_salarioBase * (vlo_RSPagos.getDouble(4) / 100));
                            vlo_DetallesPlanilla.setVgn_SalarioBruto(vln_Desglose);
                            vlo_Pagos.setVgn_Porcentaje(vlo_RSPagos.getDouble(4));
                        } else {
                            vln_Desglose = vlo_DetallesPlanilla.getVgn_SalarioBruto() + vlo_RSPagos.getDouble(4);
                            vlo_DetallesPlanilla.setVgn_SalarioBruto(vln_Desglose);
                        }
                        vlo_Pagos.setVgc_Concepto(vlo_RSPagos.getString(2));
                        vlo_Pagos.setVgn_Monto(vln_Desglose);
                        vlo_Pagos.setVgn_id(-1);
                        ListaPagos.add(vlo_Pagos);
                    }
                }

                //Se resta la pensión al usuario
                vlo_DetallesPlanilla.setVgn_SararioNeto(vlo_DetallesPlanilla.getVgn_SalarioBruto() - vlo_RSEmpladosInf.getDouble(4));
                vln_Desglose = 0;
                while (vlo_RSDeducciones.next()) {
                    //Se verifica que el puesto y la deduccion tengan la misma categoria.
                    if (vlo_RSEmpladosInf.getInt(5) == Integer.parseInt(vlo_RSDeducciones.getString(5))) {
                        //Se verifica si es un porcentaje lo que se debe calcular.
                        if (vlo_RSDeducciones.getString(3).equals("POR")) {
                            vln_Desglose = vlo_DetallesPlanilla.getVgn_SararioNeto() - (vlo_DetallesPlanilla.getVgn_SalarioBruto() * (vlo_RSDeducciones.getDouble(4) / 100));
                            vlo_DetallesPlanilla.setVgn_SararioNeto(vln_Desglose);
                            vlo_Deduciones.setVgn_Porcentaje(vlo_RSDeducciones.getDouble(4));
                        } else {
                            vln_Desglose = vlo_DetallesPlanilla.getVgn_SararioNeto() - vlo_RSDeducciones.getDouble(4);
                            vlo_DetallesPlanilla.setVgn_SararioNeto(vln_Desglose);
                        }
                        vlo_Deduciones.setVgc_Concepto(vlo_RSDeducciones.getString(2));
                        vlo_Deduciones.setVgn_Monto(vln_Desglose);
                        vlo_Deduciones.setVgn_id(-1);
                        ListaDeducciones.add(vlo_Deduciones);
                    }
                }

                double restante;
                if (vlo_DetallesPlanilla.getVgn_SalarioBruto() > 817000 && vlo_DetallesPlanilla.getVgn_SalarioBruto() <= 1226000) {
                    vlo_DetallesPlanilla.setVgn_SararioNeto((vlo_DetallesPlanilla.getVgn_SararioNeto() - (vlo_DetallesPlanilla.getVgn_SalarioBruto() * 0.10)));
                } else if (vlo_DetallesPlanilla.getVgn_SalarioBruto() > 1226000) {
                    vlo_DetallesPlanilla.setVgn_SararioNeto(((vlo_DetallesPlanilla.getVgn_SararioNeto() - (817000 * 0.10)) - ((vlo_DetallesPlanilla.getVgn_SalarioBruto() - 817000) * 0.15)));
                }
                vlo_DetallesPlanilla.setVgn_SararioNeto(vlo_DetallesPlanilla.getVgn_SararioNeto() - vln_prestamo);
                vlo_DetallesPlanilla.setVgn_idPlanilla(vlo_Retorno.getVgc_ID());
                vlo_DetallesPlanilla.setVgnIdEmpleado(vln_idEmpleado);
                vlo_DetallesPlanilla.setVgn_AdelantoFinal(vlo_DetallesPlanilla.getVgn_SararioNeto() * 0.60);
                vlo_DetallesPlanilla.setVgn_PrimerAdelanto(vlo_DetallesPlanilla.getVgn_SararioNeto() * 0.40);
                vlo_DetallesPlanilla.setVgn_idDetallePlanilla(-1);

                //Se invoca el procedimiento almacenado que guarda el detalle de planilla.
                vlo_CS = vgo_Connection.prepareCall("{call SP_CUARDAR_DETALLES_PLANILLAS(?,?,?,?,?,?,?,?)}");
                vlo_CS.setInt(1, vlo_DetallesPlanilla.getVgn_idDetallePlanilla());
                vlo_CS.setInt(2, vlo_DetallesPlanilla.getVgn_idPlanilla());
                vlo_CS.setInt(3, vlo_DetallesPlanilla.getVgnIdEmpleado());
                vlo_CS.setDouble(4, vlo_DetallesPlanilla.getVgn_SararioNeto());
                vlo_CS.setDouble(5, vlo_DetallesPlanilla.getVgn_SalarioBruto());
                vlo_CS.setDouble(6, vlo_DetallesPlanilla.getVgn_PrimerAdelanto());
                vlo_CS.setDouble(7, vlo_DetallesPlanilla.getVgn_AdelantoFinal());
                vlo_CS.setString(8, vlo_RetornoDP.getVgc_Mensaje());
                vlo_CS.registerOutParameter(1, Types.INTEGER);
                vlo_CS.registerOutParameter(8, Types.VARCHAR);

                vlo_CS.executeUpdate();

                vlo_RetornoDP.setVgc_ID(vlo_CS.getInt(1));
                vlo_RetornoDP.setVgc_Mensaje(vlo_CS.getString(8));

                //Recorro el arreglo con los pagos y los incerto.
                Iterator<ClsPagos> IteradorPagos = ListaPagos.iterator();
                while (IteradorPagos.hasNext()) {
                    vlo_Pagos = IteradorPagos.next();
                    vlo_CS = vgo_Connection.prepareCall("{SP_GUARDAR_PAGOS(?,?,?,?,?)}");
                    vlo_CS.setInt(1, -1);
                    vlo_CS.setInt(2, vlo_RetornoDP.getVgc_ID());
                    vlo_CS.setString(3, vlo_Pagos.getVgc_Concepto());
                    vlo_CS.setDouble(4, vlo_Pagos.getVgn_Porcentaje());
                    vlo_CS.setDouble(5, vlo_Pagos.getVgn_Monto());

                    vlo_CS.executeUpdate();
                }

                //Recorro el arreglo con los pagos y los incerto.
                Iterator<ClsDeducciones> IteradorDeducciones = ListaDeducciones.iterator();
                while (IteradorDeducciones.hasNext()) {
                    vlo_Deduciones = IteradorDeducciones.next();
                    vlo_CS = vgo_Connection.prepareCall("{SP_GUARDAR_DEDUCCIONES(?,?,?,?,?)}");
                    vlo_CS.setInt(1, -1);
                    vlo_CS.setInt(2, vlo_RetornoDP.getVgc_ID());
                    vlo_CS.setString(3, vlo_Deduciones.getVgc_Concepto());
                    vlo_CS.setDouble(4, vlo_Deduciones.getVgn_Porcentaje());
                    vlo_CS.setDouble(5, vlo_Deduciones.getVgn_Monto());

                    vlo_CS.executeUpdate();
                }
            }
            vgo_Connection.commit();
        } catch (Exception e) {
            vgo_Connection.rollback();
            throw e;
        }
        return vlo_Retorno;
    }
}
