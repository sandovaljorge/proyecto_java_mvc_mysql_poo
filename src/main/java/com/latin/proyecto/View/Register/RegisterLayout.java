
package com.latin.proyecto.View.Register;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Sandoval
 */
public class RegisterLayout extends JFrame{
    private int role;
    private final JTabbedPane layout=new JTabbedPane();
    
    public RegisterLayout(){
        setTitle("Vendedor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 200, 700, 450);
        init();
    }
    public RegisterLayout(int role){
        this.role=role;
        setTitle("Vendedor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 200, 700, 450);
        init();
    }
    
    private void init(){
        PersonaView pView=new PersonaView();
        pView.execute();
        
        //
        layout.addTab("Persona", pView.panel);
        //
        add(layout);
    }
    
    public static void main(String[] args) {
        RegisterLayout rl=new RegisterLayout();
        rl.setVisible(true);
    }
    
}
