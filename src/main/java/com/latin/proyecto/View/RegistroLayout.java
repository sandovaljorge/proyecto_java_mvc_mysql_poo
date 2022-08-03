package com.latin.proyecto.View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Sandoval
 */
public class RegistroLayout extends JFrame{
    private final JTabbedPane layout=new JTabbedPane();
    
    public RegistroLayout(){
        setTitle("Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 200, 400, 500);
        init();
    }
    
    private void init(){
        RegistroPersonaView rPersona=new RegistroPersonaView();
        rPersona.execute();
        RegistroUsuarioView rUsuario=new RegistroUsuarioView();
        rUsuario.execute();
        
        //
        layout.addTab("Registro de Personas", rPersona.panel);
        layout.addTab("Registro de Usuarios", rUsuario.panel);
        
        //
        add(layout);
    }
    
    public static void main(String[] args) {
        RegistroLayout rp=new RegistroLayout();
        rp.setVisible(true);
    }
}
