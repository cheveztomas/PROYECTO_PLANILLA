<%-- 
    Document   : FrmPlanilla
    Created on : 10/03/2019, 08:18:56 PM
    Author     : Thomas Chevez
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="Logica.ClsLogicaPlanilla"%>
<%@page import="Entidades.ClsPlanilla"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                    Planillas
                </h3>
                <%
                    //Variables
                    ResultSet vlo_RS = null;
                    ClsLogicaPlanilla vlo_LogicaPlanilla = new ClsLogicaPlanilla();

                    //Inicio
                    if (request.getParameter("idPlanilla") != null) {
                        try {
                            vlo_RS = vlo_LogicaPlanilla.ListaDetallesPLanilla(Integer.parseInt(request.getParameter("idPlanilla")));
                        } catch (Exception e) {
                        }
                    }
                %>
                <form action="GenerarPlanilla" method="post" class="container table-bordered" style="padding: 20px">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Seleccione la fecha</label>
                        <%
                            if (request.getParameter("idPlanilla") != null) {%>
                        <input type="text" class="form-control" id="datepicker" name="txtfecha" value="<%=request.getParameter("fecha")%>" readonly maxlength="50" required>
                        <input type="hidden" id="txtidPlanilla" name="txtidPlanilla" value="<%=request.getParameter("idPlanilla")%>">
                        <%} else {%>
                        <input type="text" class="form-control" id="datepicker" name="txtfecha" value="" readonly maxlength="50" required>
                        <input type="hidden" id="txtidPlanilla" name="txtidPlanilla" value="-1">
                        <%}
                        %>
                    </div>
                    <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                    <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmPlanilla.jsp'">Limpiar</button>
                    <br>
                    <br>
                    <div class="alert alert-warning">
                        <strong>¡Atención!</strong> Si el empleado no tiene un puesto asignado la planilla de este no será calculada.
                    </div>
                </form>
                <%
                    if (request.getParameter("idPlanilla") != null) {%>
                <div class='container' style='height: 550px; overflow: auto;'>
                    <table class="container table-bordered">
                        <tr>
                            <th>
                                Nombre
                            </th>
                            <th>
                                Salario Neto
                            </th>
                            <th>
                                Salario Bruto
                            </th>
                            <th>
                                Primer Adelanto
                            </th>
                            <th>
                                Pago Final
                            </th>
                        </tr>
                        <%
                            while (vlo_RS.next()) {%>
                        <tr>
                            <td><%
                                out.print(vlo_RS.getString(2));
                                %></td>
                            <td><%
                                out.print(vlo_RS.getDouble(3));
                                %></td>
                            <td><%
                                out.print(vlo_RS.getDouble(4));
                                %></td>
                            <td><%
                                out.print(vlo_RS.getDouble(5));
                                %></td>
                            <td><%
                                out.print(vlo_RS.getDouble(6));
                                %></td>
                        </tr>
                        <%}
                        %>
                    </table>
                </div>
                <%}
                %>
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
            <script>
                $(function () {
                    $("#datepicker").datepicker({
                        showOn: "button",
                        buttonImage: "image/calendario.png",
                        buttonImageOnly: true,
                        dateFormat: "yy-mm-dd",
                        buttonText: "Seleccione una fecha"
                    });
                });
            </script>
        </body>
    </html>
