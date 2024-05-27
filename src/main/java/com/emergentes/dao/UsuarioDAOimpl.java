
package com.emergentes.dao;

import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl extends ConexionDB implements UsuarioDAO {

    @Override
    public void insert(Usuario usuario) throws Exception {
        try{
        this.conectar();
        PreparedStatement us = this.conn.prepareStatement("insert into usuarios (email, password) values (?,?)");
        us.setString(1, usuario.getEmail());
        us.setString(2, usuario.getPassword());
        
        us.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        try{
            this.conectar();
            PreparedStatement us = this.conn.prepareStatement("update  usuarios set email = ?, password = ? where id = ?");
            us.setString(1, usuario.getEmail());
            us.setString(2, usuario.getPassword());
            us.setInt(3, usuario.getId());
            
            us.executeUpdate();
        
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
         try{
            this.conectar();
            PreparedStatement us = this.conn.prepareStatement("delete from usuarios where id = ?");
            
            us.setInt(1,id);
            
            us.executeUpdate();
        
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Usuario getById(int id) throws Exception {
        Usuario usr = new Usuario();
         try{
            this.conectar();
            PreparedStatement us = this.conn.prepareStatement("select * from usuarios  where id = ?");
            us.setInt(1, id);
            ResultSet rs = us.executeQuery();
            if(rs.next()){
                usr.setId(rs.getInt("id"));
                usr.setEmail(rs.getString("email"));
                usr.setPassword(rs.getString("password"));
            }
        
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
         return usr;
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = null;
        try{
            this.conectar();
            PreparedStatement us = this.conn.prepareStatement("select * from usuarios ");
            
            ResultSet rs = us.executeQuery();
            lista = new ArrayList<Usuario>();
            while(rs.next()){
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                
                lista.add(u);
            }
            rs.close();
            us.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
         return lista;
    }
    
}
