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
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    
    public LoginView(){
        setSize(300,300);
        setTitle("Inicio de sesión");
        setLocationRelativeTo(null);
        init();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * inicializa todos los objetos en el formulario
     */
    private void init(){
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        this.getContentPane().add(panel);
        
        initLabel();
        initTextField();
        initPassword();
        initButton();
    }
    
    /**
     * inicializa todos los 
     */
    private void initLabel(){
        JLabel lb1=new JLabel("Usuario: ");
        lb1.setBounds(20, 60, 65, 10);
        panel.add(lb1);
        
        JLabel lb1Password=new JLabel("Contraseña: ");
        lb1Password.setBounds(20, 100, 85, 10);
        panel.add(lb1Password);
    }
    
    private void initTextField(){
        txtUsuario=new JTextField();
        txtUsuario.setBounds(100, 55, 140, 20);
        panel.add(txtUsuario); 
    }
    
    private void initPassword(){
        txtPassword=new JPasswordField();
        txtPassword.setBounds(100, 95, 140, 20);
        panel.add(txtPassword);
    }
    
    /**
     * inicializa los botones en el formulario
     */
    private void initButton(){
        JButton btnRegistrar=new JButton();
        btnRegistrar.setText("Registrar");
        btnRegistrar.setBounds(50, 180, 85, 30);
        panel.add(btnRegistrar);
        
        JButton btnIngresar=new JButton();
        btnIngresar.setText("Ingresar");
        btnIngresar.setBounds(150, 180, 85, 30);
        btnIngresar.addActionListener(eventLogin());
        panel.add(btnIngresar);
    }
    
    private ActionListener eventLogin(){
        ActionListener login=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtUsuario.getText().equals("") && !txtPassword.getText().equals("")){
                    UsuarioController uController=new UsuarioController();
                    Usuario u=new Usuario();
                    u.setUsuario(txtUsuario.getText());
                    u.setPassword(txtPassword.getText());
                    switch (uController.login(u)) {
                        case 1 -> JOptionPane.showMessageDialog(null, "Role Administrador");
                        case 2 -> JOptionPane.showMessageDialog(null, "Role Vendedor");
                        default -> JOptionPane.showMessageDialog(null, "Acceso incorrecto.");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe ingresar un usuario y constraseña.");
                }
            }
        };
        return login;
    }
}
