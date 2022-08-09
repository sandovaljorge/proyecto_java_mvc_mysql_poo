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
        
    }

    @Override
    public void update(Usuario usuario) {
        
    }

    @Override
    public void delete(int id) {
        
    }
    
}
