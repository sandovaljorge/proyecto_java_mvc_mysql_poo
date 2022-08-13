package com.latin.proyecto.View;

import com.latin.proyecto.Controller.UsuarioController;
import com.latin.proyecto.Model.Usuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Sandoval
 */
public class LoginView extends JFrame{
    private final JPanel panel=new JPanel();
    private Usuario usuario;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private AdministradorLayout adLayout;
    
    public LoginView(){
        setSize(300,270);
        setTitle("Inicio de sesión");
        setLocationRelativeTo(null);
        init();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void init(){
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        this.getContentPane().add(panel);
        
        initLabel();
        initTextField();
        initPassword();
        initButton();
    }
    
    private void initLabel(){
        JLabel lb1=new JLabel("Usuario: ");
        lb1.setBounds(20, 63, 65, 10);
        panel.add(lb1);
        
        JLabel lb1Password=new JLabel("Contraseña: ");
        lb1Password.setBounds(20, 105, 85, 10);
        panel.add(lb1Password);
    }
    
    private void initTextField(){
        txtUsuario=new JTextField();
        txtUsuario.setBounds(100, 55, 140, 25);
        panel.add(txtUsuario); 
    }
    
    private void initPassword(){
        txtPassword=new JPasswordField();
        txtPassword.setBounds(100, 95, 140, 25);
        panel.add(txtPassword);
    }
    
    private void initButton(){
        JButton btnIngresar=new JButton();
        btnIngresar.setText("Ingresar");
        btnIngresar.setBounds(100, 150, 100, 40);
        btnIngresar.addActionListener(eventLogin());
        panel.add(btnIngresar);
    }
    
    private ActionListener eventLogin(){
        ActionListener login=(ActionEvent e) -> {
            if(!txtUsuario.getText().equals("") && !txtPassword.getText().equals("")){
                UsuarioController uController=new UsuarioController();               
                int role=0;
                usuario=new Usuario();
                usuario.setUsuario(txtUsuario.getText());
                usuario.setPassword(txtPassword.getText());
                role=uController.login(usuario);
                switch (role) {
                    case 1 -> {
                        adLayout=new AdministradorLayout(role);
                        adLayout.setTitle("Administrador");
                        adLayout.setVisible(true);
                        this.setVisible(false);
                    }
                    case 2 -> {
                        adLayout=new AdministradorLayout(role);
                        adLayout.setTitle("Vendedor");
                        adLayout.setVisible(true);
                        this.setVisible(false);
                    }
                    default -> {
                        JOptionPane.showMessageDialog(null, "Acceso incorrecto.");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe ingresar un usuario y constraseña.");
            }
        };
        return login;
    }    
}
