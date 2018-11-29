/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jc.controlador;

import com.jc.modelo.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author campitos
 */
public class ServletGuardarAlmacen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            PrintWriter out=response.getWriter();
            
            Connection con = Conexion.conectarse("system", "system", "xe");
            CallableStatement callate = con.prepareCall("{call guardar_almacen(?,?)}");
            callate.setInt(1,Integer.parseInt(request.getParameter("numero")));
            callate.setString(2,request.getParameter("ubicacion")); 
            
            
            callate.execute();
            
            out.println("Almacen guardado con exito");
        } catch (Exception ex) {
            Logger.getLogger(ServletGuardarAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}