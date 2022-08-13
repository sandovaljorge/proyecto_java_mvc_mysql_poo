package com.latin.proyecto.Controller;

import com.latin.proyecto.Model.Vendedor;
import com.latin.proyecto.Resource.Conexion;
import com.latin.proyecto.Service.IVendedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public class VendedorController implements IVendedor {

    private PreparedStatement ps;
    private ResultSet rs;
    private final Conexion conexion=new Conexion();
    private String query;
    @Override
    public ArrayList<Vendedor> list() {
        ArrayList<Vendedor> list=new ArrayList<>();
        query="SELECT * FROM VENDEDORES;";
        try{
            conexion.openConexion();
            ps = conexion.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Vendedor vendedor=new Vendedor();
                vendedor.setCodigo(rs.getInt(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setCaja(rs.getInt(3));
                vendedor.setVenta(rs.getInt(4));
                vendedor.setGenero(rs.getString(5));
                list.add(vendedor);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }finally{
            conexion.closeConexion();
        }
        return list;
    }

    @Override
    public Vendedor getById(int codigo) {
        return null;
    }

    @Override
    public void create(Vendedor vendedor) {
        
    }

    @Override
    public void update(Vendedor vendedor) {
        
    }

    @Override
    public void delete(int codigo) {
        
    }
    
}
