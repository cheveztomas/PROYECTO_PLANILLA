/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.ClsRetorno;
import Logica.ClsLogicaInformacionAcademica;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas Chevez
 */
public class EliminarInformacionAcademica extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Variables
            ClsRetorno vlo_Retorno;
            ClsLogicaInformacionAcademica vlo_LogicaInformacionAcademica = new ClsLogicaInformacionAcademica();
            String vlc_Mensaje = "";

            //Inicio
            try {
                vlo_Retorno = vlo_LogicaInformacionAcademica.EliminarInformacionAcademica(Integer.parseInt(request.getParameter("idInfA")));
                vlc_Mensaje = URLEncoder.encode(vlo_Retorno.getVgc_Mensaje(), "ISO-8859-1");
                response.sendRedirect("FrmEmpleados.jsp?msj=" + vlc_Mensaje + "&idEmpleado=" + request.getParameter("idEmpleado") + "&form=2");
            } catch (Exception e) {
                vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al realizar acción.", "ISO-8859-1");
                response.sendRedirect("FrmEmpleados.jsp?msj=" + vlc_Mensaje + "&idEmpleado=" + request.getParameter("idEmpleado") + "&form=2");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
