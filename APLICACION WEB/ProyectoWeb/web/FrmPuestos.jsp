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
                String vlc_Mensaje = "";
                ClsPuestos vlo_Puesto;
                ClsLogicaPuestos vlo_LogicaPuestos = new ClsLogicaPuestos();

                if (request.getParameter("idPuesto") != null) {
                    vlo_Puesto = vlo_LogicaPuestos.ObtenerPuesto(Integer.parseInt(request.getParameter("idPuesto")));
                } else {
                    vlo_Puesto = new ClsPuestos();

                }
            %>
            <h3 style="margin-top: 50px" class="container text-center">
                Lista de Puestos
            </h3>
            <form action="GuardarPuesto" method="post" class="container table-bordered" style="padding: 20px">
                <div class="form-group">
                    <label for="Puesto">Puesto</label>
                    <input type="text" class="form-control" id="txtpuesto" name="txtpuesto" value="<%=vlo_Puesto.getVgc_NombrePuesto()%>" maxlength="50" required>
                    <label for="Puesto">Categoría</label>
                    <input type="number" class="form-control" id="txtcategoria" name="txtcategoria" value="<%=vlo_Puesto.getVgn_CategoriaPuesto()%>" maxlength="50" required>
                    <label for="Puesto">Salario Base</label>
                    <input type="number" class="form-control" id="txtsalario" name="txtsalario" value="<%=vlo_Puesto.getVgn_SalarioBase()%>" maxlength="50" required>
                    <input type="hidden" id="txtidPuesto" name="txtidPuesto" value="<%=vlo_Puesto.getVgn_iPuesto()%>">
                </div>
                <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmPuestos.jsp'">Limpiar</button>
                <button type="button" id="btn_Lista" class="btn btn-primary" onclick="location.href = 'FrmListaPuestos.jsp'">Lista</button>
            </form>

        </section>
        <footer class="page-footer font-small bg-secondary" style="margin-top: 50px">

            <!-- Copyright -->
            <div class="footer-copyright text-center py-3">© 2019 Copyright:
                <a class="text-light nav-link" href="https://tomaschevez.com" target="_blank"> Tomás Chévez Elizondo <i class="fas fa-glasses"></i> <i class="fas fa-coffee"></i></a>
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
