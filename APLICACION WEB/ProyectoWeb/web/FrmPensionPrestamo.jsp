<%-- 
    Document   : FrmPensionPrestamo
    Created on : 11/03/2019, 12:51:18 PM
    Author     : Thomas Chevez
--%>

<%@page import="Entidades.ClsPension"%>
<%@page import="Entidades.ClsPrestamo"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Logica.ClsLogicaDeduccionesPagos"%>
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
            <%
                //Variables
                ClsLogicaDeduccionesPagos vlo_LogicaDeduccionesPagos = new ClsLogicaDeduccionesPagos();
                ResultSet vlo_RS;
                String vlc_Nombre = "";
                ClsPrestamo vlo_Prestamo = new ClsPrestamo();
                ClsPension vlo_Pension = new ClsPension();

                //Inicio
                //Se verifica si existe un valor por sesión.
                if (request.getParameter("idEmpleado") != null) {
                    try {
                        vlo_RS = vlo_LogicaDeduccionesPagos.ObtenerPretamoPension(Integer.parseInt(request.getParameter("idEmpleado")));
                        if (vlo_RS.next()) {
                            vlo_Pension.setVgn_idEmpleado(vlo_RS.getInt(1));
                            vlo_Prestamo.setVgn_idEmpleado(vlo_RS.getInt(1));
                            vlo_Pension.setVgn_monto(vlo_RS.getDouble(3));
                            vlo_Prestamo.setVgn_monto(vlo_RS.getDouble(4));
                            vlc_Nombre = vlo_RS.getString(2);
                        }
                    } catch (Exception e) {
                        throw e;
                    }
                }


            %>
            <div id="accordion">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                Empleado
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <div class="container text-center">
                                <h3 class="container text-center">
                                    <%=vlc_Nombre%>
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingTwo">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            Préstamo
                        </button>
                    </h5>
                </div>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <form action="GuardarPrestamo" method="post" class="container table-bordered" style="padding: 20px">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Monto préstamo mensual</label>
                                <input type="number" class="form-control" id="txtmontoprestamo" name="txtmontoprestamo" value="<%=vlo_Prestamo.getVgn_monto()%>" maxlength="50" required>
                                <input type="hidden" id="txtidempleado" name="txtidempleado" value="<%=vlo_Prestamo.getVgn_idEmpleado()%>">
                            </div>
                            <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingThree">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            Pensión
                        </button>
                    </h5>
                </div>
                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                    <div class="card-body">
                        <form action="GuardarPension" method="post" class="container table-bordered" style="padding: 20px">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Monto pensión</label>
                                <input type="number" class="form-control" id="txtmontopension" name="txtmontopension" value="<%=vlo_Pension.getVgn_monto()%>" maxlength="50" required>
                                <input type="hidden" id="txtidempleado" name="txtidempleado" value="<%=vlo_Pension.getVgn_idEmpleado()%>">
                            </div>
                            <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                        </form>
                    </div>
                </div>
            </div>
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
    <%
        if (request.getParameter("form") != null) {
            if (request.getParameter("form").equals("2")) {%>
    <script type="text/javascript">
        $('#collapseTwo').collapse({
            toggle: true
        });
    </script>
    <%} else {
        if (request.getParameter("form").equals("3")) {%>
    <script type="text/javascript">
        $('#collapseThree').collapse({
            toggle: true
        });
    </script>
    <%}
            }
        }
    %>
</body>
</html>
