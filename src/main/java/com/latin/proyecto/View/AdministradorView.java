package com.latin.proyecto.View;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Sandoval
 */
public class AdministradorView extends JFrame{
    private JTabbedPane layouts=new JTabbedPane();
    private JPanel productos=new JPanel();
    private JPanel vendedores=new JPanel();
    private JPanel clientes=new JPanel();
    
    
    public AdministradorView(){
        setTitle("Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 200, 900, 550);
        init();
    }
    
    private void init(){
        //
        productos.setLayout(null);
        productos.setBackground(Color.gray);
        clientes.setLayout(null);
        clientes.setBackground(Color.gray);
        vendedores.setLayout(null);
        vendedores.setBackground(Color.gray);
        
        //
        SucursalView sucursalView=new SucursalView();
        sucursalView.execute();
        
        //        
        layouts.addTab("Productos", productos);
        layouts.addTab("Clientes", clientes);
        layouts.addTab("Vendedores", vendedores);
        layouts.addTab("Sucursal", sucursalView.panelSucursal);
        
        //
        add(layouts);
    }
    
    public static void main(String[] args) {
        AdministradorView ad=new AdministradorView();
        ad.setVisible(true);
    }
}
