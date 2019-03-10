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
//import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Iterator;

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
//        ArrayList<ClsPagos> ListaPagos = new ArrayList<ClsPagos>();
//        ArrayList<ClsDeducciones> ListaDeducciones = new ArrayList<ClsDeducciones>();
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
            //vlo_CS.registerOutParameter(2, Types.VARCHAR);
            vlo_CS.executeUpdate();
            vlo_Retorno.setVgc_ID(vlo_CS.getInt(1));
            //vlo_Retorno.setVgc_Mensaje(vlo_CS.getString(2));
            
            if (vlo_Retorno.getVgc_ID() > 0) {
                //Se estable la sentencia sql
                vlc_SentenciaSQL = "SELECT EMPLEADOS.ID_EMPLEADO,SALARIO_BASE,PRESTAMO,PENSION,CATEGORIA_PUESTO FROM EMPLEADOS INNER JOIN EMPLEADOS_PUESTOS ON EMPLEADOS.ID_EMPLEADO = EMPLEADOS_PUESTOS.ID_EMPLEADO INNER JOIN PUESTOS ON EMPLEADOS_PUESTOS.ID_PUESTO=PUESTOS.ID_PUESTO WHERE EMPLEADOS.BORRADO_LOGICO=0";

                //Se lee toda la información de todos los usuarios
                vlo_Statement = vgo_Connection.createStatement();
                vlo_RSEmpladosInf = vlo_Statement.executeQuery(vlc_SentenciaSQL);

                //Este ciclo recorre la tabla que contiene la incformación del empleado.
                int vln_idEmpleado;
                double vln_salarioBase;
                double vln_prestamo;
                double vln_pension;
                int vln_categoriaPuesto;
                double vln_Desglose;

                while (vlo_RSEmpladosInf.next()) {
                    //Variables Auxiliares
                    vln_idEmpleado = vlo_RSEmpladosInf.getInt(1);
                    vln_salarioBase = vlo_RSEmpladosInf.getDouble(2);
                    vln_prestamo = vlo_RSEmpladosInf.getDouble(3);
                    vln_pension = vlo_RSEmpladosInf.getDouble(4);
                    vln_categoriaPuesto = vlo_RSEmpladosInf.getInt(5);
                    vln_Desglose = 0;
                    vlo_Pagos = new ClsPagos();
                    vlo_Deduciones = new ClsDeducciones();
                    vlo_DetallesPlanilla = new ClsDetallePlanillas();

                    //Se guarda como valor principal el salario base.
                    vlo_DetallesPlanilla.setVgn_SalarioBruto(vln_salarioBase);

                    //Se guardan en le entidad los valores necesarios.
                    vlo_DetallesPlanilla.setVgn_idDetallePlanilla(-1);
                    vlo_DetallesPlanilla.setVgn_idPlanilla(vlo_Retorno.getVgc_ID());
                    vlo_DetallesPlanilla.setVgnIdEmpleado(vln_idEmpleado);

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

                    //Se establese la nueva sentencia sql
                    vlc_SentenciaSQL = "SELECT ID_DEDUCCION_PAGO,DEDUCCION_GENERAL,TIPO,MONTO,DEDUCCION_DETALLADA FROM DEDUCCIONES_PAGOS WHERE ES_DEDUCCION=0";

                    //Se obtiene la lista de pagos.
                    vlo_Statement = vgo_Connection.createStatement();
                    vlo_RSPagos = vlo_Statement.executeQuery(vlc_SentenciaSQL);

                    //Se recorre la tabla de pagos.
                    while (vlo_RSPagos.next()) {
                        vlo_Pagos = new ClsPagos();
                        //Se verifica que el puesto y la deduccion tengan la misma categoria.
                        if (Integer.parseInt(vlo_RSPagos.getString(5)) == 0) {
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
                            //ListaPagos.add(vlo_Pagos);
                        } else if (vlo_RSEmpladosInf.getInt(5) == Integer.parseInt(vlo_RSPagos.getString(5))) {
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
                            //ListaPagos.add(vlo_Pagos);
                        }
                        vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_PAGOS(?,?,?,?,?)}");
                        vlo_CS.setInt(1, -1);
                        vlo_CS.setInt(2, vlo_RetornoDP.getVgc_ID());
                        vlo_CS.setString(3, vlo_Pagos.getVgc_Concepto());
                        vlo_CS.setDouble(4, vlo_Pagos.getVgn_Porcentaje());
                        vlo_CS.setDouble(5, vlo_Pagos.getVgn_Monto());

                        vlo_CS.executeUpdate();
                    }

                    //Se guardan en le entidad los valores necesarios.
                    vlo_DetallesPlanilla.setVgn_idDetallePlanilla(vlo_RetornoDP.getVgc_ID());
                    vlo_DetallesPlanilla.setVgn_idPlanilla(vlo_Retorno.getVgc_ID());
                    vlo_DetallesPlanilla.setVgnIdEmpleado(vln_idEmpleado);
                    vlo_DetallesPlanilla.setVgn_AdelantoFinal(vlo_DetallesPlanilla.getVgn_SararioNeto() * 0.60);
                    vlo_DetallesPlanilla.setVgn_PrimerAdelanto(vlo_DetallesPlanilla.getVgn_SararioNeto() * 0.40);

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

                    //Se resta la pensión al usuario
                    vlo_Deduciones = new ClsDeducciones();
                    vlo_Deduciones.setVgc_Concepto("Pensión");
                    vlo_Deduciones.setVgn_Monto(vlo_RSEmpladosInf.getDouble(4));
                    vlo_Deduciones.setVgn_Porcentaje(0);

                    vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_DEDUCCIONES(?,?,?,?,?)}");
                    vlo_CS.setInt(1, -1);
                    vlo_CS.setInt(2, vlo_RetornoDP.getVgc_ID());
                    vlo_CS.setString(3, vlo_Deduciones.getVgc_Concepto());
                    vlo_CS.setDouble(4, vlo_Deduciones.getVgn_Porcentaje());
                    vlo_CS.setDouble(5, vlo_Deduciones.getVgn_Monto());

                    vlo_CS.executeUpdate();

                    vlo_DetallesPlanilla.setVgn_SararioNeto(vlo_DetallesPlanilla.getVgn_SalarioBruto() - vlo_RSEmpladosInf.getDouble(4));

                    vln_Desglose = 0;

                    //Se establese la nueva sentencia sql
                    vlc_SentenciaSQL = "SELECT ID_DEDUCCION_PAGO,DEDUCCION_GENERAL,TIPO,MONTO,DEDUCCION_DETALLADA FROM DEDUCCIONES_PAGOS WHERE ES_DEDUCCION=1";

                    //Se obtinen todas las deducciones existentes.
                    vlo_Statement = vgo_Connection.createStatement();
                    vlo_RSDeducciones = vlo_Statement.executeQuery(vlc_SentenciaSQL);
                    while (vlo_RSDeducciones.next()) {
                        vlo_Deduciones = new ClsDeducciones();
                        //Se verifica que el puesto y la deduccion tengan la misma categoria.
                        if (Integer.parseInt(vlo_RSDeducciones.getString(5)) == 0) {
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
                            //ListaDeducciones.add(vlo_Deduciones);
                        } else {
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
                                //ListaDeducciones.add(vlo_Deduciones);
                            }
                        }
                        vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_DEDUCCIONES(?,?,?,?,?)}");
                        vlo_CS.setInt(1, -1);
                        vlo_CS.setInt(2, vlo_RetornoDP.getVgc_ID());
                        vlo_CS.setString(3, vlo_Deduciones.getVgc_Concepto());
                        vlo_CS.setDouble(4, vlo_Deduciones.getVgn_Porcentaje());
                        vlo_CS.setDouble(5, vlo_Deduciones.getVgn_Monto());

                        vlo_CS.executeUpdate();
                    }

                    //double restante;
                    vlo_Deduciones = new ClsDeducciones();
                    vlo_Deduciones.setVgc_Concepto("Impuesto a la renta.");
                    if (vlo_DetallesPlanilla.getVgn_SalarioBruto() > 817000 && vlo_DetallesPlanilla.getVgn_SalarioBruto() <= 1226000) {
                        vlo_DetallesPlanilla.setVgn_SararioNeto((vlo_DetallesPlanilla.getVgn_SararioNeto() - (vlo_DetallesPlanilla.getVgn_SalarioBruto() * 0.10)));
                        vlo_Deduciones.setVgn_Monto(vlo_DetallesPlanilla.getVgn_SalarioBruto() * 0.10);
                        vlo_Deduciones.setVgn_Porcentaje(10);
                    } else if (vlo_DetallesPlanilla.getVgn_SalarioBruto() > 1226000) {
                        vlo_DetallesPlanilla.setVgn_SararioNeto(((vlo_DetallesPlanilla.getVgn_SararioNeto() - (817000 * 0.10)) - ((vlo_DetallesPlanilla.getVgn_SalarioBruto() - 817000) * 0.15)));
                        vlo_Deduciones.setVgn_Monto(((vlo_DetallesPlanilla.getVgn_SararioNeto() - (817000 * 0.10)) - ((vlo_DetallesPlanilla.getVgn_SalarioBruto() - 817000) * 0.15)));
                        vlo_Deduciones.setVgn_Porcentaje(15);
                    }
                    //Se guarda la información en la base de datos.
                    vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_DEDUCCIONES(?,?,?,?,?)}");
                    vlo_CS.setInt(1, -1);
                    vlo_CS.setInt(2, vlo_RetornoDP.getVgc_ID());
                    vlo_CS.setString(3, vlo_Deduciones.getVgc_Concepto());
                    vlo_CS.setDouble(4, vlo_Deduciones.getVgn_Porcentaje());
                    vlo_CS.setDouble(5, vlo_Deduciones.getVgn_Monto());

                    vlo_CS.executeUpdate();

                    vlo_Deduciones = new ClsDeducciones();
                    vlo_Deduciones.setVgc_Concepto("Prestamo");
                    vlo_Deduciones.setVgn_Monto(vlo_DetallesPlanilla.getVgn_SararioNeto() - vln_prestamo);
                    vlo_Deduciones.setVgn_Porcentaje(0);

                    //Se guarda la información en la base de datos.
                    vlo_CS = vgo_Connection.prepareCall("{call SP_GUARDAR_DEDUCCIONES(?,?,?,?,?)}");
                    vlo_CS.setInt(1, -1);
                    vlo_CS.setInt(2, vlo_RetornoDP.getVgc_ID());
                    vlo_CS.setString(3, vlo_Deduciones.getVgc_Concepto());
                    vlo_CS.setDouble(4, vlo_Deduciones.getVgn_Porcentaje());
                    vlo_CS.setDouble(5, vlo_Deduciones.getVgn_Monto());

                    vlo_CS.executeUpdate();

                    vlo_DetallesPlanilla.setVgn_SararioNeto(vlo_DetallesPlanilla.getVgn_SararioNeto() - vln_prestamo);
                    //Se guardan en le entidad los valores necesarios.
                    vlo_DetallesPlanilla.setVgn_idDetallePlanilla(vlo_RetornoDP.getVgc_ID());
                    vlo_DetallesPlanilla.setVgn_idPlanilla(vlo_Retorno.getVgc_ID());
                    vlo_DetallesPlanilla.setVgnIdEmpleado(vln_idEmpleado);
                    vlo_DetallesPlanilla.setVgn_AdelantoFinal(vlo_DetallesPlanilla.getVgn_SararioNeto() * 0.60);
                    vlo_DetallesPlanilla.setVgn_PrimerAdelanto(vlo_DetallesPlanilla.getVgn_SararioNeto() * 0.40);

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
                }
                vgo_Connection.commit();
            }
            else{
                vgo_Connection.rollback();
                vlo_Retorno.setVgc_Mensaje("La planilla solo se puede calcular una vez al mes.");
            }
        } catch (Exception e) {
            vgo_Connection.rollback();
            throw e;
        }
        vlo_Retorno.setVgc_Mensaje("Se generó la planilla de forma correcta.");
        return vlo_Retorno;
    }

    public ResultSet ListaDetallesPlanilla(int pvn_idPlanilla) throws Exception {
        //Variable
        ResultSet vlo_RS;
        Statement vlo_Statement;
        String vlo_Senetncia = "SELECT EMPLEADOS.ID_EMPLEADO,CONCAT(NOMBRE,' ',PRIMER_APELLIDO,' ',SEGUNDO_APELLIDO)AS NOMBRE_COMPLETO,SALARIO_NETO,SALARIO_BRUTO,PRIMER_ADELANTO,ADELANTO_FINAL_SALARIAL FROM PLANILLAS INNER JOIN DETALLES_PLANILLAS ON PLANILLAS.ID_PLANILLA=DETALLES_PLANILLAS.ID_PLANILLA INNER JOIN EMPLEADOS ON DETALLES_PLANILLAS.ID_EMPLEADO=EMPLEADOS.ID_EMPLEADO WHERE PLANILLAS.ID_PLANILLA='" + pvn_idPlanilla + "'";

        //Inicio
        try {
            vlo_Statement = vgo_Connection.createStatement();
            vlo_RS = vlo_Statement.executeQuery(vlo_Senetncia);
        } catch (Exception e) {
            throw e;
        }
        return vlo_RS;
    }
}
