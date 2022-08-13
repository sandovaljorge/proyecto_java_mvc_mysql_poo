package com.latin.proyecto.Controller;

import com.latin.proyecto.Model.Cliente;
import com.latin.proyecto.Resource.Conexion;
import com.latin.proyecto.Service.ICliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public class ClienteController implements ICliente{

    private PreparedStatement ps;
    private ResultSet rs;
    private final Conexion conexion=new Conexion();
    private String query;
    @Override
    public ArrayList<Cliente> list() {
        ArrayList<Cliente> list=new ArrayList<>();
        query = "SELECT C.CODIGO,P.NOMBRE,NIT,P.CORREO,P.GENERO FROM CLIENTE AS C "
                + "INNER JOIN PERSONA AS P ON C.CODIGO_PERSONA=P.CODIGO;";
        //query = "SELECT * FROM VIEW_CLIENTE;";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cliente=new Cliente();
                cliente.setCodigo(rs.getShort(1));
                //cliente.setCodigoPersona(rs.getInt(2));
                cliente.setNombre(rs.getString(2));
                cliente.setNit(rs.getString(3));
                cliente.setCorreo(rs.getString(4));
                cliente.setGenero(rs.getString(5));
                list.add(cliente);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return list;
    }

    @Override
    public void create(Cliente cliente) {
        query = "INSERT INTO CLIENTE(CODIGO_PERSONA,NIT) VALUES(?,?)";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setInt(1, cliente.getCodigoPersona());
            ps.setString(2, cliente.getNit());
            ps.executeUpdate();
            System.out.println("Inserted...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void update(Cliente cliente) {
        query = "UPDATE CLIENTE SET NIT=? WHERE CODIGO=?";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, cliente.getNit());
            ps.setInt(7, cliente.getCodigo());
            ps.executeUpdate();
            System.out.println("Updated...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void delete(int codigo) {
        query = "DELETE FROM CLIENTE WHERE CODIGO="+codigo+"";
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
    public Cliente clienteById(int codigo) {
        Cliente cliente=new Cliente();
        query = "SELECT * FROM CLIENTE WHERE CODIGO_PERSONA="+codigo+"";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                cliente.setCodigo(rs.getShort(1));
                cliente.setCodigoPersona(rs.getInt(2));
                cliente.setNit(rs.getString(3));
                return cliente;
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return cliente;
    }

    @Override
    public ArrayList<Cliente> list(String parameter) {
        ArrayList<Cliente> list=new ArrayList<>();
        String sql = "SELECT C.CODIGO,P.NOMBRE,NIT,P.CORREO,P.GENERO FROM CLIENTE AS C "
                + "INNER JOIN PERSONA AS P ON C.CODIGO_PERSONA=P.CODIGO "
                + "WHERE P.NOMBRE LIKE '%"+parameter+"%'";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cliente=new Cliente();
                cliente.setCodigo(rs.getInt("CODIGO"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setNit(rs.getString("NIT"));
                cliente.setCorreo(rs.getString("CORREO"));
                cliente.setGenero(rs.getString("GENERO"));
                list.add(cliente);
            }
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }finally{
            conexion.closeConexion();
        }
        return list;
    }

    @Override
    public Cliente clientByName(String parameter) {
        Cliente cliente=new Cliente();
        query = "SELECT C.CODIGO,P.NOMBRE,NIT,P.CORREO,P.GENERO FROM CLIENTE AS C "
                + "INNER JOIN PERSONA AS P ON C.CODIGO_PERSONA=P.CODIGO "
                + "WHERE P.NOMBRE LIKE '%"+parameter+"%'";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                cliente.setCodigo(rs.getInt("CODIGO"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setNit(rs.getString("NIT"));
                cliente.setCorreo(rs.getString("CORREO"));
                cliente.setGenero(rs.getString("GENERO"));
                return cliente;
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return cliente;
    }
    
}
