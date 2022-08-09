package com.latin.proyecto.View;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Sandoval
 */
public class UsuarioView {
    protected JPanel uPanel=new JPanel();
    
    
    public void execute(){
        init();
    }
    
    private void init(){
        uPanel.setLayout(null);
        uPanel.setBackground(Color.GREEN);
        initButtons();
        initTable();
    }
    
    private void initButtons(){
        
    }
    
    private void initTable(){
        
    }
}
