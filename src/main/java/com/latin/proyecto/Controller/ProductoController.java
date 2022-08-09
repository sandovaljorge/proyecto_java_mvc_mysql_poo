package com.latin.proyecto.Controller;

import com.latin.proyecto.Model.Producto;
import com.latin.proyecto.Resource.Conexion;
import com.latin.proyecto.Service.IProducto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public class ProductoController implements IProducto{

    private PreparedStatement ps;
    private ResultSet rs;
    private final Conexion conexion=new Conexion();
    private String query;
    @Override
    public ArrayList<Producto> list() {
        ArrayList<Producto> list=new ArrayList<>();
        query = "SELECT * FROM PRODUCTO";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto p=new Producto();
                p.setCodigo(rs.getShort(1));
                p.setNombre(rs.getString(2));
                p.setDescripcion(rs.getString(3));
                p.setCantidad(rs.getInt(4));
                p.setPrecio(rs.getFloat(5));
                list.add(p);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return list;
    }

    @Override
    public void create(Producto producto) {
        query = "INSERT INTO PRODUCTO(NOMBRE,DESCRIPCION,CANTIDAD,PRECIO) VALUES"
                + "(?,?,?,?);";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getCantidad());
            ps.setFloat(4, producto.getPrecio());
            ps.executeUpdate();
            //System.out.println("Inserted...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public void update(Producto producto) {
        query = "UPDATE PRODUCTO SET NOMBRE=?, DESCRIPCION=?, CANTIDAD=?, "
                + "PRECIO=? WHERE CODIGO=?";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getCantidad());
            ps.setFloat(4, producto.getPrecio());
            ps.setInt(5, producto.getCodigo());
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
        query = "DELETE FROM PRODUCTO WHERE CODIGO="+codigo+"";
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
