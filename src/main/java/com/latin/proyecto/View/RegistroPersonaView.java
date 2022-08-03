package com.latin.proyecto.View;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sandoval
 */
public class RegistroPersonaView {
    protected JPanel panel=new JPanel();
    private final int X=50;
    //private final int Y=60;
    
    public void execute(){
        init();
    }
    
    private void init(){
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        initLabels();
        initButtons();
        initTextField();
        initComboBox();
    }
    
    private void initButtons(){
        JButton btnRegistrar=new JButton("Registrar");
        btnRegistrar.setBounds(150, 380, 100, 35);
        
        panel.add(btnRegistrar);
    }
    
    private void initLabels(){
        JLabel lblNombre=new JLabel();
        lblNombre.setText("Nombre:");
        lblNombre.setBounds(X, 30, 50, 20);
        panel.add(lblNombre);
        
        JLabel lblApellido=new JLabel();
        lblApellido.setText("Apellido:");
        lblApellido.setBounds(X, 90, 50, 20);
        panel.add(lblApellido);
        
        JLabel lblDireccion=new JLabel();
        lblDireccion.setText("Dirección:");
        lblDireccion.setBounds(X, 150, 60, 20);
        panel.add(lblDireccion);
        
        JLabel lblCorreo=new JLabel();
        lblCorreo.setText("Correo:");
        lblCorreo.setBounds(X, 210, 60, 20);
        panel.add(lblCorreo);
        
        JLabel lblTelefono=new JLabel();
        lblTelefono.setText("Telefono:");
        lblTelefono.setBounds(X, 270, 60, 20);
        panel.add(lblTelefono);
        
        JLabel lblGenero=new JLabel();
        lblGenero.setText("Género:");
        lblGenero.setBounds(X, 330, 60, 20);
        panel.add(lblGenero);
    }
    private void initTextField(){
        JTextField txtNombre=new JTextField();
        txtNombre.setBounds(X*2+25, 30, 200, 20);
        panel.add(txtNombre);
        
        JTextField txtApellido=new JTextField();
        txtApellido.setBounds(X*2+25, 90, 200, 20);
        panel.add(txtApellido);
        
        JTextField txtDireccion=new JTextField();
        txtDireccion.setBounds(X*2+25, 150, 200, 20);
        panel.add(txtDireccion);
        
        JTextField txtCorreo=new JTextField();
        txtCorreo.setBounds(X*2+25, 210, 200, 20);
        panel.add(txtCorreo);
        
        JTextField txtTelefono=new JTextField();
        txtTelefono.setBounds(X*2+25, 270, 200, 20);
        panel.add(txtTelefono);
    }
    
    private void initComboBox(){
        String generos[]={"N","M","F"};
        JComboBox cbnGenero=new JComboBox(generos);
        cbnGenero.setBounds(X*2+25, 330, 200, 20);
        panel.add(cbnGenero);        
    }
}
