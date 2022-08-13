package com.latin.proyecto.View;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sandoval
 */
public class VentaView {
    protected JPanel panelVenta=new JPanel();
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    
    
    public void execute(){
        init();
    }
    
    private void init(){
        panelVenta.setLayout(null);
        panelVenta.setBackground(Color.LIGHT_GRAY);
        
    }
}
