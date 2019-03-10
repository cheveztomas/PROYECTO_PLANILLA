<%-- 
    Document   : FrmEmpleados
    Created on : 08/03/2019, 11:33:04 AM
    Author     : Thomas Chevez
--%>

<%@page import="Entidades.ClsPuestos"%>
<%@page import="Logica.ClsLogicaPuestos"%>
<%@page import="java.sql.ResultSet"%>
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
                            <h3 style="margin-top: 10px" class="container text-center">
                                Empleados
                            </h3>
                            <form action="GuardarEmpleado" method="post" class="container table-bordered" style="padding: 20px">
                                <%
                                    //Variables
                                    ClsEmpleados vlo_Empleados = new ClsEmpleados();
                                    ClsLogicaEmpleado vlo_LogicaEmpleado = new ClsLogicaEmpleado();
                                    String vlc_msj = "";

                                    //Inicio
                                    //Se verifica si en seición existe un valor de empleado.
                                    if (request.getParameter("idEmpleado") != null) {
                                        try {
                                            //Si existe se carga el empleado que viene por sesión
                                            vlo_Empleados = vlo_LogicaEmpleado.ObtenerEmpleado(Integer.parseInt(request.getParameter("idEmpleado")));
                                        } catch (Exception e) {
                                            vlc_msj = URLEncoder.encode("Error al trar de cargar al empleado seleccioando.", "ISO-8859-1");
                                            response.sendRedirect("FrmEmpleado.jsp?msj=" + vlc_msj);
                                        }
                                    } else {
                                        //En caso contrario se intancia el empleado para incializar variables.
                                        vlo_Empleados = new ClsEmpleados();
                                    }

                                    //Se cargan los valores de la instancia o de la entidad emppleado en el formulario web.
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
                                    <input type="text" class="form-control" id="datepicker" name="txtfecha" value="<%=vlo_Empleados.getVgf_fechaContratacion()%>" readonly maxlength="50" required>
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
                                //Se verifica si la variable por sesión del id empleado tiene un valor.
                                //En caso de que sea nulo se muestra un mensaje al usuario para indicar que debe ingresar o seleccionar empleado.
                                if (request.getParameter("idEmpleado") == null) {%>
                            <div class="alert alert-warning">
                                <strong>¡Atención!</strong> Se debe ingresar primero un empleado o seleccionar uno existente para poder gestionar la información académica.
                            </div>
                            <%} else {%>
                            <%
                                //En caso contrario se carga la lista de información academica y se muestra un formulario al usuario si desea modificar o agragar información académica al empleado.
                                //Variables
                                ClsLogicaInformacionAcademica vlo_LogicaInformacionAcademica = new ClsLogicaInformacionAcademica();
                                ClsInformacionAcademica vlo_InformacionAcademica = new ClsInformacionAcademica();

                                //Inicio
                                //Se verifica si por sesión existe en valor con el identificador de la especialidad académica.
                                if (request.getParameter("idInfA") != null) {
                                    try {
                                        //Si es distinta de nula se carga la información académica.
                                        vlo_InformacionAcademica = vlo_LogicaInformacionAcademica.ObtenerInformacionAcademica(Integer.parseInt(request.getParameter("idInfA")));
                                    } catch (Exception e) {
                                        throw e;
                                    }

                                }
                                //Se le muestra al usuario ya sea la enitidad instanciada inicializada o la entidad cargada de la base de datos.
%>
                            <h3 style="margin-top: 10px" class="container text-center">
                                Información Académica
                            </h3>
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
                                <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>&form=2'">Limpiar</button>

                            </form>
                            <h3 style="margin-top: 20px" class="container text-center">
                                Lista Información Académica
                            </h3>
                            <form action="FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>&form=2" method="post" class="container table-bordered form-inline" style="padding: 20px">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Buscar:</label>&nbsp;
                                    <input type="text" class="form-control" id="txtBuscarIA" name="txtBuscarIA" value="" maxlength="50">&nbsp;&nbsp;&nbsp;
                                    <button type="submit" id="btn_BuscarIA" class="btn btn-primary">Buscar</button>
                                </div>
                            </form>
                            <form action="FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>&form=2" method="post">
                                <div class='container' style='height: 200px; overflow: auto;'>
                                    <table class="table-bordered container">
                                        <tr>
                                            <th>Especialidad</th>
                                            <th>Grado</th>
                                            <th>Editar</th>
                                            <th>Eliminar</th>
                                        </tr>
                                        <%
                                            //Variables
                                            ResultSet vlo_RSIA;
                                            String vlc_CondicionInf = "";

                                            //Inicio
                                            //Se verifica si existe un marametro de busqueda para filtrar la información
                                            if (request.getParameter("txtBuscarIA") != null) {
                                                vlc_CondicionInf = request.getParameter("txtBuscarIA");
                                            }
                                            try {
                                                //Se carga la información de la base de datos en la pantalla del usuario.
                                                vlo_RSIA = vlo_LogicaInformacionAcademica.ListaInformacionAcademica(vlc_CondicionInf, Integer.parseInt(request.getParameter("idEmpleado")));
                                                while (vlo_RSIA.next()) {%>                                                
                                        <tr>
                                            <td><%=vlo_RSIA.getString(4)%></td>
                                            <td><%=vlo_RSIA.getString(3)%></td>
                                            <td>
                                                <a href="FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>&idInfA=<%=vlo_RSIA.getInt(1)%>&form=2">
                                                    <img src="image/editar.png" alt=""/>
                                                </a>
                                            </td>
                                            <td>
                                                <a href="EliminarInformacionAcademica?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>&idInfA=<%=vlo_RSIA.getInt(1)%>">
                                                    <img src="image/basura.png" alt=""/>
                                                </a>
                                            </td>
                                        </tr>
                                        <%}
                                            } catch (Exception e) {
                                                throw e;
                                            }
                                        %>
                                    </table>
                                </div>
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
                            <%
                                //Variables
                                ResultSet vlo_RSIAP = null;
                                ClsLogicaInformacionAcademica vlo_LogicaInformacionAcademica = new ClsLogicaInformacionAcademica();

                                //Inicio
                                try {
                                    //Se carga la información acádemica del usuario para que seleccione cual va ser la especialidad por la cual está siendo contratrado.
                                    vlo_RSIAP = vlo_LogicaInformacionAcademica.ListaInformacionAcademica("", vlo_Empleados.getVgn_idEmpleado());
                                } catch (Exception e) {
                                    throw e;
                                }
                                //Se verifica si el resultado del resultset es nulo y se le muestra un mensaje al usuario o se abre el acordeón con el formulario de puestos-
                                if (vlo_RSIAP == null) {%>
                            <div class="alert alert-warning">
                                <strong>¡Atención!</strong> No se puede tener un puesto si no existe una especialidad académica.
                            </div>
                            <%} else {
                                if (!vlo_RSIAP.next()) {%>
                            <div class="alert alert-warning">
                                <strong>¡Atención!</strong> No se puede tener un puesto si no existe una especialidad académica.
                            </div>  
                            <%} else {%>
                            <h3 style="margin-top: 20px" class="container text-center">
                                Puesto Empleado
                            </h3>
                            <%
                                //En caso de que la lista sea distinta de vacía.
                                //Variables
                                ClsLogicaPuestos vlo_LogicaPuestos = new ClsLogicaPuestos();
                                ClsPuestos vlo_Puesto = new ClsPuestos();
                                ClsInformacionAcademica vlo_InformacionAcademica = new ClsInformacionAcademica();

                                //Inicio
                                try {
                                    //Se caraga el puesto del usuario.
                                    vlo_Puesto = vlo_LogicaPuestos.ObtenerPuestosIdEmpleado(Integer.parseInt(request.getParameter("idEmpleado")));
                                } catch (Exception e) {
                                }

                                if (request.getParameter("idInfAP") != null) {
                                    try {
                                        //Se carga la información académica del usuario.
                                        vlo_InformacionAcademica = vlo_LogicaInformacionAcademica.ObtenerInformacionAcademica(Integer.parseInt(request.getParameter("idInfAP")));
                                    } catch (Exception e) {
                                        throw e;
                                    }
                                }

                                //Se verifica si el puesto cargado tiene o no información
                                if (vlo_Puesto.getVgc_NombrePuesto().equals("")) {
                                    //En caso de que no tenga se carga un puesto si el id del puesto es distinto de vacío.
                                    if (request.getParameter("idPuesto") != null) {
                                        vlo_Puesto = vlo_LogicaPuestos.ObtenerPuesto(Integer.parseInt(request.getParameter("idPuesto")));
                                    }
                                } else {
                                    if (request.getParameter("idPuesto") != null) {
                                        vlo_Puesto = vlo_LogicaPuestos.ObtenerPuesto(Integer.parseInt(request.getParameter("idPuesto")));
                                    }
                                }
                            %>
                            <form action="GuardarEmpleadoPuesto?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>" method="post" class="container table-bordered" style="padding: 20px">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Puesto empleado</label>
                                    <input type="text" class="form-control" id="txtpuesto" name="txtpuesto" value="<%=vlo_Puesto.getVgc_NombrePuesto()%>" maxlength="50" readonly required>
                                    <input type="hidden" id="txtidpuesto" name="txtidpuesto" value="<%=vlo_Puesto.getVgn_iPuesto()%>" required>
                                    <br>
                                    <button type="button" id="btn_AgregarPuesto" class="btn btn-primary" onclick="location.href = 'FrmEmpleados.jsp?modalPE=1&idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>&form=3<%
                                        //Se verifica si el ha seleccionado una información academica para agregar la avraible a la sesión.
                                        if (request.getParameter("idInfAP") != null) {

                                            out.print("&idInfAP=" + request.getParameter("idInfAP"));
                                        }
                                            %>'">Seleccione un puesto</button>
                                    <br>
                                    <%
                                        //Se verifica si en sesión tranto el puesto está vacio para ocultar o mostrar controles.
                                        if (vlo_Puesto.getVgc_NombrePuesto().equals("") || request.getParameter("idPuesto") != null) {%>
                                    <br>
                                    <label for="exampleInputEmail1">Especialidad</label>
                                    <input type="text" class="form-control" id="txtespecialidadAP" name="txtespecialidadAP" value="<%=vlo_InformacionAcademica.getVgc_especialidad()%>" maxlength="50" readonly required>
                                    <br>
                                    <button type="button" id="btn_SelIA" class="btn btn-primary" onclick="location.href = 'FrmEmpleados.jsp?modalIA=1&idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>&form=3<%
                                        //Se verifica si el puesto se encuentra vacio para agregar o elimianar de la sesión el valor del puesto.
                                        if (request.getParameter("idPuesto") != null) {
                                            out.print("&idPuesto=" + request.getParameter("idPuesto"));
                                        }
                                            %>'">Seleccione una especialidad</button>
                                    <%
                                        if (request.getParameter("idInfAP") != null) {%>
                                    <input type="hidden" id="txtidInfAP" name="txtidInfAP" value="<%=vlo_InformacionAcademica.getVgn_idInformacionA()%>" required>
                                    <br>
                                    <%}
                                    %>
                                    <%}
                                    %>
                                </div>
                                <%
                                    if (request.getParameter("idInfAP") != null && request.getParameter("idPuesto") != null) {%>
                                <button type="submit" id="btn_Guardar" class="btn btn-primary">Guardar</button>
                                <%} else {%>
                                <div class="alert alert-danger">
                                    <strong>¡Atención!</strong> Solo se puede guardar un puesto cuando: seleccione el puesto que desea asignar al empleado, y la especialidad por la que está siendo contratado.
                                </div>
                                <%}
                                %>
                            </form>

                            <%
                                //Se verifica si la variable se encuentra vacía para cargar modal de información académica.
                                if (request.getParameter("modalIA") != null) {
                            %>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $('#ModalInformacionAcademica').modal('toggle')
                                });
                            </script>
                            <div class="modal fade" id="ModalInformacionAcademica" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title" id="myModalLabel">Lista de especialidades</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">

                                            <div class='container' style='height: 200px; overflow: auto;'>
                                                <div class='container' style='height: 300px; overflow: auto;'>
                                                    <table class="container table-bordered">
                                                        <tr>
                                                            <th>
                                                                Especialidad
                                                            </th>
                                                            <th>
                                                                Grado
                                                            </th>
                                                            <th>
                                                                Seleccionar
                                                            </th>
                                                        </tr>
                                                        <%
                                                            try {
                                                                //Se carga en el modal al lista de especialidades.
                                                                vlo_RSIAP = vlo_LogicaInformacionAcademica.ListaInformacionAcademica("", Integer.parseInt(request.getParameter("idEmpleado")));
                                                            } catch (Exception e) {
                                                                throw e;
                                                            }
                                                            while (vlo_RSIAP.next()) {%>                                                            
                                                        <tr>
                                                            <td><%=vlo_RSIAP.getString(4)%></td>
                                                            <td><%=vlo_RSIAP.getString(3)%></td>
                                                            <td>
                                                                <a href="FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%>&idInfAP=<%=vlo_RSIAP.getInt(1)%>&form=3<%
                                                                    if (request.getParameter("idPuesto") != null) {
                                                                        out.print("&idPuesto=" + request.getParameter("idPuesto"));
                                                                    }
                                                                   %>">
                                                                    <img src="image/comprobado.png" alt=""/>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <%}
                                                        %>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%                                }
                            %>
                            <%            if (request.getParameter("modalPE") != null) {
                            %>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $('#ModalPuestoEmpleado').modal('toggle')
                                });
                            </script>
                            <div class="modal fade" id="ModalPuestoEmpleado" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
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

                                                if (request.getParameter("idPuesto") != null) {
                                                    vlo_Puesto = vlo_LogicaPuestos.ObtenerPuesto(Integer.parseInt(request.getParameter("idPuesto")));
                                                } else {
                                                    vlo_Puesto = new ClsPuestos();

                                                }
                                            %>
                                            <h3 style="margin-top: 50px" class="container text-center">
                                                Lista de Puestos
                                            </h3>
                                            <form action="GuardarPuesto?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%><%
                                                if (request.getParameter("idInfAP") != null) {
                                                    out.print("&idInfAP=" + request.getParameter("idInfAP"));
                                                }
                                                  %>" method="post" class="container table-bordered" style="padding: 20px">
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
                                                <button type="button" id="btn_Nuevo" class="btn btn-primary" onclick="location.href = 'FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%><%
                                                    if (request.getParameter("idInfAP") != null) {
                                                        out.print("&idInfAP=" + request.getParameter("idInfAP"));
                                                    }
                                                        %>&modalPE=1&form=3'">Limpiar</button>
                                            </form>
                                            <form action="FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%><%
                                                if (request.getParameter("idInfAP") != null) {
                                                    out.print("&idInfAP=" + request.getParameter("idInfAP"));
                                                }
                                                  %>&modalPE=1&form=3" method="post" class="container table-bordered form-inline" style="padding: 20px">
                                                <div class="form-group">
                                                    <label for="exampleInputEmail1">Buscar:</label>&nbsp;
                                                    <input type="text" class="form-control" id="txtBuscarPuesto" name="txtBuscar" value="" maxlength="50">&nbsp;&nbsp;&nbsp;
                                                    <button type="submit" id="btn_BuscarPuesto" class="btn btn-primary">Buscar</button>
                                                </div>
                                            </form>
                                            <form action="FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%><%
                                                if (request.getParameter("idInfAP") != null) {
                                                    out.print("&idInfAP=" + request.getParameter("idInfAP"));
                                                }
                                                  %>&form=3" method="post">
                                                <div class="container" style='height: 200px; overflow: auto;'>
                                                    <table class="container table-bordered">
                                                        <tr>
                                                            <th>
                                                                Puesto
                                                            </th>
                                                            <th>
                                                                Categoría
                                                            </th>
                                                            <th>
                                                                Salario Base
                                                            </th>
                                                            <th>
                                                                Seleccionar
                                                            </th>
                                                        </tr>
                                                        <%
                                                            //Variables
                                                            ResultSet vlo_RS;
                                                            String vlo_Condicion = "";

                                                            //Inicio
                                                            try {
                                                                if (request.getParameter("txtBuscar") != null) {
                                                                    vlo_Condicion = request.getParameter("txtBuscar");
                                                                }
                                                                vlo_RS = vlo_LogicaPuestos.ListaPuestos(vlo_Condicion);
                                                                while (vlo_RS.next()) {%>
                                                        <tr>
                                                            <td>
                                                                <%=vlo_RS.getString(2)%>
                                                            </td>
                                                            <td>
                                                                <%=vlo_RS.getInt(3)%>
                                                            </td>
                                                            <td>
                                                                <%=vlo_RS.getDouble(4)%>
                                                            </td>
                                                            <td>
                                                                <a href="FrmEmpleados.jsp?idEmpleado=<%=vlo_Empleados.getVgn_idEmpleado()%><%
                                                                    if (request.getParameter("idInfAP") != null) {
                                                                        out.print("&idInfAP=" + request.getParameter("idInfAP"));
                                                                    }
                                                                   %>&form=3&idPuesto=<%=vlo_RS.getInt(1)%>">
                                                                    <img src="image/comprobado.png" alt=""/>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <%}
                                                            } catch (Exception e) {
                                                                vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al cargar la lista de registros.", "ISO-8859-1");
                                                                response.sendRedirect("FrmListaPuestos.jsp?msj=" + vlc_Mensaje);
                                                            }
                                                        %>
                                                    </table>
                                                </div>
                                            </form>
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
                            <%}
                                }
                            %>
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
