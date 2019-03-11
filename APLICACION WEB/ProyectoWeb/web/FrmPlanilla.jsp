<%-- 
    Document   : FrmPlanilla
    Created on : 10/03/2019, 08:18:56 PM
    Author     : Thomas Chevez
--%>

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
                <form action="" method="post" class="container table-bordered" style="padding: 20px">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Seleccione la fecha</label>
                        <input type="text" class="form-control" id="datepicker" name="txtfecha" value="<%=%>" readonly maxlength="50" required>
                        <input type="hidden" id="" name="" value="<%=%>">
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
