package com.latin.proyecto.View;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Sandoval
 */
public class SucursalView {
    
    protected JPanel panelSucursal=new JPanel();
    private JScrollPane scrollPane;
    private JTable table;
    
    public void execute(){
        init();
    }
    
    private void init(){
        panelSucursal.setLayout(null);
        panelSucursal.setBackground(Color.gray);
        buttons();
        table();
    }
    
    private void buttons(){
        JButton btnCreate=new JButton("Crear");
        btnCreate.setBounds(500, 25, 150, 50);
        panelSucursal.add(btnCreate);
        
        JButton btnLoad=new JButton("Cargar");
        btnLoad.setBounds(700, 25, 150, 50);
        panelSucursal.add(btnLoad);
        
        JButton btnUpdate=new JButton("Actualizar");
        btnUpdate.setBounds(500, 100, 150, 50);
        panelSucursal.add(btnUpdate);
        
        JButton btnDelete=new JButton("Eliminar");
        btnDelete.setBounds(700, 100, 150, 50);
        panelSucursal.add(btnDelete);
        
        JButton btnExport=new JButton("Exportar a PDF");
        btnExport.setBounds(500, 180, 350, 50);
        panelSucursal.add(btnExport);
    }
    
    private void table(){
        String columns[] = {"CODIGO","NOMBRE","DIRECCION","CORREO","TELEFONO"};
        Object files [][]={{"2022","Jorge","Zona 1","jorge@correo.com","12345678"}};
        table=new JTable(files,columns);
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 20, 450, 450);
        panelSucursal.add(scrollPane);
    }
}
