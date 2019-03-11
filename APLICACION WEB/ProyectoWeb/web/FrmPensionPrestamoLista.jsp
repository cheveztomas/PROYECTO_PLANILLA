<%-- 
    Document   : FrmPensionPrestamoLista
    Created on : 10/03/2019, 01:17:04 PM
    Author     : Thomas Chevez
--%>

<%@page import="Logica.ClsLogicaEmpleado"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Empleados</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                Lista empleados.
            </h3>
            <form action="FrmPensionPrestamoLista.jsp" method="post" class="container table-bordered form-inline" style="padding: 20px">
                <div class="form-group">
                    <label for="exampleInputEmail1">Buscar:</label>&nbsp;
                    <input type="text" class="form-control" id="txtBuscar" name="txtBuscar" value="" maxlength="50">&nbsp;&nbsp;&nbsp;
                    <button type="submit" id="btn_Buscar" class="btn btn-primary">Buscar</button>&nbsp;&nbsp;
                    <!--<button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmPensionPrestamo.jsp'">Agregar pensi�n o pretamo.</button>-->
                </div>
            </form>
            <form action="FrmPensionPrestamoLista.jsp" method="post">
                <div class='container' style='height: 550px; overflow: auto;'>
                    <table class="container table-bordered tab-content">
                        <tr>
                            <th>Nombre</th>
                            <th>C�dula</th>
                            <th>Tel�fono</th>
                            <th>Correo</th>
                            <th>Seleccionar</th>
                        </tr>
                        <%
                            //Varaibles
                            ResultSet vlo_RS;
                            String vlc_Condicion = "", vlc_msj = "";
                            ClsLogicaEmpleado vlo_LogicaEmpleado = new ClsLogicaEmpleado();

                            //Inicio
                            try {
                                if (request.getParameter("txtBuscar") != null) {
                                    vlc_Condicion = request.getParameter("txtBuscar");
                                }
                                vlo_RS = vlo_LogicaEmpleado.ListaEmpleados(vlc_Condicion);
                                while (vlo_RS.next()) {%>                                
                        <tr>
                            <td>
                                <%
                                    out.print(vlo_RS.getString(3));
                                %>
                            </td>
                            <td>
                                <%
                                    out.print(vlo_RS.getString(2));
                                %>
                            </td>
                            <td>
                                <%
                                    out.print(vlo_RS.getString(4));
                                %>
                            </td>
                            <td>
                                <%
                                    out.print(vlo_RS.getString(5));
                                %>
                            </td>
                            <td>
                                <a href="FrmPensionPrestamo.jsp?idEmpleado=<%=vlo_RS.getString(1)%>">
                                    <img src="image/mas.png" alt=""/>
                                </a>
                            </td>
                        </tr>
                        <%}
                            } catch (Exception e) {
                                vlc_msj = URLEncoder.encode(e.getMessage() + " Error al trartar de cargar la lista de empleados.", "ISO-8859-1");
                            }
                        %>
                    </table>
                </div>
            </form>
        </section>
        <footer class="page-footer font-small bg-secondary" style="margin-top: 50px">

            <!-- Copyright -->
            <div class="footer-copyright text-center py-3">� 2019 Copyright:
                <a class="text-light nav-link" href="https://tomaschevez.com" target="_blank"> Tom�s Ch�vez Elizondo</a>
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
                        <h4 class="modal-title" id="myModalLabel">Informaci�n</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>

                    </div>
                    <div class="modal-body">
                        <%
                            //Variables
                            String vlc_Mensaje = vlc_msj;

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
