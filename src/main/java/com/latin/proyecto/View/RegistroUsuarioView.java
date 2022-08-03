package com.latin.proyecto.View;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Sandoval
 */
public class RegistroUsuarioView {
    protected JPanel panel=new JPanel();
    private final int X=40;
    private final int Y=70;
    
    public void execute(){
        init();
    }
    
    private void init(){
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        initLabels();
        initButtons();
        initTextFields();
    }
    
    private void initButtons(){
        JButton btnRegistrar=new JButton("Registrar");
        btnRegistrar.setBounds(150, 300, 100, 35);
        
        panel.add(btnRegistrar);
    }
    
    private void initTextFields(){
        JTextField txtUsuario=new JTextField();
        txtUsuario.setBounds(X*4+25, Y, 150, 20);
        panel.add(txtUsuario);
        
        JPasswordField txtPassword=new JPasswordField();
        txtPassword.setBounds(X*4+25, Y*2, 150, 20);
        panel.add(txtPassword);
        
        JPasswordField txtPasswordConfirm=new JPasswordField();
        txtPasswordConfirm.setBounds(X*4+25, Y*3, 150, 20);
        panel.add(txtPasswordConfirm);
    }
    private void initLabels(){
        JLabel lblUsuario=new JLabel();
        lblUsuario.setText("Usuario:");
        lblUsuario.setBounds(X, Y, 50, 20);
        panel.add(lblUsuario);
        
        JLabel lblPassword=new JLabel();
        lblPassword.setText("Contraseña:");
        lblPassword.setBounds(X, Y*2, 150, 20);
        panel.add(lblPassword);
        
        JLabel lblPasswordConfirm=new JLabel();
        lblPasswordConfirm.setText("Confirmar Contraseña:");
        lblPasswordConfirm.setBounds(X, Y*3, 150, 20);
        panel.add(lblPasswordConfirm);
    }
}
