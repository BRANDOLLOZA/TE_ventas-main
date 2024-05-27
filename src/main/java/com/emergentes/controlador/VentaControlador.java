
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.dao.VentaDAO;
import com.emergentes.dao.VentaDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Venta;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VentaControlador", urlPatterns = {"/VentaControlador"})
public class VentaControlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           VentaDAO dao = new VentaDAOimpl();
           ProductoDAO daoProducto = new ProductoDAOimpl();
           ClienteDAO daoCliente = new ClienteDAOimpl();
           int id;
           List<Producto> lista_productos = null;
           List<Cliente> lista_clientes = null;
           Venta ven = new Venta();
           
           String action = (request.getParameter("action") != null ) ? request.getParameter("action") :"view"; 
           
           switch(action){
               case "add":
                   lista_productos = daoProducto.getAll();
                   lista_clientes = daoCliente.getAll();
                   request.setAttribute("lista_clientes", lista_clientes);
                   request.setAttribute("lista_productos", lista_productos);
                   request.setAttribute("venta", ven);
                   request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    break;
               case "edit":
                   
                    id = Integer.parseInt(request.getParameter("id"));
                    ven = dao.getById(id);
                    request.setAttribute("venta", ven);
                   request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                   break;
               case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("VentaControlador");
                 
                   break;
               case "view":
                   //obtener la lista de registros
                   List<Venta> lista = dao.getAll();
                   request.setAttribute("ventas", lista);
                   request.getRequestDispatcher("ventas.jsp").forward(request, response);
                   break;
           }
       }catch(Exception ex){
           System.out.println("Error " + ex.getMessage());
       }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         int id = Integer.parseInt(request.getParameter("id"));
        int cliente_id = Integer.parseInt(request.getParameter("cliente_id"));
        int producto_id = Integer.parseInt(request.getParameter("producto_id"));
        String cliente = request.getParameter("cliente");
        String producto = request.getParameter("producto");
        String fecha = request.getParameter("fecha");
        
        Venta ven = new Venta();

        ven.setId(id);
        ven.setProducto_id(producto_id);
        ven.setCliente_id(cliente_id);
        ven.setFecha((java.sql.Date) convierteFecha (fecha));
        ven.setProducto(producto);
        ven.setCliente(cliente);
        
        if(id == 0){
            //
            VentaDAO dao = new VentaDAOimpl();
             try {
                 dao.insert(ven);
                 response.sendRedirect("VentaControlador");
             } catch (Exception ex) {
                 Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
             }
            
        }
        else{
            //
             VentaDAO dao = new VentaDAOimpl();
             try {
                 dao.update(ven);
                 response.sendRedirect("VentaControlador");
             } catch (Exception ex) {
                 Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        }
        public Date convierteFecha(String fecha)
        {
            Date fechaBD = null;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            
            java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return fechaBD;
        }
    }
    

   

