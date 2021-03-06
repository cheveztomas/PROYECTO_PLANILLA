<%-- 
    Document   : FrmListaPuestos
    Created on : 06/03/2019, 09:23:34 AM
    Author     : tomas
--%>

<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URL"%>
<%@page import="Logica.ClsLogicaPuestos"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Puestos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="image/analitica (2).png" />
        <script src="js/jquery-3.3.1.js" type="text/javascript"></script>
        <script src="js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>


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
                            <a class="nav-link" href="FrmListaPLanilas.jsp">Planilla</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Deducciones y Pagos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="FrmListaDeduccionesPagos.jsp">Generales</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="FrmPensionPrestamoLista.jsp">Personales</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <section>
            <h3 style="margin-top: 50px" class="container text-center">
                Lista de Puestos
            </h3>
            <form action="FrmListaPuestos.jsp" method="post" class="container table-bordered form-inline" style="padding: 20px">
                <div class="form-group">
                    <label for="exampleInputEmail1">Buscar:</label>&nbsp;
                    <input type="text" class="form-control" id="txtBuscar" name="txtBuscar" value="" maxlength="50">&nbsp;&nbsp;&nbsp;
                    <button type="submit" id="btn_Buscar" class="btn btn-primary">Buscar</button>&nbsp;&nbsp;
                    <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmPuestos.jsp'">Nuevo</button>
                </div>
            </form>
            <form action="FrmListaPuestos.jsp" method="post">
                <div class="container" style='height: 550px; overflow: auto;'>
                    <table class="container table-bordered">
                        <tr>
                            <th>
                                Puesto
                            </th>
                            <th>
                                Categor�a
                            </th>
                            <th>
                                Salario Base
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
                            ClsLogicaPuestos vlo_LogicaPuestos = new ClsLogicaPuestos();
                            String vlo_Condicion = "";
                            String vlc_Mensaje = "";

                            //Inicio
                            try {
                                //Se verifica si existe la condici�n de busqueda.
                                if (request.getParameter("txtBuscar") != null) {
                                    vlo_Condicion = request.getParameter("txtBuscar");
                                }
                                //Con la condi�n de busqueda se caraga la lista de puestos.
                                vlo_RS = vlo_LogicaPuestos.ListaPuestos(vlo_Condicion);
                                while (vlo_RS.next()) {
                        //Se le mustra al usuario la lista de puestos.
                        %>
                        <tr>
                            <td>
                                <%=vlo_RS.getString(2)%>
                            </td>
                            <td>
                                <%=vlo_RS.getInt(3)%>
                            </td>
                            <td>
                                <%=vlo_RS.getDouble(4)%>
                            </td>
                            <td>
                                <a href="FrmPuestos.jsp?idPuesto=<%=vlo_RS.getInt(1)%>">
                                    <img src="image/editar.png" alt=""/>
                                </a>
                            </td>
                            <td>
                                <a href="EliminarPuesto?idPuesto=<%=vlo_RS.getInt(1)%>">
                                    <img src="image/basura.png" alt=""/>
                                </a>
                            </td>
                        </tr>
                        <%}
                            } catch (Exception e) {
                                vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al cargar la lista de registros.", "ISO-8859-1");
                                response.sendRedirect("FrmListaPuestos.jsp?msj=" + vlc_Mensaje);
                            }
                        %>
                    </table>
                </div>
            </form>
        </section>
        <footer class="page-footer font-small bg-secondary" style="margin-top: 50px">

            <!-- Copyright -->
            <div class="footer-copyright text-center py-3">� 2019 Copyright:
                <a class="text-light nav-link" href="https://tomaschevez.com" target="_blank"> Tom�s Ch�vez Elizondo <i class="fas fa-glasses"></i></a>
            </div>
            <!-- Copyright -->

        </footer>
        <%            if (request.getParameter("msj") != null) {
        %>
        <div class="modal fade" id="miModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Informaci�n</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>

                    </div>
                    <div class="modal-body">
                        <%
                            vlc_Mensaje = request.getParameter("msj");
                            vlc_Mensaje = URLDecoder.decode(vlc_Mensaje, "ISO-8859-1");
                            out.print(vlc_Mensaje);
                        %>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#miModal').modal('toggle')
            });
        </script>
        <%
            }
        %>
    </body>
</html>
