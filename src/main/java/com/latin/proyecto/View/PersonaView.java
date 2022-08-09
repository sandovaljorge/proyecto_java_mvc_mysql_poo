package com.latin.proyecto.View;

import com.latin.proyecto.Controller.ClienteController;
import com.latin.proyecto.Controller.PersonaController;
import com.latin.proyecto.Model.Cliente;
import com.latin.proyecto.Model.Persona;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Sandoval
 */
public class PersonaView {
    private AdministradorLayout rLayout;
    protected final JPanel panel=new JPanel();
    private JTable table;
    private JDialog dialog,cDialog;
    private DefaultTableModel model;
    private PersonaController pController;
    private ClienteController cController;
    private JScrollPane scrollPane;
    private int row,codigo;
    private Persona persona;
    private Cliente cliente;
    private JTextField txtNombre,txtApellido,txtDireccion,txtCorreo,
            txtTelefono,txtNit;
    private JComboBox cbnGenero;
    private JButton btnCreate,btnUpdate,btnDelete,btnClient;
    
    public void execute(){
        init();
    }
    
    private void init(){
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);
        initButtons();
        initTable();
    }
    
    private void initButtons(){
        btnCreate=new JButton("Crear");
        btnCreate.setBounds(45, 300, 100, 50);
        panel.add(btnCreate);
        ActionListener create=(ActionEvent e) -> {
            this.persona=null;
            create(model,row);
        };
        btnCreate.addActionListener(create);
        
        btnClient=new JButton("Cliente");
        btnClient.setEnabled(false);
        btnClient.setBounds(285, 300, 100, 50);
        panel.add(btnClient);
        ActionListener createClient=(ActionEvent e) -> {
            createClient();
        };
        btnClient.addActionListener(createClient);
        
        btnUpdate=new JButton("Actualizar");
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(410, 300, 100, 50);
        panel.add(btnUpdate);
        ActionListener update=(ActionEvent e) -> {
            create(model,row);
        };
        btnUpdate.addActionListener(update);
        
        btnDelete=new JButton("Eliminar");
        btnDelete.setEnabled(false);
        btnDelete.setBounds(535, 300, 100, 50);
        panel.add(btnDelete);
    }
    
    private void initTable(){
        table=new JTable();
        model=new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NOMBRE");
        model.addColumn("APELLIDO");
        model.addColumn("DIRECCION");
        model.addColumn("CORREO");
        model.addColumn("TELEFONO");
        model.addColumn("GENERO");
        pController=new PersonaController();
        codigo=0;
        for(Persona p : pController.list()){
            Object [] obj={p.getCodigo(),p.getNombre(),p.getApellido(),
                p.getDireccion(),p.getCorreo(),p.getTelefono(),
                p.getGenero()};
            model.addRow(obj);
            codigo=p.getCodigo();
        }
        
        table.setModel(model);
        
        /**
         *Cambiar el ancho de columnas de la tabla 
         */
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(37);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);
        /**
         * Centrar el texto en la celdas de la tabla
         */
        DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <=6 ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRender);
        }
                
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 20, 660, 250);        
        panel.add(scrollPane);
        codigo++;
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event){
                row=-2;
                tableMouseClicked(event);
            }
        });
    }
    
    public void tableMouseClicked(java.awt.event.MouseEvent evt){
            row = table.getSelectedRow();
            cController=new ClienteController();
            if(row >=0){
                persona=new Persona();
                cliente=new Cliente();
                persona.setCodigo(Integer.parseInt(table.getValueAt(row, 0).toString()));
                persona.setNombre(table.getValueAt(row, 1).toString());
                persona.setApellido(table.getValueAt(row, 2).toString());
                persona.setDireccion(table.getValueAt(row, 3).toString());
                persona.setCorreo(table.getValueAt(row, 3).toString());
                persona.setTelefono(table.getValueAt(row, 3).toString());
                cliente= cController.clienteById(Integer.parseInt(table.getValueAt(row, 0).toString()));
                btnClient.setEnabled(true);
                //btnUpdate.setEnabled(true);
                //btnDelete.setEnabled(true);
            }
            //System.out.println("Row "+row);
    }
    
    protected void createClient(){
        txtNit=new JTextField();
        if(cliente!=null){
            txtNit.setText(cliente.getNit());
        }else{
            txtNit.setText("");
            cliente=new Cliente();
        }
        rLayout=new AdministradorLayout();
        cDialog=new JDialog(rLayout, true);
        cDialog.setTitle("Usuario");
        cDialog.setLocationRelativeTo(null);
        cDialog.setBounds(50, 175, 350, 300);
        
        JPanel cPanel=new JPanel();
        cPanel.setLayout(null);
        cPanel.setBackground(Color.LIGHT_GRAY);
        cDialog.add(cPanel);        
        //
        initLabelsClient(cPanel);
        initTextFieldClient(cPanel);
        
        JButton btnSave = new JButton("Registrar");
        btnSave.setBounds(100, 180, 140, 30);
        cPanel.add(btnSave);
        ActionListener register=(ActionEvent e) -> {
            if(!txtNit.getText().equals("")){
                cliente.setCodigoPersona(persona.getCodigo());
                cliente.setNit(txtNit.getText());
                cController=new ClienteController();
                cController.create(cliente);
            }else if(cliente.getCodigo()!=0){
                cliente.setNit(txtNit.getText());
                cController.update(cliente);
            }
            cDialog.setVisible(false);
        };
        btnSave.addActionListener(register);        
        cDialog.setVisible(true);
    }
    
    private void create(DefaultTableModel model,int row){
        txtNombre=new JTextField();
        txtApellido=new JTextField();
        txtDireccion=new JTextField();
        txtCorreo=new JTextField();
        txtTelefono=new JTextField();
        if(persona!=null){
            txtNombre.setText(persona.getNombre());
            txtApellido.setText(persona.getApellido());
            txtDireccion.setText(persona.getDireccion());
            txtCorreo.setText(persona.getCorreo());
            txtTelefono.setText(persona.getTelefono());            
        }else{
            txtNombre.setText("");
            txtApellido.setText("");
            txtDireccion.setText("");
            txtCorreo.setText("");
            txtTelefono.setText("");
            persona=new Persona();
        }
        rLayout=new AdministradorLayout();
        dialog=new JDialog(rLayout, true);
        dialog.setTitle("Persona");
        dialog.setLocationRelativeTo(null);
        dialog.setBounds(50, 175, 350, 450);
        
        JPanel dPanel=new JPanel();
        dPanel.setLayout(null);
        dPanel.setBackground(Color.LIGHT_GRAY);
        dialog.add(dPanel);
        
        //
        initLabels(dPanel);
        initTextField(dPanel);
        
        JButton btnSave = new JButton("Guardar");
        btnSave.setBounds(100, 365, 140, 30);
        dPanel.add(btnSave);
        pController=new PersonaController();
        ActionListener insert=(ActionEvent e) -> {
            if(!txtNombre.getText().equals("") && !txtApellido.getText().equals("") && !txtDireccion.getText().equals("")
                    && !txtCorreo.getText().equals("") && !txtTelefono.getText().equals("")
                    && persona.getCodigo()==0){
                persona.setNombre(txtNombre.getText());
                persona.setApellido(txtApellido.getText());
                persona.setDireccion(txtDireccion.getText());
                persona.setCorreo(txtCorreo.getText());
                persona.setTelefono(txtTelefono.getText());
                persona.setGenero(cbnGenero.getSelectedItem().toString());
                pController.create(persona);
                Object[] obj={codigo,persona.getNombre(),persona.getApellido(),
                    persona.getDireccion(),persona.getCorreo(),persona.getTelefono(),
                    persona.getGenero()};
                model.addRow(obj);
                codigo++;
            }else if(persona.getCodigo()!=0){
                persona.setNombre(txtNombre.getText());
                persona.setApellido(txtApellido.getText());
                persona.setDireccion(txtDireccion.getText());
                persona.setCorreo(txtCorreo.getText());
                persona.setTelefono(txtTelefono.getText());
                persona.setGenero(cbnGenero.getSelectedItem().toString());
                pController.update(persona);
                model.setValueAt(txtNombre.getText(), row, 1);
                model.setValueAt(txtApellido.getText(), row, 2);
                model.setValueAt(txtDireccion.getText(), row, 3);
                model.setValueAt(txtCorreo.getText(), row, 4);
                model.setValueAt(txtTelefono.getText(), row, 5);
                model.setValueAt(cbnGenero.getSelectedItem(), row, 6);
            }
            dialog.setVisible(false);
            System.out.println("Codigo: "+codigo);
        };
        btnSave.addActionListener(insert);
        dialog.setVisible(true);
    }
    
    private void initLabels(JPanel panel){
        int X=40;
        int Y=55;
        JLabel lblNombre=new JLabel();
        lblNombre.setText("Nombre:");
        lblNombre.setBounds(X, 30, 50, 20);
        panel.add(lblNombre);
        
        JLabel lblApellido=new JLabel();
        lblApellido.setText("Apellido:");
        lblApellido.setBounds(X, Y+30, 50, 20);
        panel.add(lblApellido);
        
        JLabel lblDireccion=new JLabel();
        lblDireccion.setText("Dirección:");
        lblDireccion.setBounds(X, Y*2+30, 60, 20);
        panel.add(lblDireccion);
        
        JLabel lblCorreo=new JLabel();
        lblCorreo.setText("Correo:");
        lblCorreo.setBounds(X, Y*3+30, 60, 20);
        panel.add(lblCorreo);
        
        JLabel lblTelefono=new JLabel();
        lblTelefono.setText("Telefono:");
        lblTelefono.setBounds(X, Y*4+30, 60, 20);
        panel.add(lblTelefono);
        
        JLabel lblGenero=new JLabel();
        lblGenero.setText("Género:");
        lblGenero.setBounds(X, Y*5+30, 60, 20);
        panel.add(lblGenero);
    }
    
    private void initTextField(JPanel panel){
        int X=40;
        int Y=55;
        txtNombre.setBounds(X*2+25, 30, 200, 20);
        panel.add(txtNombre);        
        
        txtApellido.setBounds(X*2+25, Y+30, 200, 20);
        panel.add(txtApellido);        
        
        txtDireccion.setBounds(X*2+25, Y*2+30, 200, 20);
        panel.add(txtDireccion);        
        
        txtCorreo.setBounds(X*2+25, Y*3+30, 200, 20);
        panel.add(txtCorreo);        
        
        txtTelefono.setBounds(X*2+25, Y*4+30, 200, 20);
        panel.add(txtTelefono);
        
        String generos[]={"M","F"};
        cbnGenero=new JComboBox(generos);
        cbnGenero.setBounds(X*2+25, Y*5+30, 200, 20);
        panel.add(cbnGenero);
    }
    
    private void initLabelsClient(JPanel panel){
        int X=40;
        //int Y=55;
        JLabel lblPersona=new JLabel("Cliente: "+persona.getNombre().toUpperCase());
//        lblPersona.setOpaque(true);
//        lblPersona.setBackground(Color.RED);
        lblPersona.setBounds(X+60, 30, 150, 20);
        lblPersona.setHorizontalAlignment(JLabel.CENTER);
//        lblPersona.setHorizontalTextPosition(JLabel.CENTER);        
        panel.add(lblPersona);

        JLabel lblNit=new JLabel("NIT: ");
        lblNit.setBounds(X+20, 80, 30, 20);
        panel.add(lblNit);
//        JLabel lblPassword=new JLabel();
//        lblPassword.setText("Contraseña:");
//        lblPassword.setBounds(X, Y+30, 50, 20);
//        panel.add(lblPassword);
//        
//        JLabel lblConfirmPassword=new JLabel();
//        lblConfirmPassword.setText("Confirmar Contraseña:");
//        lblConfirmPassword.setBounds(X, Y*2+30, 60, 20);
//        panel.add(lblConfirmPassword);
//        
//        JLabel lblDate=new JLabel();
//        lblDate.setText("Fecha Creación:");
//        lblDate.setBounds(X, Y*3+30, 60, 20);
//        panel.add(lblDate);
        
//        JLabel lblTelefono=new JLabel();
//        lblTelefono.setText("Telefono:");
//        lblTelefono.setBounds(X, Y*4+30, 60, 20);
//        panel.add(lblTelefono);
        
//        JLabel lblRole=new JLabel();
//        lblRole.setText("Género:");
//        lblRole.setBounds(X, Y*5+30, 60, 20);
//        panel.add(lblRole);
    }
    
    private void initTextFieldClient(JPanel panel){
        int X=40;        
        
        txtNit.setBounds(X*2+25, 80, 150, 25);
        panel.add(txtNit);        
        
//        txtPassword.setBounds(X*2+25, Y+30, 200, 20);
//        panel.add(txtPassword);        
//        
//        txtConfirmPassword.setBounds(X*2+25, Y*2+30, 200, 20);
//        panel.add(txtConfirmPassword);        
//        
//        txtDate.setBounds(X*2+25, Y*3+30, 200, 20);
//        txtDate.setEditable(false);
//        panel.add(txtDate);        
        
//        txtTelefono.setBounds(X*2+25, Y*4+30, 200, 20);
//        panel.add(txtTelefono);
        
//        String generos[]={"CLIENTE","VENDEDOR","ADMINISTRADOR"};
//        cbnRole=new JComboBox(generos);
//        cbnRole.setBounds(X*2+25, Y*5+30, 200, 20);
//        panel.add(cbnRole);
    }
    
    
//                usuario.setUsuario(txtUser.getText());
//                usuario.setPassword(txtPassword.getText());
//                usuario.setCodigoPersona(persona.getCodigo());
//                usuario.setFechaCreacion(java.sql.Date.valueOf(LocalDate.now()));
//                usuario.setRole(1);
//                usuario.setEstado(true);
}
