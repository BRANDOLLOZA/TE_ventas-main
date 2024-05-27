
package com.emergentes.controlador;

import com.emergentes.dao.ProveedorDAO;
import com.emergentes.dao.ProveedorDAOimpl;
import com.emergentes.modelo.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProveedorControlador", urlPatterns = {"/ProveedorControlador"})
public class ProveedorControlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try {
            Proveedor prop = new Proveedor();
            int id;
            ProveedorDAO dao = new ProveedorDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("proveedor", prop);
                    request.getRequestDispatcher("frmproveedor.jsp").forward(request, response);

                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    prop = dao.getById(id);
                    request.setAttribute("proveedor", prop);
                    request.getRequestDispatcher("frmproveedor.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("ProveedorControlador");
                    break;
                case "view":
                    //obtener la lista de registros
                    List<Proveedor> lista = dao.getAll();
                    request.setAttribute("proveedores", lista);
                    request.getRequestDispatcher("proveedor.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        int id = Integer.parseInt(request.getParameter("id"));
        String nom_proveedor = request.getParameter("nom_proveedor");
        String nom_producto = request.getParameter("nom_producto");
        int prod_cantidad =Integer.parseInt( request.getParameter("prod_cantidad"));
        String fecha = request.getParameter("fecha");

        Proveedor prop = new Proveedor();

        prop.setId(id);
        prop.setNom_proveedor(nom_proveedor);
        prop.setNom_producto(nom_producto);
        prop.setProd_cantidad(prod_cantidad);
        prop.setFecha(fecha);
        ProveedorDAO dao = new ProveedorDAOimpl();
        if (id == 0) {
            try {
                //Nuevo registro

                dao.insert(prop);
            } catch (Exception ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
        } else {
            try {
                dao.update(prop);
            } catch (Exception ex) {
                System.out.println("Error al editar " + ex.getMessage());
            }
        }
        response.sendRedirect("ProveedorControlador");
    }
}


