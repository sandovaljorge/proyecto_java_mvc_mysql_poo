package com.latin.proyecto.View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Sandoval
 */
public class AdministradorLayout extends JFrame{
    private final JTabbedPane layouts=new JTabbedPane();
    
    public AdministradorLayout(){
        setTitle("Administrador");
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
        productosView.execute();
        ClientesView clientesView=new ClientesView();
        clientesView.execute();
        VendedoresView vendedoresView=new VendedoresView();
        vendedoresView.execute();
        
        //        
        layouts.addTab("Productos", productosView.panelProductos);
        layouts.addTab("Clientes", clientesView.panelClientes);
        layouts.addTab("Vendedores", vendedoresView.panelVendedores);
        layouts.addTab("Sucursal", sucursalView.panelSucursal);
        
        //
        add(layouts);
    }
    
    public static void main(String[] args) {
        AdministradorLayout ad=new AdministradorLayout();
        ad.setVisible(true);
    }
}
