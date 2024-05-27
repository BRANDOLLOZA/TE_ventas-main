
package com.emergentes.dao;

import com.emergentes.modelo.Proveedor;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOimpl extends ConexionDB implements ProveedorDAO {
    @Override
    public void insert(Proveedor proveedor) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement ("insert into proveedores (nom_proveedor, nom_producto, prod_cantidad, fecha) values(?,?,?,?)");      
            ps.setString(1, proveedor.getNom_proveedor());
            ps.setString(2, proveedor.getNom_producto());
            ps.setInt(3, proveedor.getProd_cantidad());
            ps.setString(4, proveedor.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();;
        }
    }

    @Override
    public void update(Proveedor proveedor) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update proveedores set nom_proveedor = ?, nom_producto= ?,prod_cantidad = ?, fecha=? where id = ?");
            ps.setString(1, proveedor.getNom_proveedor());
            ps.setString(2, proveedor.getNom_producto());
            ps.setInt(3, proveedor.getProd_cantidad());
            ps.setString(4, proveedor.getFecha());
            ps.setInt(5, proveedor.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("delete from  proveedores where id = ?");       
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();;
        }
    }

    @Override
    public Proveedor getById(int id) throws Exception {
        Proveedor pro = new Proveedor();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from  proveedores where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setNom_proveedor(rs.getString("nom_proveedor"));
                pro.setNom_producto(rs.getString("nom_producto"));
                pro.setProd_cantidad(rs.getInt("prod_cantidad"));
                pro.setFecha(rs.getString("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();;
        }
        return pro;
    }

    @Override
    public List<Proveedor> getAll() throws Exception {
        List<Proveedor> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement( "select * from proveedores");
 
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Proveedor>();
           while(rs.next()) {
               Proveedor p = new Proveedor();
               
                p.setId(rs.getInt("id"));
                p.setNom_proveedor(rs.getString("nom_proveedor"));
                p.setNom_producto(rs.getString("nom_producto"));
                p.setProd_cantidad(rs.getInt("prod_cantidad"));
                p.setFecha(rs.getString("fecha"));
                
                lista.add(p);
            }
           rs.close();
           ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();;
        }
        return lista;
    }
}
