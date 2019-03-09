<%-- 
    Document   : FrmEmpleados
    Created on : 08/03/2019, 11:33:04 AM
    Author     : Thomas Chevez
--%>

<%@page import="Entidades.ClsInformacionAcademica"%>
<%@page import="Logica.ClsLogicaInformacionAcademica"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="Logica.ClsLogicaEmpleado"%>
<%@page import="Entidades.ClsEmpleados"%>
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
            <div id="accordion">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                Agregar un empleado.
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <form action="GuardarEmpleado" method="post" class="container table-bordered" style="padding: 20px">
                                <%
                                    //Variables
                                    ClsEmpleados vlo_Empleados = new ClsEmpleados();
                                    ClsLogicaEmpleado vlo_LogicaEmpleado = new ClsLogicaEmpleado();
                                    String vlc_msj = "";

                                    //Inicio
                                    if (request.getParameter("idEmpleado") != null) {
                                        try {
                                            vlo_Empleados = vlo_LogicaEmpleado.ObtenerEmpleado(Integer.parseInt(request.getParameter("idEmpleado")));
                                        } catch (Exception e) {
                                            vlc_msj = URLEncoder.encode("Error al trar de cargar al empleado seleccioando.", "ISO-8859-1");
                                            response.sendRedirect("FrmEmpleado.jsp?msj=" + vlc_msj);
                                        }
                                    } else {
                                        vlo_Empleados = new ClsEmpleados();
                                    }
                                %>
                                <div class="form-group">
                                    <label for="Empleado">Nombre</label>
                                    <input type="text" class="form-control" id="txtnombre" name="txtnombre" value="<%=vlo_Empleados.getVgc_nombre()%>" maxlength="50" required>
                                    <label for="">Primer Apellido</label>
                                    <input type="text" class="form-control" id="txtapellido1" name="txtapellido1" value="<%=vlo_Empleados.getVgc_primerApellido()%>" maxlength="50" required>
                                    <label for="">Segundo Apellido</label>
                                    <input type="text" class="form-control" id="txtapellido2" name="txtapellido2" value="<%=vlo_Empleados.getVgc_segundoApellido()%>" maxlength="50" required>
                                    <label for="">Cédula</label>
                                    <input type="text" class="form-control" id="txtcedula" name="txtcedula" value="<%=vlo_Empleados.getVgc_cedula()%>" maxlength="20" required>
                                    <label for="">Teléfono</label>
                                    <input type="number" class="form-control" id="txttelefono" name="txttelefono" value="<%=vlo_Empleados.getVgc_telefono()%>" maxlength="20" required>
                                    <label for="">Correo</label>
                                    <input type="text" class="form-control" id="txtcorreo" name="txtcorreo" value="<%=vlo_Empleados.getVgc_correo()%>" maxlength="50" required>
                                    <label for="">Número de Cuenta</label>
                                    <input type="text" class="form-control" id="txtcuenta" name="txtcuenta" value="<%=vlo_Empleados.getVgc_numeroCuenta()%>" maxlength="50" required>
                                    <label for="">Fecha contratación</label>
                                    <input type="text" class="form-control" id="datepicker" name="txtfecha" value="<%=vlo_Empleados.getVgf_fechaContratacion()%>" maxlength="50" required>
                                    <input type="hidden" id="txtidEmpleado" name="txtidEmpleado" value="<%=vlo_Empleados.getVgn_idEmpleado()%>">
                                </div>
                                <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                                <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmEmpleados.jsp'">Limpiar</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Información académica del empleado.
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                        <div class="card-body">
                            <%
                                if (request.getParameter("idEmpleado") == null) {%>
                            <div class="alert alert-warning">
                                <strong>¡Atención!</strong> Se debe ingresar primero un empleado o seleccionar uno existente para poder gestionar la información académica.
                            </div>
                            <%} else {%>
                            <%
                                //Variables
                                ClsLogicaInformacionAcademica vlo_LogicaInformacionAcademica = new ClsLogicaInformacionAcademica();
                                ClsInformacionAcademica vlo_InformacionAcademica = new ClsInformacionAcademica();

                                //Inicio
                                if (request.getParameter("idInformacionAcademica") != null) {
                                    try {
                                        vlo_InformacionAcademica = vlo_LogicaInformacionAcademica.ObtenerInformacionAcademica(Integer.parseInt("idInformacionAcademica"));
                                    } catch (Exception e) {
                                        throw e;
                                    }

                                }
                            %>
                            <form action="GuardarInformacionAcademica" method="post" class="container table-bordered" style="padding: 20px">
                                <div class="form-group">
                                    <label for="Especialidad">Especialidad</label>
                                    <input type="text" class="form-control" id="txtespecialidad" name="txtespecialidad" value="<%=vlo_InformacionAcademica.getVgc_especialidad()%>" maxlength="100" required>
                                    <label for="Grado">Grado</label>
                                    <%
                                        if (vlo_InformacionAcademica.getVgc_Grado().equals("OTRO")) {
                                    %>
                                    <select id="txtgrado" name="txtgrado" class="form-control" required>
                                        <option value="BACHILLER" >Bachiller</option>
                                        <option value="LICENCIADO">Licenciatura</option>
                                        <option value="MAESTRIA">Maestría</option>
                                        <option value="DOCTORADO">Doctorado</option>
                                        <option value="TECNICO">Técnico</option>
                                        <option value="OTRO" selected="true">Otro</option>
                                    </select>
                                    <%} else {
                                        if (vlo_InformacionAcademica.getVgc_Grado().equals("TECNICO")) {
                                    %>
                                    <select id="txtgrado" name="txtgrado" class="form-control" required>
                                        <option value="BACHILLER" >Bachiller</option>
                                        <option value="LICENCIADO">Licenciatura</option>
                                        <option value="MAESTRIA">Maestría</option>
                                        <option value="DOCTORADO">Doctorado</option>
                                        <option value="TECNICO" selected="true">Técnico</option>
                                        <option value="OTRO">Otro</option>
                                    </select>
                                    <%
                                    } else {
                                        if (vlo_InformacionAcademica.getVgc_Grado().equals("DOCTORADO")) {
                                    %>
                                    <select id="txtgrado" name="txtgrado" class="form-control" required>
                                        <option value="BACHILLER" >Bachiller</option>
                                        <option value="LICENCIADO">Licenciatura</option>
                                        <option value="MAESTRIA">Maestría</option>
                                        <option value="DOCTORADO" selected="true">Doctorado</option>
                                        <option value="TECNICO">Técnico</option>
                                        <option value="OTRO">Otro</option>
                                    </select>
                                    <%
                                    } else {
                                        if (vlo_InformacionAcademica.getVgc_Grado().equals("MAESTRIA")) {
                                    %>
                                    <select id="txtgrado" name="txtgrado" class="form-control" required>
                                        <option value="BACHILLER" >Bachiller</option>
                                        <option value="LICENCIADO">Licenciatura</option>
                                        <option value="MAESTRIA" selected="true">Maestría</option>
                                        <option value="DOCTORADO">Doctorado</option>
                                        <option value="TECNICO">Técnico</option>
                                        <option value="OTRO">Otro</option>
                                    </select>
                                    <%
                                    } else {
                                        if (vlo_InformacionAcademica.getVgc_Grado().equals("LICENCIADO")) {
                                    %>
                                    <select id="txtgrado" name="txtgrado" class="form-control" required>
                                        <option value="BACHILLER" >Bachiller</option>
                                        <option value="LICENCIADO" selected="true">Licenciatura</option>
                                        <option value="MAESTRIA">Maestría</option>
                                        <option value="DOCTORADO">Doctorado</option>
                                        <option value="TECNICO">Técnico</option>
                                        <option value="OTRO">Otro</option>
                                    </select>
                                    <%
                                    } else {%>
                                    <select id="txtgrado" name="txtgrado" class="form-control" required>
                                        <option value="BACHILLER" selected="true">Bachiller</option>
                                        <option value="LICENCIADO">Licenciatura</option>
                                        <option value="MAESTRIA">Maestría</option>
                                        <option value="DOCTORADO">Doctorado</option>
                                        <option value="TECNICO">Técnico</option>
                                        <option value="OTRO">Otro</option>
                                    </select>
                                    <%
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    %>
                                    <label for="Información">Información</label>
                                    <input type="text" class="form-control" name="txtinformacion" id="txtinformacion" value="<%=vlo_InformacionAcademica.getVgc_informacion()%>" maxlength="50" required>
                                    <input type="hidden" id="idInfA" name="idInfA" value="<%=vlo_InformacionAcademica.getVgn_idInformacionA()%>">
                                    <input type="hidden" id="txtidEmpleado" name="txtidEmpleado" value="<%=vlo_Empleados.getVgn_idEmpleado()%>">
                                </div>
                                <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                                <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>'">Limpiar</button>

                            </form>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingThree">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                Puesto del empleado.
                            </button>
                        </h5>
                    </div>
                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                        <div class="card-body">
                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
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
        <%
            if (request.getParameter("msj") != null) {
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
                </div>
            </div>
        </div>
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
