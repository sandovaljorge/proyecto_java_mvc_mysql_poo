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
public class VendedoresView {
    protected JPanel panelVendedores=new JPanel();
    private JScrollPane scrollPane;
    private JTable table;
    
    public void execute(){
        init();
    }
    
    private void init(){
        panelVendedores.setLayout(null);
        panelVendedores.setBackground(Color.gray);
        initButtons();
        initTable();
    }
    
    private void initButtons(){
        JButton btnCreate=new JButton("Crear");
        btnCreate.setBounds(500, 25, 150, 50);
        panelVendedores.add(btnCreate);
        
        JButton btnLoad=new JButton("Cargar");
        btnLoad.setBounds(700, 25, 150, 50);
        panelVendedores.add(btnLoad);
        
        JButton btnUpdate=new JButton("Actualizar");
        btnUpdate.setBounds(500, 100, 150, 50);
        panelVendedores.add(btnUpdate);
        
        JButton btnDelete=new JButton("Eliminar");
        btnDelete.setBounds(700, 100, 150, 50);
        panelVendedores.add(btnDelete);
        
        JButton btnExport=new JButton("Exportar a PDF");
        btnExport.setBounds(500, 180, 350, 50);
        panelVendedores.add(btnExport);
    }
    
    private void initTable(){
        String columns[] = {"CODIGO","NOMBRE","CAJA","VENTAS","GENERO"};
        Object files [][]={{"2022","Jorge","150","33","M"}};
        table=new JTable(files,columns);
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 20, 450, 450);
        panelVendedores.add(scrollPane);
    }
}
