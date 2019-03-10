<%-- 
    Document   : FrmListaDeduccionesPagos
    Created on : 06/03/2019, 10:04:42 AM
    Author     : tomas
--%>

<%@page import="java.net.URLDecoder"%>
<%@page import="Logica.ClsLogicaDeduccionesPagos"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Deducciones y Pagos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-3.3.1.js" type="text/javascript"></script>
        <script src="js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <link rel="icon" type="image/png" href="image/analitica (2).png" />
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="index.html"><img src="image/analitica.png" alt=""/> Inicio</a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="FrmListaPuestos.jsp">Puestos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="FrmListaEmpleados.jsp">Empleados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="FrmInfromacionAcademicasEmpleados.jsp">Información Académica</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="FrmListaDeduccionesPagos.jsp">Deducciones y Pagos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="FrmListaPLanilas.jsp">Planilla</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <section>
            <h3 style="margin-top: 50px" class="container text-center">
                Lista deducciones y pagos
            </h3>
            <form action="FrmListaDeduccionesPagos.jsp" method="post" class="container table-bordered form-inline" style="padding: 20px">
                <div class="form-group">
                    <label for="exampleInputEmail1">Buscar:</label>&nbsp;
                    <input type="text" class="form-control" id="txtBuscar" name="txtBuscar" value="" maxlength="50">&nbsp;&nbsp;&nbsp;
                    <button type="submit" id="btn_Buscar" class="btn btn-primary">Buscar</button>&nbsp;&nbsp;
                    <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmDeduccionesPagos.jsp'">Nuevo</button>
                </div>
            </form>
            <form action="FrmListaDeduccionesPagos.jsp" method="post">
                <table class="container table-bordered tab-content">
                    <tr>
                        <th>
                            Concepto
                        </th>
                        <th>
                            Categoría
                        </th>
                        <th>
                            Tipo Concepto
                        </th>
                        <th>
                            Tipo monto
                        </th>
                        <th>
                            Monto
                        </th>
                        <th>
                            Editar
                        </th>
                        <th>
                            Eliminar
                        </th>
                    </tr>
                    <%
                        //Variables
                        ResultSet vlo_RS;
                        ClsLogicaDeduccionesPagos vlo_LogicaDeduccionesPagos = new ClsLogicaDeduccionesPagos();
                        String vlc_Condicion = "";

                        //Inicio
                        try {
                            vlo_RS = vlo_LogicaDeduccionesPagos.ListaPagosDeducciones(vlc_Condicion);
                            while (vlo_RS.next()) {%>                                
                    <tr>
                        <td>
                            <%
                                out.print(vlo_RS.getString(2));
                            %>
                        </td>
                        <td>
                            <%
                                out.print(vlo_RS.getString(3));
                            %>
                        </td>
                        <td>
                            <%
                                if (vlo_RS.getBoolean(4)) {
                                    out.print("Deducción");
                                } else {
                                    out.print("Pago");
                                }
                            %>
                        </td>
                        <td>
                            <%
                                if (vlo_RS.getString(5).equals("POR")) {
                                    out.print("Porcentaje");
                                } else {
                                    out.print("Colones");
                                }
                                //out.print(obj);
                            %>
                        </td>
                        <td>
                            <%
                                out.print(vlo_RS.getDouble(6));
                            %>
                        </td>
                        <td>
                            <a href="FrmDeduccionesPagos.jsp?idDeduccionPago=<%=vlo_RS.getInt(1)%>">
                                <img src="image/editar.png" alt=""/>
                            </a>
                        </td>
                        <td>
                            <a href="EliminarDeduccionesPagos?idDeduccionPago=<%=vlo_RS.getInt(1)%>">
                                <img src="image/basura.png" alt=""/>
                            </a>
                        </td>
                    </tr>
                    <%}
                        } catch (Exception e) {

                        }
                    %>
                </table>
            </form>
        </section>
        <footer class="page-footer font-small bg-secondary" style="margin-top: 50px">

            <!-- Copyright -->
            <div class="footer-copyright text-center py-3">© 2019 Copyright:
                <a class="text-light nav-link" href="https://tomaschevez.com" target="_blank"> Tomás Chévez Elizondo</a>
            </div>
            <!-- Copyright -->

        </footer>
        <%            if (request.getParameter("msj") != null) {
        %>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#miModal').modal('toggle')
            });
        </script>
        <div class="modal fade" id="miModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Información</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>

                    </div>
                    <div class="modal-body">
                        <%
                            //Variables
                            String vlc_Mensaje = "";

                            //Inicio
                            vlc_Mensaje = URLDecoder.decode(request.getParameter("msj"), "ISO-8859-1");
                            out.print(vlc_Mensaje);
                        %>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </body>
</html>
