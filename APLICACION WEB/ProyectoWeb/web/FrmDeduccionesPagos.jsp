<%-- 
    Document   : FrmDeduccionesPagos
    Created on : 07/03/2019, 08:38:04 AM
    Author     : tomas
--%>

<%@page import="Logica.ClsLogicaDeduccionesPagos"%>
<%@page import="Entidades.ClsDeduccionesPagos"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Deducciones y Pagos</title>
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
                            <a class="nav-link" href="FrmInfromacionAcademicasEmpleados.jsp">Informaci�n Acad�mica</a>
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
            <h3 style="margin-top: 50px" class="container text-center">
                Deducciones y pagos
            </h3>
            <%
                //Variables
                ClsDeduccionesPagos vlo_DeduccionesPagos;
                ClsLogicaDeduccionesPagos vlo_LogicaDeduccionesPagos = new ClsLogicaDeduccionesPagos();

                //inicio
                if (request.getParameter("idDeduccionPago") != null) {
                    vlo_DeduccionesPagos = vlo_LogicaDeduccionesPagos.ObteDeduccionesPagos(Integer.parseInt(request.getParameter("idDeduccionPago")));
                } else {
                    vlo_DeduccionesPagos = new ClsDeduccionesPagos();
                    vlo_DeduccionesPagos.setVgn_idDeduccionPago(-1);
                }
                vlo_DeduccionesPagos.getVgc_tipo();

            %>
            <form action="GuardarDeduccionesPagos" method="post" class="container table-bordered" style="padding: 20px">
                <div class="form-group">
                    <label for="Concepto">Concepto</label>
                    <input type="text" class="form-control" id="txtconcepto" name="txtconcepto" value="<%=vlo_DeduccionesPagos.getVgc_DeduccionGeneral()%>" maxlength="50" required>
                    <label for="Concepto">Categor�a</label>
                    <input type="number" class="form-control" id="txtcategoria" name="txtcategoria" value="<%=vlo_DeduccionesPagos.getVgc_DeduccionDetallada()%>" maxlength="2" required>
                    <label for="Concepto">Tipo de concepto</label>
                    <%
                        if (vlo_DeduccionesPagos.isVgc_EsDeduccion()) {
                    %>
                    <select name="cmbtipoconcepto" id="cmbtipoconcepto" class="form-control" required>
                        <option value="true" >Deducci�n</option>
                        <option value="false">Pago</option>
                    </select>
                    <%
                    } else {
                    %>
                    <select name="cmbtipoconcepto" id="cmbtipoconcepto" class="form-control" required>
                        <option value="false">Pago</option>
                        <option value="true" >Deducci�n</option>
                    </select>
                    <%
                        }
                    %>
                    <label for="Monto">Monto</label>
                    <input type="number" class="form-control" id="txtmonto" name="txtmonto" value="<%=vlo_DeduccionesPagos.getVgn_Monto()%>" maxlength="10" required>
                    <input type="hidden" id="txt_idDirector" name="txt_idDirector" value="<%=vlo_DeduccionesPagos.getVgn_idDeduccionPago()%>">
                </div>
                <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmDeduccionesPagos.jsp'">Limpiar</button>
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
                            String vlc_Mensaje = "";

                            //Inicio
                            vlc_Mensaje = URLDecoder.decode(request.getParameter("msj"), "ISO-8859-1");
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
