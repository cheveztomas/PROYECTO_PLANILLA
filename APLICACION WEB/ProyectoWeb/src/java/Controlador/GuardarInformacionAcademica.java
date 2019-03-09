/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.ClsInformacionAcademica;
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
public class GuardarInformacionAcademica extends HttpServlet {

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
            ClsInformacionAcademica vlo_InformacionAcademica = new ClsInformacionAcademica();
            ClsRetorno vlo_Retorno;
            ClsLogicaInformacionAcademica vlo_LogicaInformacionAcademica = new ClsLogicaInformacionAcademica();
            String vlc_Mensaje = "";

            //Inicio
            try {
                vlo_InformacionAcademica.setVgc_especialidad(request.getParameter("txtespecialidad"));
                vlo_InformacionAcademica.setVgc_Grado(request.getParameter("txtgrado"));
                vlo_InformacionAcademica.setVgc_informacion(request.getParameter("txtinformacion"));
                vlo_InformacionAcademica.setVgn_idInformacionA(Integer.parseInt(request.getParameter("idInfA")));
                vlo_InformacionAcademica.setVgn_idEmpleado(Integer.parseInt(request.getParameter("txtidEmpleado")));
                vlo_Retorno = vlo_LogicaInformacionAcademica.GuardarInformacionAcademica(vlo_InformacionAcademica);
                vlc_Mensaje = URLEncoder.encode(vlo_Retorno.getVgc_Mensaje(), "ISO-8859-1");
                response.sendRedirect("FrmEmpleados.jsp?idEmpleado=" + vlo_InformacionAcademica.getVgn_idEmpleado() + "&idInfA=" + vlo_Retorno.getVgc_ID() + "&msj=" + vlo_Retorno.getVgc_Mensaje()+"&form=2");
            } catch (Exception e) {
                vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al realizar acción", "ISO-8859-1");
                response.sendRedirect("FrmEmpleados.jsp?idEmpleado=" + vlo_InformacionAcademica.getVgn_idEmpleado() + "&msj=" + vlc_Mensaje);
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
