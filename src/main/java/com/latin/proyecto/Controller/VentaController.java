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
        
    }
    
}
