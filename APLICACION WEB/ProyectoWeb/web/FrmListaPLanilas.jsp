<%-- 
    Document   : FrmListaPLanilas
    Created on : 06/03/2019, 10:05:18 AM
    Author     : tomas
--%>

<%@page import="Logica.ClsLogicaPlanilla"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Planilla</title>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
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
                Lista Planillas
            </h3>
            <form action="FrmListaPLanilas.jsp" method="post" class="form-inline container table-bordered" style="padding: 20px">
                <div class="form-group mb-2">
                    <select name="txtMes" class="form-control" id="txtMes">
                        <option value="">Seleccionar Mes</option>
                        <option value="1">Enero</option>
                        <option value="2">Febrero</option>
                        <option value="3">Marzo</option>
                        <option value="4">Abril</option>
                        <option value="5">Mayo</option>
                        <option value="6">Junio</option>
                        <option value="7">Julio</option>
                        <option value="8">Agosto</option>
                        <option value="9">Septiembre</option>
                        <option value="10">Octubre</option>
                        <option value="11">Noviembre</option>
                        <option value="12">Diciembre</option>
                    </select>
                </div>
                <div class="form-group mx-sm-3 mb-2">
                    <input type="text" class="form-control" name="txtAnio" id="txtAnio" placeholder="Año" minlength="4" maxlength="4">
                </div>
                <button type="submit" class="btn btn-primary mb-2">Buscar</button>&nbsp;&nbsp;
                <button type="button" id="btn_Nuevo" class="btn btn-primary mb-2" onclick="location.href = 'FrmPlanilla.jsp'">Nuevo</button>
            </form>
            <div class='container' style='height: 550px; overflow: auto;'>
                <table class="container table-bordered">
                    <tr>
                        <th>
                            Fecha
                        </th>
                        <th>
                            Ver más
                        </th>
                    </tr>
                    <%
                        //Variables
                        ResultSet vlo_RS;
                        ClsLogicaPlanilla vlo_LogicaPlanilla = new ClsLogicaPlanilla();
                        String vlc_Mes = "", vlc_Anio = "";

                        //Inicio
                        //Según la selección del usuario al seleccionar el año y el mes se carga la lista.
                        if (request.getParameter("txtAnio") != null) {
                            vlc_Anio = request.getParameter("txtAnio");
                        }
                        if (request.getParameter("txtMes") != null) {
                            vlc_Mes = request.getParameter("txtMes");
                        }
                        try {
                            vlo_RS = vlo_LogicaPlanilla.ListaPlanilla(vlc_Mes, vlc_Anio);
                            while (vlo_RS.next()) {
                    //Se le muestra al usuario la lista de planilla.
                    %>                                
                    <tr>
                        <td>
                            <%=vlo_RS.getDate(2)%>
                        </td>
                        <td>
                            <a href="FrmPlanilla.jsp?idPlanilla=<%
                                out.print(vlo_RS.getInt(1));
                               %>&fecha=<%=vlo_RS.getDate(2)%>">
                                <img src="image/mas.png" alt=""/>
                            </a>
                        </td>
                    </tr>
                    <%}
                        } catch (Exception e) {
                        }
                    %>
                </table>
            </div>
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
                        <%= new String(request.getParameter("msj").getBytes("ISO-8859-1"), "UTF-8")%>
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
