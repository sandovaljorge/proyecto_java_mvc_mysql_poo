package com.latin.proyecto.Controller;

import com.latin.proyecto.Model.Sucursal;
import com.latin.proyecto.Resource.Conexion;
import com.latin.proyecto.Service.ISucursal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sandoval
 */
public class SucursalController implements ISucursal{

    private PreparedStatement ps;
    private ResultSet rs;
    private final Conexion conexion=new Conexion();
    private String query;
    @Override
    public List<Sucursal> list() {
        List<Sucursal> list=new ArrayList<>();
        query = "SELECT * FROM SUCURSAL";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Sucursal s=new Sucursal();
                s.setCodigo(rs.getShort(1));
                s.setNombre(rs.getString(2));
                s.setDireccion(rs.getString(3));
                s.setCorreo(rs.getString(4));
                s.setTelefono(rs.getString(5));
                list.add(s);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return list;
    }

    @Override
    public void create(Sucursal sucursal) {
        query = "INSERT INTO SUCURSAL(NOMBRE,DIRECCION,CORREO,TELEFONO) VALUES"
                + "(?,?,?,?);";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, sucursal.getNombre());
            ps.setString(2, sucursal.getDireccion());
            ps.setString(3, sucursal.getCorreo());
            ps.setString(4, sucursal.getTelefono());
            ps.executeUpdate();
            //System.out.println("Inserted...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void update(Sucursal sucursal) {
        query = "UPDATE SUCURSAL SET NOMBRE=?, DIRECCION=?, CORREO=?, "
                + "TELEFONO=? WHERE CODIGO=?";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, sucursal.getNombre());
            ps.setString(2, sucursal.getDireccion());
            ps.setString(3, sucursal.getCorreo());
            ps.setString(4, sucursal.getTelefono());
            ps.setInt(5, sucursal.getCodigo());
            ps.executeUpdate();
            //System.out.println("Updated...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void delete(int codigo) {
        query = "DELETE FROM SUCURSAL WHERE CODIGO="+codigo+"";
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
    
}
