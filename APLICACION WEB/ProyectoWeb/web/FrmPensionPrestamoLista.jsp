<%-- 
    Document   : FrmPensionPrestamoLista
    Created on : 10/03/2019, 01:17:04 PM
    Author     : Thomas Chevez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </body>
</html>
