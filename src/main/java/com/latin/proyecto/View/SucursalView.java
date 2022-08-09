package com.latin.proyecto.View;

import com.latin.proyecto.Controller.SucursalController;
import com.latin.proyecto.Model.Sucursal;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sandoval
 */
public class SucursalView {
    private AdministradorLayout administradorLayout;
    private SucursalController sController;
    private Sucursal sucursal;
    protected JPanel panelSucursal=new JPanel();
    private JScrollPane scrollPane;
    private javax.swing.JTable table;
    private JDialog sucursalDialog, deleteDialog;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnUpdate, btnDelete;
    private DefaultTableModel dfm;
    private int row,codigo;    
    
    public void execute(){
        init();        
    }
    
    private void init(){
        panelSucursal.setLayout(null);
        panelSucursal.setBackground(Color.gray);
        initButtons();
        
        initTable();
    }
    
    private void initButtons(){
        JButton btnCreate=new JButton("Crear");
        btnCreate.setBounds(500, 25, 150, 50);
        panelSucursal.add(btnCreate);
        ActionListener create=(ActionEvent e) -> {
            this.sucursal=null;
            create(dfm,row);
        };
        btnCreate.addActionListener(create);

        JButton btnLoad=new JButton("Cargar");
        btnLoad.setBounds(700, 25, 150, 50);
        panelSucursal.add(btnLoad);
        ActionListener load=(ActionEvent e) -> {
            //
        };
        btnLoad.addActionListener(load);
        
        btnUpdate=new JButton("Actualizar");
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(500, 100, 150, 50);
        panelSucursal.add(btnUpdate);
        ActionListener update=(ActionEvent e) -> {
            create(dfm,row);
        };
        btnUpdate.addActionListener(update);
        
        btnDelete=new JButton("Eliminar");
        btnDelete.setEnabled(false);
        btnDelete.setBounds(700, 100, 150, 50);
        panelSucursal.add(btnDelete);
        ActionListener delete=(ActionEvent e) -> {
            delete(dfm,row);
        };
        btnDelete.addActionListener(delete);
        
        JButton btnExport=new JButton("Exportar a PDF");
        btnExport.setBounds(500, 180, 350, 50);
        panelSucursal.add(btnExport);
    }
    
    private void initTable(){
        table=new JTable();
        dfm=new DefaultTableModel();
        dfm.addColumn("ID");
        dfm.addColumn("NOMBRE"); 
        dfm.addColumn("DIRECCION");
        dfm.addColumn("CORREO");
        dfm.addColumn("TELEFONO");
        sController=new SucursalController();        
        codigo=0;
        for(Sucursal s : sController.list()){
            Object[] obj= {s.getCodigo(),s.getNombre(),s.getDireccion(),
                            s.getCorreo(),s.getTelefono()};
            dfm.addRow(obj);
            codigo=s.getCodigo();
        }
        table.setModel(dfm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(37);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <=4 ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRender);
        }
        
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 20, 450, 450);        
        panelSucursal.add(scrollPane);
        codigo++;
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event){
                row=-2;
                tableMouseClicked(event);
            }
        });
    }
    
    private void create(DefaultTableModel dfm,int row){
        txtNombre = new JTextField();
        txtDireccion = new JTextField();
        txtCorreo = new JTextField();
        txtTelefono = new JTextField();
        if(sucursal!=null){
            txtNombre.setText(sucursal.getNombre());
            txtDireccion.setText(sucursal.getDireccion());
            txtCorreo.setText(sucursal.getCorreo());
            txtTelefono.setText(sucursal.getTelefono());
        }else{
            txtNombre.setText("");
            txtDireccion.setText("");
            txtCorreo.setText("");
            txtTelefono.setText("");
            sucursal=new Sucursal();
        }

        
        administradorLayout=new AdministradorLayout();
        sucursalDialog=new JDialog(administradorLayout,true);
        sucursalDialog.setTitle("Sucursal");
        sucursalDialog.setLocationRelativeTo(null);
        sucursalDialog.setBounds(50, 175, 350, 400);
        
        JPanel panelCreate=new JPanel();
        panelCreate.setLayout(null);
        panelCreate.setBackground(Color.gray);
        sucursalDialog.add(panelCreate);        
        //
        initLabels(panelCreate);
        initTextFields(panelCreate);
        
        JButton btnSave = new JButton("Guardar");
        btnSave.setBounds(100, 280, 140, 30);
        panelCreate.add(btnSave);
        sController=new SucursalController();
        ActionListener insert=(ActionEvent e) -> {            
            if(!txtNombre.getText().equals("") && !txtDireccion.getText().equals("")
                    && !txtCorreo.getText().equals("") && !txtTelefono.getText().equals("")
                    && sucursal.getCodigo()==0){
                sucursal.setNombre(txtNombre.getText());
                sucursal.setDireccion(txtDireccion.getText());
                sucursal.setCorreo(txtCorreo.getText());
                sucursal.setTelefono(txtTelefono.getText());
                sController.create(sucursal);
                Object[] obj={codigo,sucursal.getNombre(),sucursal.getDireccion(),
                    sucursal.getCorreo(),sucursal.getTelefono()};
                dfm.addRow(obj);
                codigo++;
            }else if(sucursal.getCodigo() !=0){
                sucursal.setNombre(txtNombre.getText());
                sucursal.setDireccion(txtDireccion.getText());
                sucursal.setCorreo(txtCorreo.getText());
                sucursal.setTelefono(txtTelefono.getText());
                sController.update(sucursal);
                dfm.setValueAt(txtNombre.getText(), row, 1);
                dfm.setValueAt(txtDireccion.getText(), row, 2);
                dfm.setValueAt(txtCorreo.getText(), row, 3);
                dfm.setValueAt(txtTelefono.getText(), row, 4);
            }
            sucursalDialog.setVisible(false);
            
        };
        btnSave.addActionListener(insert);        
        sucursalDialog.setVisible(true);
    }
    
    public void tableMouseClicked(java.awt.event.MouseEvent evt){
            row = table.getSelectedRow(); 
            if(row >=0){
                sucursal=new Sucursal();
                sucursal.setCodigo(Integer.parseInt(table.getValueAt(row, 0).toString()));
                sucursal.setNombre(table.getValueAt(row, 1).toString());
                sucursal.setDireccion(table.getValueAt(row, 2).toString());
                sucursal.setCorreo(table.getValueAt(row, 3).toString());
                sucursal.setTelefono(table.getValueAt(row, 4).toString());
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
            }
            //System.out.println("Row "+row);
    }
    
    private void delete(DefaultTableModel dfm,int row){
        deleteDialog=new JDialog(administradorLayout, true);
        deleteDialog.setTitle("Eliminación de elementos");
        deleteDialog.setLocationRelativeTo(null);
        deleteDialog.setBounds(50, 175, 350, 200);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        deleteDialog.add(panel);
        
        JLabel mensaje=new JLabel("Desea eliminar elemento");
        mensaje.setBounds(100, 20, 200, 50);
        panel.add(mensaje);
        
        JLabel elemento=new JLabel(sucursal.getNombre().toUpperCase());
        elemento.setBounds(150, 40, 200, 50);
        elemento.setForeground(Color.blue);
        panel.add(elemento);
        
        JButton delete = new JButton("Eliminar");
        delete.setBounds(100, 110, 140, 30);
        panel.add(delete);
        sController = new SucursalController();
        ActionListener actionDelete=(ActionEvent e) -> {
            sController.delete(sucursal.getCodigo());
            deleteDialog.setVisible(false);
            dfm.removeRow(row);
            codigo--;
//            initTable();
        };
        delete.addActionListener(actionDelete);        
        deleteDialog.setVisible(true);
    }

    private void initLabels(JPanel panel){
        final short Y=50;
        JLabel lblNombre=new JLabel("Nombre:");
        lblNombre.setBounds(30, Y, 80, 50);
        panel.add(lblNombre);
        
        JLabel lblDireccion=new JLabel("Direccion:");
        lblDireccion.setBounds(30, Y*2, 80, 50);
        panel.add(lblDireccion);        
        
        JLabel lblCorreo=new JLabel("Correo:");
        lblCorreo.setBounds(30, Y*3, 80, 50);
        panel.add(lblCorreo);      
        
        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(30, Y*4, 80, 50);
        panel.add(lblTelefono);  
    }
    
    private void initTextFields(JPanel panel){
        final short Y=50;
        txtNombre.setBounds(100, Y+12, 180, 25);
        panel.add(txtNombre);
        
        txtDireccion.setBounds(100, Y*2+12, 180, 25);
        panel.add(txtDireccion);
        
        txtCorreo.setBounds(100, Y*3+12, 180, 25);
        panel.add(txtCorreo);
        
        txtTelefono.setBounds(100, Y*4+12, 180, 25);
        panel.add(txtTelefono);
    }
}
