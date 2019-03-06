<%-- 
    Document   : FrmPuestos
    Created on : 06/03/2019, 11:42:40 AM
    Author     : tomas
--%>

<%@page import="Logica.ClsLogicaPuestos"%>
<%@page import="Entidades.ClsPuestos"%>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Puestos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
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
            <%
                //Variables
                String vlc_Mensaje = "";
                ClsPuestos vlo_Puesto;
                ClsLogicaPuestos vlo_LogicaPuestos = new ClsLogicaPuestos();

                if (request.getParameter("idPuesto") != null) {
                    vlo_Puesto = vlo_LogicaPuestos.ObtenerPuesto(Integer.parseInt(request.getParameter("idPuesto")));
                } else {
                    vlo_Puesto = new ClsPuestos();

                }
            %>
            <form action="GuardarPuesto" method="post" class="container table-bordered" style="padding: 20px">
                <div class="form-group">
                    <label for="exampleInputEmail1">...</label>
                    <input type="text" class="form-control" id="txtnombre" name="txtnombre" value="<%=vlo_Puesto.getVgc_NombrePuesto()%>" maxlength="50" required>
                    <input type="hidden" id="txtidPuesto" name="txtidPuesto" value="<%=vlo_Puesto.getVgn_iPuesto()%>">
                </div>
                <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = ''">Limpiar</button>
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
