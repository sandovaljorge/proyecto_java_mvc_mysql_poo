package com.latin.proyecto.View;

import com.latin.proyecto.Controller.VentaController;
import com.latin.proyecto.Model.Venta;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Sandoval
 */
public class AdministradorLayout extends JFrame{
    private final JTabbedPane layouts=new JTabbedPane();
    private int role;
    
    public AdministradorLayout(){
        setTitle("Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 200, 900, 550);
        setResizable(false);
        init();
    }
    
    public AdministradorLayout(int role){
        this.role=role;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 200, 900, 550);
        init();
    }
    
    private void init(){
        //
        SucursalView sucursalView=new SucursalView();
        sucursalView.execute();
        ProductosView productosView=new ProductosView();
        productosView.execute(this.role);       
        PersonaView personaView=new PersonaView();
        personaView.execute(this.role);
        ClientesView clientesView=new ClientesView();
        clientesView.execute(this.role);
        VendedoresView vendedoresView=new VendedoresView();
        vendedoresView.execute();
        NuevaVentaView nVentaView=new NuevaVentaView();
        nVentaView.execute();
        VentaView venta=new VentaView();
        venta.execute();
        
        //        
        if(this.role==1){
            layouts.addTab("Personas", personaView.panel);
            layouts.addTab("Clientes", clientesView.panelClientes);
            layouts.addTab("Productos", productosView.panelProductos);
            layouts.addTab("Vendedores", vendedoresView.panelVendedores);
            layouts.addTab("Sucursal", sucursalView.panelSucursal);
        }else{
            layouts.addTab("Personas", personaView.panel);
            layouts.addTab("Clientes", clientesView.panelClientes);
            layouts.addTab("Nueva Venta", nVentaView.globalPanel);
            layouts.addTab("Venta", venta.globalPanel);
        }
        
        //
        add(layouts);
    }
    public static void main(String[] args) {
        VentaController v=new VentaController();
        Venta ve=new Venta();
        ve.setNombre("");
        ve.setNit("");
        ve.setDate("");
        try{
            if(ve.getDate().equals("")){
                System.out.println("null date");
            }else{
                System.out.println("Date not null");
            }
        }catch(NullPointerException e){
            System.out.println("Error");            
        }
        for(Venta ven: v.getByParam(ve)){
            System.out.println("Codigo: "+ven.getCodigo()+" Nombre: "+ven.getNombre());
        }
    }
    
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    
}
