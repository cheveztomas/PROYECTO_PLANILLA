/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.ClsPlanilla;
import Entidades.ClsRetorno;
import Logica.ClsLogicaPlanilla;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas Chevez
 */
public class GenerarPlanilla extends HttpServlet {

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
            ClsLogicaPlanilla vlo_LogicaPlanilla = new ClsLogicaPlanilla();
            ClsPlanilla vlo_Planilla = new ClsPlanilla();
            ClsRetorno vlo_Retorno;
            String vlc_Mensaje = "";

            //Inicio
            try {
                //Se caragn un la entidad los datos que vienen por sesión del formulario que genera una planilla
                vlo_Planilla.setVgn_idPlanilla(Integer.parseInt(request.getParameter("txtidPlanilla")));
                vlo_Planilla.setVgf_Fecha(new java.sql.Date(java.sql.Date.valueOf(request.getParameter("txtfecha")).getTime()));
                
                //Se invova el metodo que genera una planilla como parametro la entidad de planilla.
                vlo_Retorno = vlo_LogicaPlanilla.GenerarPlanilla(vlo_Planilla);
                vlc_Mensaje = URLEncoder.encode(vlo_Retorno.getVgc_Mensaje(), "ISO-8859-1");
                
                //Se redirige a la página de origen con el mensaje del base de datos..
                if (vlo_Retorno.getVgc_ID() < 0) {
                    response.sendRedirect("FrmPlanilla.jsp?msj=" + vlc_Mensaje);
                } else {
                    response.sendRedirect("FrmPlanilla.jsp?idPlanilla=" + vlo_Retorno.getVgc_ID() + "&msj=" + vlc_Mensaje);
                }
            } catch (Exception e) {
                vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al realizar acción.", "ISO-8859-1");
                response.sendRedirect("FrmPlanilla.jsp?msj=" + vlc_Mensaje);
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
            Logger.getLogger(GenerarPlanilla.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GenerarPlanilla.class.getName()).log(Level.SEVERE, null, ex);
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
