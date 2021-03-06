<%-- 
    Document   : FrmInfromacionAcademicasEmpleados
    Created on : 06/03/2019, 10:04:08 AM
    Author     : tomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Información Académica</title>
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
        </nav>
    </header>
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
                    <%= new String(request.getParameter("msj").getBytes("ISO-8859-1"), "UTF-8")%>
                </div>
            </div>
        </div>
    </div>
    <%
        }
    %>
</body>
</html>
