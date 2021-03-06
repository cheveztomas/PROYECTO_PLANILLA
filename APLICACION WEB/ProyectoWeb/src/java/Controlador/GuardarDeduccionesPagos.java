/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.ClsDeduccionesPagos;
import Entidades.ClsRetorno;
import Logica.ClsLogicaDeduccionesPagos;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas Chevez
 */
public class GuardarDeduccionesPagos extends HttpServlet {

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
            ClsLogicaDeduccionesPagos vlo_LogicaDeduccionesPagos = new ClsLogicaDeduccionesPagos();
            ClsRetorno vlo_Retorno;
            ClsDeduccionesPagos vlo_DeduccionesPagos = new ClsDeduccionesPagos();
            String vlc_Mensaje = "";

            //Inicio
            try {
                //Se caragn en la entidad de deducciones y pagos la información que viene por sesión del formulario de deducciones y pagos.
                vlo_DeduccionesPagos.setVgc_DeduccionGeneral(request.getParameter("txtconcepto"));
                vlo_DeduccionesPagos.setVgc_DeduccionDetallada(request.getParameter("txtcategoria"));
                vlo_DeduccionesPagos.setVgc_EsDeduccion(Boolean.valueOf(request.getParameter("cmbtipoconcepto")));
                vlo_DeduccionesPagos.setVgc_tipo(request.getParameter("cmbtipomonto"));
                vlo_DeduccionesPagos.setVgn_idDeduccionPago(Integer.parseInt(request.getParameter("txtidDediccionPago")));
                vlo_DeduccionesPagos.setVgn_Monto(Double.parseDouble(request.getParameter("txtmonto")));
                //Se invoca el metodo que guarda una deducción o pago con la entidad generada previamente.
                vlo_Retorno = vlo_LogicaDeduccionesPagos.GuardarDeduccionesPagos(vlo_DeduccionesPagos);
                vlc_Mensaje = URLEncoder.encode(vlo_Retorno.getVgc_Mensaje(), "ISO-8859-1");
                
                //Se redirige al formulario de origen con el mensaje proveniente de la base de datos.
                response.sendRedirect("FrmDeduccionesPagos.jsp?idDeduccionPago=" + vlo_Retorno.getVgc_ID() + "&msj=" + vlc_Mensaje);
            } catch (Exception e) {
                vlc_Mensaje = URLEncoder.encode(e.getMessage() + " Error al realizar acción.", "ISO-8859-1");
                response.sendRedirect("FrmDeduccionesPagos.jsp?msj=" + vlc_Mensaje);
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
