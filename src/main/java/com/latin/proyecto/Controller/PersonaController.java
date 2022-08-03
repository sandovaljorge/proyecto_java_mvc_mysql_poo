package com.latin.proyecto.Controller;

import com.latin.proyecto.Model.Persona;
import com.latin.proyecto.Resource.Conexion;
import com.latin.proyecto.Service.IPersona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sandoval
 */
public class PersonaController implements IPersona{

    private PreparedStatement ps;
    private ResultSet rs;
    private final Conexion conexion=new Conexion();
    private String query;
    @Override
    public void create(Persona p) {
        query = "INSERT INTO PRODUCTO(nombre,cantidad,lugar_produccion,fecha_ingreso) "
                + "VALUES(?,?,?,?)";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getDireccion());
            ps.setString(4, p.getCorreo());
            ps.setString(5, p.getTelefono());
            ps.setString(6, p.getGenero());
            ps.executeUpdate();
            //System.out.println("Inserted...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void update(Persona persona) {
        
    }

    @Override
    public void delete(int id) {
        
    }    
}
