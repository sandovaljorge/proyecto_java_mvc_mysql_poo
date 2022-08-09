package com.latin.proyecto.View;

import com.latin.proyecto.Controller.ProductoController;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.latin.proyecto.Model.Producto;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Sandoval
 */
public class ProductosView {
    protected JPanel panelProductos=new JPanel();
    private ProductoController pController;
    private JScrollPane scrollPane;
    private AdministradorLayout administradorLayout;
    private JTable table;
    private DefaultTableModel dfm;
    private Producto producto;
    private JDialog dialog,deleteDialog;
    private int row,codigo,role;
    private JTextField txtNombre, txtDescripcion,
            txtCantidad,txtPrecio;
    private JButton btnCreate,btnUpdate,btnDelete,
            btnLoad,btnExport;
    
    public void execute(int role){
        this.role=role;
        init();
    }
    
    private void init(){
        panelProductos.setLayout(null);
        panelProductos.setBackground(Color.gray);
        initButtons();
        initTable();
    }
    
    private void initButtons(){
        btnCreate=new JButton("Crear");
        btnCreate.setBounds(500, 25, 150, 50);
        panelProductos.add(btnCreate);
        ActionListener create=(ActionEvent e) -> {
            this.producto=null;
            create(dfm,row);
        };
        btnCreate.addActionListener(create);
        
        btnLoad=new JButton("Cargar");
        btnLoad.setBounds(700, 25, 150, 50);
        panelProductos.add(btnLoad);
        
        btnUpdate=new JButton("Actualizar");
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(500, 100, 150, 50);
        panelProductos.add(btnUpdate);
        ActionListener update=(ActionEvent e) -> {
            create(dfm,row);
        };
        btnUpdate.addActionListener(update);
        
        btnDelete=new JButton("Eliminar");
        btnDelete.setEnabled(false);
        btnDelete.setBounds(700, 100, 150, 50);
        panelProductos.add(btnDelete);
        ActionListener delete=((e) -> {
            delete(dfm, row);
        });
        btnDelete.addActionListener(delete);
        
        btnExport=new JButton("Exportar a PDF");
        if(role==1){
            btnExport.setEnabled(true);
        }else{
            btnExport.setEnabled(false);
        }
        btnExport.setBounds(500, 180, 350, 50);
        panelProductos.add(btnExport);
    }

    private void initTable(){
        table=new JTable();
        dfm=new DefaultTableModel();
        dfm.addColumn("ID");
        dfm.addColumn("NOMBRE");
        dfm.addColumn("DESCRIPCION");
        dfm.addColumn("CANTIDAD");
        dfm.addColumn("PRECIO");        
        pController =new ProductoController();
        codigo=0;
        for(Producto p : pController.list()){
            Object[] obj={
                p.getCodigo(),p.getNombre(),p.getDescripcion(),
                p.getCantidad(),p.getPrecio()
            };
            dfm.addRow(obj);
            codigo=p.getCodigo();
        }
        table.setModel(dfm);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(127);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        
        DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <=4 ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRender);
        }
        
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 20, 450, 450);
        panelProductos.add(scrollPane);
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
            if(row >=0){
                producto=new Producto();
                producto.setCodigo(Integer.parseInt(table.getValueAt(row, 0).toString()));
                producto.setNombre(table.getValueAt(row, 1).toString());
                producto.setDescripcion(table.getValueAt(row, 2).toString());
                producto.setCantidad(Integer.parseInt(table.getValueAt(row, 3).toString()));
                producto.setPrecio(Float.parseFloat(table.getValueAt(row, 4).toString()));
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
            }
    }
    
    private void create(DefaultTableModel dfm,int row){
        txtNombre = new JTextField();
        txtDescripcion= new JTextField();
        txtCantidad = new JTextField();
        txtPrecio= new JTextField();
        if(producto!=null){
            txtNombre.setText(producto.getNombre());
            txtDescripcion.setText(producto.getDescripcion());
            txtCantidad.setText(String.valueOf(producto.getCantidad()));
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
        }else{
            txtNombre.setText("");
            txtDescripcion.setText("");
            txtCantidad.setText("");
            txtPrecio.setText("");
            producto=new Producto();
        }

        final short Y=50;
        administradorLayout=new AdministradorLayout();
        dialog=new JDialog(administradorLayout,false);
        dialog.setTitle("Producto");
        dialog.setLocationRelativeTo(null);
        dialog.setBounds(50, 175, 350, 400);
        
        JPanel panelCreate=new JPanel();
        panelCreate.setLayout(null);
        panelCreate.setBackground(Color.gray);
        dialog.add(panelCreate);
        
        JLabel lblNombre=new JLabel("Nombre:");
        lblNombre.setBounds(30, Y, 80, 50);
        panelCreate.add(lblNombre);
        
        txtNombre.setBounds(100, Y+12, 180, 25);
        panelCreate.add(txtNombre);
        
        JLabel lblDireccion=new JLabel("Descripción:");
        lblDireccion.setBounds(30, Y*2, 80, 50);
        panelCreate.add(lblDireccion);

        txtDescripcion.setBounds(100, Y*2+12, 180, 25);
        panelCreate.add(txtDescripcion);
        
        JLabel lblCorreo=new JLabel("Cantidad:");
        lblCorreo.setBounds(30, Y*3, 80, 50);
        panelCreate.add(lblCorreo);
        
        txtCantidad.setBounds(100, Y*3+12, 180, 25);
        panelCreate.add(txtCantidad);
        
        JLabel lblTelefono = new JLabel("Precio:");
        lblTelefono.setBounds(30, Y*4, 80, 50);
        panelCreate.add(lblTelefono);
        
        txtPrecio.setBounds(100, Y*4+12, 180, 25);
        panelCreate.add(txtPrecio);
        
        JButton btnSave = new JButton("Registrar");
        btnSave.setBounds(100, 280, 140, 30);
        panelCreate.add(btnSave);
        pController=new ProductoController();
        ActionListener insert=(ActionEvent e) -> {            
            if(!txtNombre.getText().equals("") && !txtDescripcion.getText().equals("")
                    && !txtCantidad.getText().equals("") && !txtPrecio.getText().equals("")
                    && producto.getCodigo()==0){
                producto.setNombre(txtNombre.getText());
                producto.setDescripcion(txtDescripcion.getText());
                producto.setCantidad(Integer.parseInt(txtCantidad.getText()));
                producto.setPrecio(Float.parseFloat(txtPrecio.getText()));
                pController.create(producto);
                Object[] obj={codigo,producto.getNombre(),producto.getDescripcion(),
                    producto.getCantidad(),producto.getPrecio()};
                dfm.addRow(obj);
                codigo++;
            }else if(producto.getCodigo() !=0){
                producto.setNombre(txtNombre.getText());
                producto.setDescripcion(txtDescripcion.getText());
                producto.setCantidad(Integer.parseInt(txtCantidad.getText()));
                producto.setPrecio(Float.parseFloat(txtPrecio.getText()));
                pController.update(producto);
                dfm.setValueAt(txtNombre.getText(), row, 1);
                dfm.setValueAt(txtDescripcion.getText(), row, 2);
                dfm.setValueAt(txtCantidad.getText(), row, 3);
                dfm.setValueAt(producto.getPrecio(), row, 4);
            }
            dialog.setVisible(false);            
        };
        btnSave.addActionListener(insert);        
        dialog.setVisible(true);
    }
    
    private void delete(DefaultTableModel dfm, int row){
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
        
        JLabel elemento=new JLabel(producto.getNombre().toUpperCase());
        elemento.setBounds(150, 40, 200, 50);
        elemento.setForeground(Color.blue);
        panel.add(elemento);
        
        JButton delete = new JButton("Eliminar");
        delete.setBounds(100, 110, 140, 30);
        panel.add(delete);
        pController =new ProductoController();
        ActionListener actionDelete=(ActionEvent e) -> {
            pController.delete(producto.getCodigo());
            deleteDialog.setVisible(false);
            dfm.removeRow(row);            
//            initTable();
        };
        delete.addActionListener(actionDelete);        
        deleteDialog.setVisible(true);
    }

}
