package com.latin.proyecto.Controller;

import com.latin.proyecto.Model.Usuario;
import com.latin.proyecto.Resource.Conexion;
import com.latin.proyecto.Service.IUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public class UsuarioController implements IUsuario{

    private PreparedStatement ps;
    private ResultSet rs;
    private final Conexion conexion=new Conexion();
    private String query;
    
    @Override
    public ArrayList<Usuario> list() {
        return null;
    }

    @Override
    public int login(Usuario usuario) {
        query="SELECT ROLE FROM USUARIO WHERE USUARIO=? AND PASSWORD=?;";
        //query="SELECT CODIGO,USUARIO,FECHA_CREACION,ESTADO FROM USUARIO WHERE USUARIO=? AND PASSWORD=?;";
        int role=-1;
        try{            
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){                
                role=rs.getShort(1);
            }else{
                return -1;
            }
        }catch(SQLException e){
            System.out.println("Usuario o contraseña incorrecta.");
        }
        return role;
    }

    @Override
    public void create(Usuario usuario) {
        query = "INSERT INTO USUARIO(CODIGO_PERSONA,USUARIO,PASSWORD,FECHA_CREACION,"
                + "ROLE,ESTADO)VALUES(?,?,?,?,?,?)";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setInt(1, usuario.getCodigoPersona());
            ps.setString(2,usuario.getUsuario());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getFechaCreacion().toString());
            ps.setInt(5, usuario.getRole());
            ps.setBoolean(6, usuario.getEstado());
            ps.executeUpdate();
            //System.out.println("Inserted...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void update(Usuario usuario) {
        query = "UPDATE USUARIO SET USUARIO=?, PASSWORD=?, ROLE=?, "
                + "ESTADO=? WHERE CODIGO=?";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getRole());
            ps.setBoolean(4, usuario.getEstado());
            ps.setInt(5, usuario.getCodigo());
            ps.executeUpdate();
            System.out.println("Updated...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void delete(int id) {
        
    }

    @Override
    public Usuario getById(int codigo) {
        Usuario usuario=new Usuario();
        query = "SELECT * FROM USUARIO WHERE CODIGO_PERSONA="+codigo+"";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                usuario.setCodigo(rs.getShort(1));
                usuario.setCodigoPersona(rs.getInt(2));
                usuario.setUsuario(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setFechaCreacion(rs.getDate(5));
                usuario.setRole(rs.getInt(6));
                usuario.setEstado(rs.getBoolean(7));
                return usuario;
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return usuario;
    }
    
}
