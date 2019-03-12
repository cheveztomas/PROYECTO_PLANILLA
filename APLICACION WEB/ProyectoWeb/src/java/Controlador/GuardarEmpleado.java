/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.ClsEmpleados;
import Entidades.ClsRetorno;
import Logica.ClsLogicaEmpleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas Chevez
 */
public class GuardarEmpleado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Variables
            ClsLogicaEmpleado vlo_LogicaEmpleado = new ClsLogicaEmpleado();
            ClsRetorno vlo_Retorno;
            ClsEmpleados vlo_Empleado = new ClsEmpleados();
            String vlc_Mensaje = "";

            //Inicio
            try {
                //Se carga la entidad de empleado con los valores de la sesión
                vlo_Empleado.setVgn_idEmpleado(Integer.parseInt(request.getParameter("txtidEmpleado")));
                vlo_Empleado.setVgc_nombre(request.getParameter("txtnombre"));
                vlo_Empleado.setVgc_primerApellido(request.getParameter("txtapellido1"));
                vlo_Empleado.setVgc_segundoApellido(request.getParameter("txtapellido2"));
                vlo_Empleado.setVgc_cedula(request.getParameter("txtcedula"));
                vlo_Empleado.setVgc_telefono(request.getParameter("txttelefono"));
                vlo_Empleado.setVgc_correo(request.getParameter("txtcorreo"));
                vlo_Empleado.setVgc_numeroCuenta(request.getParameter("txtcuenta"));
                vlo_Empleado.setVgf_fechaContratacion(new java.sql.Date(java.sql.Date.valueOf(request.getParameter("txtfecha")).getTime()));
                
                //Se invoca la función que guarda un empleado con la entidad de empleado.
                vlo_Retorno = vlo_LogicaEmpleado.GuardarEmpleado(vlo_Empleado);
                vlc_Mensaje = URLEncoder.encode(vlo_Retorno.getVgc_Mensaje(), "ISO-8859-1");
                
                //Se redirige a la página de origen con el mensaje retornado de la base datos.
                response.sendRedirect("FrmEmpleados.jsp?msj=" + vlo_Retorno.getVgc_Mensaje() + "&idEmpleado=" + vlo_Retorno.getVgc_ID());
            } catch (Exception e) {
                vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al realizar acción.", "ISO-8859-1");
                response.sendRedirect("FrmEmpleados.jsp?msj=" + vlc_Mensaje);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GuardarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GuardarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
