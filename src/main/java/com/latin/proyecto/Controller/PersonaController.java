package com.latin.proyecto.Controller;

import com.latin.proyecto.Model.Persona;
import com.latin.proyecto.Resource.Conexion;
import com.latin.proyecto.Service.IPersona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        query = "INSERT INTO PERSONA(NOMBRE,APELLIDO,DIRECCION,CORREO,"
                + "TELEFONO,GENERO)VALUES(?,?,?,?,?,?)";
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
        query = "UPDATE PERSONA SET NOMBRE=?, APELLIDO=?, DIRECCION=?, "
                + "CORREO=?, TELEFONO=?, GENERO=? WHERE CODIGO=?";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getDireccion());
            ps.setString(4, persona.getCorreo());
            ps.setString(5, persona.getTelefono());
            ps.setString(6, persona.getGenero());
            ps.setInt(7, persona.getCodigo());
            ps.executeUpdate();
            //System.out.println("Updated...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void delete(int id) {
        query = "DELETE FROM PERSONA WHERE CODIGO="+id+"";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }    

    @Override
    public ArrayList<Persona> list() {
        ArrayList<Persona> list=new ArrayList<>();
        query = "SELECT * FROM PERSONA";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Persona p=new Persona();
                p.setCodigo(rs.getShort(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setCorreo(rs.getString(5));
                p.setTelefono(rs.getString(6));
                p.setGenero(rs.getString(7));
                list.add(p);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return list;
    }
}
