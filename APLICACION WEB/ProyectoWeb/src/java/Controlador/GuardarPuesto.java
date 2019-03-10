/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.ClsPuestos;
import Entidades.ClsRetorno;
import Logica.ClsLogicaPuestos;
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
public class GuardarPuesto extends HttpServlet {

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
            ClsRetorno vlo_Retorno = null;
            ClsLogicaPuestos vlo_LogicaPuestos = new ClsLogicaPuestos();
            ClsPuestos vlo_Puesto = new ClsPuestos();
            String vlc_Mensaje = "";

            //Inicio
            try {
                vlo_Puesto.setVgn_iPuesto(Integer.parseInt(request.getParameter("txtidPuesto")));
                vlo_Puesto.setVgc_NombrePuesto(request.getParameter("txtpuesto"));
                vlo_Puesto.setVgn_CategoriaPuesto(Integer.parseInt(request.getParameter("txtcategoria")));
                vlo_Puesto.setVgn_SalarioBase(Double.parseDouble(request.getParameter("txtsalario")));
                vlo_Retorno = vlo_LogicaPuestos.GuardarPuesto(vlo_Puesto);
                vlc_Mensaje = URLEncoder.encode(vlo_Retorno.getVgc_Mensaje(), "ISO-8859-1");
            } catch (Exception e) {
                vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al realizar acción.", "ISO-8859-1");
            } finally {
                if (request.getParameter("idEmpleado") != null) {
                    response.sendRedirect("FrmEmpleados.jsp?idEmpleado=" + request.getParameter("idEmpleado") + "&idPuesto=" + vlo_Retorno.getVgc_ID() + "&form=3");
                } else {
                    response.sendRedirect("FrmPuestos.jsp?msj=" + vlc_Mensaje);
                }
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
