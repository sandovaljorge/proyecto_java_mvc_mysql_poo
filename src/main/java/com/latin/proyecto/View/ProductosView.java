package com.latin.proyecto.View;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Sandoval
 */
public class ProductosView {
    protected JPanel panelProductos=new JPanel();
    private JScrollPane scrollPane;
    private JTable table;
    
    public void execute(){
        init();
    }
    
    private void init(){
        panelProductos.setLayout(null);
        panelProductos.setBackground(Color.gray);
        initButtons();
        initTable();
    }
    
    private void initButtons(){
        JButton btnCreate=new JButton("Crear");
        btnCreate.setBounds(500, 25, 150, 50);
        panelProductos.add(btnCreate);
        
        JButton btnLoad=new JButton("Cargar");
        btnLoad.setBounds(700, 25, 150, 50);
        panelProductos.add(btnLoad);
        
        JButton btnUpdate=new JButton("Actualizar");
        btnUpdate.setBounds(500, 100, 150, 50);
        panelProductos.add(btnUpdate);
        
        JButton btnDelete=new JButton("Eliminar");
        btnDelete.setBounds(700, 100, 150, 50);
        panelProductos.add(btnDelete);
        
        JButton btnExport=new JButton("Exportar a PDF");
        btnExport.setBounds(500, 180, 350, 50);
        panelProductos.add(btnExport);
    }
    
    private void initTable(){
        String columns[] = {"CODIGO","NOMBRE","DESCRIPCIÓN","CANTIDAD","PRECIO"};
        Object files [][]={{"2018","BARRENO","HERRAMIENTA","12","550"},
            {"1805","CALADORA","HERRAMIENTA","9","475"}};
        table=new JTable(files,columns);
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 20, 450, 450);
        panelProductos.add(scrollPane);
    }
}
