package com.latin.proyecto.Controller;

import com.latin.proyecto.Model.Venta;
import com.latin.proyecto.Resource.Conexion;
import com.latin.proyecto.Service.IVenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public class VentaController implements IVenta{

    private PreparedStatement ps;
    private ResultSet rs;
    private final Conexion conexion=new Conexion();
    private String query;
    @Override
    public void create(Venta venta) {
        query = "INSERT INTO VENTA(CODIGO_CLIENTE,FECHA,TOTAL) VALUES(?,?,?)";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            ps.setInt(1, venta.getCodigoCliente());
            ps.setString(2, venta.getFecha().toString());
            ps.setDouble(3, venta.getTotal());
            ps.executeUpdate();
            //System.out.println("Inserted...");
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
    }

    @Override
    public ArrayList<Venta> list() {
        ArrayList<Venta> list=new ArrayList<>();
        query = "SELECT * FROM VENTAS;";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta venta=new Venta();
                venta.setCodigo(rs.getShort(1));
                venta.setCodigoCliente(rs.getInt(2));
                venta.setNombre(rs.getString(3));
                venta.setNit(rs.getString(4));
                venta.setFecha(rs.getDate(5));
                venta.setTotal(Double.parseDouble(rs.getString(6)));
                list.add(venta);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return list;
    }

    @Override
    public ArrayList<Venta> getByParam(Venta parameter) {
        ArrayList<Venta> list=new ArrayList<>();
        query = "SELECT V.CODIGO,V.CODIGO_CLIENTE,P.NOMBRE,C.NIT,V.FECHA,V.TOTAL "
                + "FROM VENTA AS V INNER JOIN CLIENTE AS C ON V.CODIGO_CLIENTE=C.CODIGO "
                + "INNER JOIN PERSONA AS P ON C.CODIGO_PERSONA=P.CODIGO WHERE "
                + "P.NOMBRE LIKE '%"+parameter.getNombre()+"%' AND "
                + "C.NIT LIKE '%"+parameter.getNit()+"%' AND "
                + "V.FECHA LIKE '%"+parameter.getDate()+"%';";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta venta=new Venta();
                venta.setCodigo(rs.getInt("CODIGO"));
                venta.setCodigoCliente(rs.getInt("CODIGO_CLIENTE"));
                venta.setNombre(rs.getString("NOMBRE"));
                venta.setNit(rs.getString("NIT"));
                venta.setFecha(rs.getDate("FECHA"));
                venta.setTotal(rs.getDouble("TOTAL"));
                list.add(venta);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return list;
    }
    
}
