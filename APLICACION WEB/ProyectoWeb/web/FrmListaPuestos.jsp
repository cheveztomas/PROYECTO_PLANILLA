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
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script src="js/jquery-3.3.1.js" type="text/javascript"></script>
        <script src="js/jquery-ui.min.js" type="text/javascript"></script>
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
            <header>
                <h3 style="margin-top: 50px" class="container text-center">
                    Lista de Puestos
                </h3>
            </header>
            <form action="FrmListaPuestos.jsp" method="post" class="container table-bordered form-inline" style="padding: 20px">
                <div class="form-group">
                    <label for="exampleInputEmail1">Buscar:</label>&nbsp;
                    <input type="text" class="form-control" id="txtBuscar" name="txtBuscar" value="" maxlength="50">&nbsp;&nbsp;&nbsp;
                    <button type="submit" id="btn_Buscar" class="btn btn-primary">Buscar</button>
                </div>
            </form>
            <form action="FrmListaPuestos.jsp" method="post">
                <table class="container table-bordered">
                    <tr>
                        <th>
                            Puesto
                        </th>
                        <th>
                            Categoría
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
                            if (request.getParameter("txtBuscar") != null) {
                                vlo_Condicion = request.getParameter("txtBuscar");
                            }
                            vlo_RS = vlo_LogicaPuestos.ListaPuestos(vlo_Condicion);
                            while (vlo_RS.next()) {%>
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
                    </tr>
                    <%}
                        } catch (Exception e) {
                            vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al cargar la lista de registros.", "ISO-8859-1");
                            response.sendRedirect("FrmListaPuestos.jsp?msj=" + vlc_Mensaje);
                        }
                    %>
                </table>
            </form>
        </section>

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
                            vlc_Mensaje = request.getParameter("msj");
                            vlc_Mensaje = URLDecoder.decode(vlc_Mensaje, "ISO-8859-1");
                            out.print(vlc_Mensaje);
                        %>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </body>
</html>
