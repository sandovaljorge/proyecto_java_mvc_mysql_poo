package com.latin.proyecto.View;

import com.latin.proyecto.Controller.ClienteController;
import com.latin.proyecto.Controller.ProductoController;
import com.latin.proyecto.Controller.VentaController;
import com.latin.proyecto.Model.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class NuevaVentaView {
    protected JPanel globalPanel=new JPanel();
    protected JPanel filterPanel=new JPanel();
    protected JPanel shopPanel=new JPanel();
    private Producto producto;
    private ProductoController pController;
    private ClienteController cController;
    private VentaController ventaController;
    private JScrollPane scrollPane;
    private int row,id;
    private double total2;
    private JTable table,tableProduct;
    private JLabel total,lblCodigoCliente;
    private DefaultTableModel model,modelProduct;
    private JTextField txtNombre,txtNit,txtCorreo,txtProducto,txtCantidad;
    private JComboBox cbnGenero;
    private JButton btnFiltro,btnAdd,btnShop,btnFind;
    
    
    public void execute(){
        init();
    }
    
    private void init(){
        globalPanel.setLayout(null);
        globalPanel.setBackground(Color.DARK_GRAY);
        
        filterPanel.setBackground(Color.LIGHT_GRAY);
        filterPanel.setLayout(null);
        filterPanel.setBounds(40, 20, 800, 150);
        globalPanel.add(filterPanel);
        
        shopPanel.setBackground(Color.LIGHT_GRAY);
        shopPanel.setLayout(null);
        shopPanel.setBounds(40, 180, 800, 285);
        globalPanel.add(shopPanel);
        
        initLabelsFilter(filterPanel);
        initTextFields(filterPanel);
        initButtons(filterPanel);
        
        producto=new Producto();
        total2=0;
        id=1;
        total=new JLabel();
        total.setText("Q. ");
        initLabelsShop(shopPanel);        
        initButtonsShop(shopPanel);
        initTable(shopPanel);
        initTableProduct(shopPanel);
        txtCantidad=new JTextField();
        txtCantidad.setText("");
        initTextFieldsShop(shopPanel);
    }
    
    private void initTable(JPanel panel){
        table=new JTable();
        model=new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NOMBRE");
        model.addColumn("CANTIDAD");
        model.addColumn("PRECIO");
        model.addColumn("SUBTOTAL");
        
        table.setModel(model);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(115);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        
        DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <=4 ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRender);
        }
        
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(350, 120, 400, 100);        
        panel.add(scrollPane);
    }
    
    private void initTableProduct(JPanel panel){
        tableProduct=new JTable();
        modelProduct=new DefaultTableModel();
        modelProduct.addColumn("ID");
        modelProduct.addColumn("PRODUCTO");
        modelProduct.addColumn("PRECIO");
        pController =new ProductoController();
        for(Producto p : pController.list()){
            Object[] obj={
                p.getCodigo(),p.getNombre(),p.getPrecio()
            };
            modelProduct.addRow(obj);
        }        
        tableProduct.setModel(modelProduct);
        
        tableProduct.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableProduct.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableProduct.getColumnModel().getColumn(1).setPreferredWidth(130);
        tableProduct.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableProduct.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        
        DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <=2 ; i++) {
            tableProduct.getColumnModel().getColumn(i).setCellRenderer(cellRender);
        }
        
        scrollPane=new JScrollPane(tableProduct);
        scrollPane.setBounds(50, 50, 265, 215);        
        panel.add(scrollPane);
        
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event){
                row=-2;
                tableProductMouseClicked(event);
            }
        });
    }
    
    public void tableProductMouseClicked(java.awt.event.MouseEvent evt){
            row = tableProduct.getSelectedRow(); 
            if(row >=0){
                producto=new Producto();
                producto.setCodigo(Integer.parseInt(tableProduct.getValueAt(row, 0).toString()));
                producto.setNombre(tableProduct.getValueAt(row, 1).toString());
                producto.setPrecio(Float.parseFloat(tableProduct.getValueAt(row, 2).toString()));
                btnFind.setEnabled(true);
                System.out.println("Producto: "+producto.getNombre());
                initTextFieldsShop(shopPanel);
            }
    }
    
    private void initButtons(JPanel panel){
        btnFiltro=new JButton("Filtrar");
        btnFiltro.setBounds(520, 100, 200, 30);
        panel.add(btnFiltro);
        ActionListener filter=(ActionEvent e) -> {
            filter();
        };
        btnFiltro.addActionListener(filter);
    }
    
    private void initLabelsFilter(JPanel panel){
        JLabel lblNombre=new JLabel("Nombre: ");
        lblNombre.setBounds(140, 20, 80, 30);
        panel.add(lblNombre);
        
        JLabel lblNit=new JLabel("Nit: ");
        lblNit.setBounds(450, 20, 80, 30);
        panel.add(lblNit);
        
        JLabel lblCorreo=new JLabel("Correo: ");
        lblCorreo.setBounds(140, 60, 80, 30);
        panel.add(lblCorreo);
        
        JLabel lblGenero=new JLabel("Genero: ");
        lblGenero.setBounds(450, 60, 80, 30);
        panel.add(lblGenero);
        
        JLabel lblCliente=new JLabel("Cliente: ");
        lblCliente.setBounds(140, 100, 80, 30);
        panel.add(lblCliente);
        
        lblCodigoCliente=new JLabel();
        lblCodigoCliente.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        lblCodigoCliente.setHorizontalAlignment(JLabel.CENTER);
        lblCodigoCliente.setBounds(210, 100, 280, 30);
        panel.add(lblCodigoCliente);

    }
    
    private void filter(){
        System.out.println("Pressed...");
        cController=new ClienteController();
        Cliente client=new Cliente();
        client=cController.clientByName(txtNombre.getText());
        lblCodigoCliente.setText(String.valueOf(client.getCodigo()));
        txtNombre.setText(client.getNombre());
        txtCorreo.setText(client.getCorreo());
        txtNit.setText(client.getNit());
        cbnGenero.setSelectedItem(client.getGenero());
    }
    
    private void initTextFields(JPanel panel){
        //cController=new ClienteController();
        txtNombre=new JTextField();
        txtNombre.setBounds(210, 20, 200, 30);
        panel.add(txtNombre);

        txtNit=new JTextField();
        txtNit.setBounds(520, 20, 200, 30);
        panel.add(txtNit);
        
        txtCorreo=new JTextField();
        txtCorreo.setBounds(210, 60, 200, 30);
        panel.add(txtCorreo);
        
        String genero[]={"F","M"};
        cbnGenero=new JComboBox(genero);
        cbnGenero.setBounds(520, 60, 200, 30);
        panel.add(cbnGenero); 
    }
    
    private void addProduts(){
        double subTotal=Integer.parseInt(txtCantidad.getText())*producto.getPrecio();
        total2+=subTotal;
        Object[] obj={id++,producto.getNombre(),
            Integer.parseInt(txtCantidad.getText()),producto.getPrecio(),
            subTotal};
        model.addRow(obj);
        table.setModel(model);
        this.total.setText("Q. "+total2+"");
    }
    
    private void shopProducts(){
        ventaController =new VentaController();
        Venta venta=new Venta();
        venta.setCodigoCliente(Integer.parseInt(lblCodigoCliente.getText()));
        venta.setFecha(java.sql.Date.valueOf(LocalDate.now()));
        venta.setTotal(total2);
        ventaController.create(venta);
    }
    
    private void initButtonsShop(JPanel panel){
        btnFind=new JButton("BUSCAR");
        btnFind.setEnabled(false);
        btnFind.setBounds(605, 20, 140, 30);
        panel.add(btnFind);
        
        btnAdd=new JButton("AGREGAR");
        btnAdd.setBounds(605, 65, 140, 30);
        panel.add(btnAdd);
        ActionListener add=(ActionEvent e) -> {
            addProduts();
        };
        btnAdd.addActionListener(add);
        
        btnShop=new JButton("VENDER");
        btnShop.setBounds(350, 240, 150, 30);
        panel.add(btnShop);
        ActionListener shop=(ActionEvent e) -> {
            shopProducts();
        };
        btnShop.addActionListener(shop);
    }
    
    private void initLabelsShop(JPanel panel){
        JLabel lblFecha=new JLabel("FECHA:    "+
                java.sql.Date.valueOf(LocalDate.now()));
        lblFecha.setBounds(50,15, 150, 30);
        panel.add(lblFecha);
        
        JLabel lblNo=new JLabel("NO: ");
        lblNo.setBounds(230,15, 80, 30);
        panel.add(lblNo);
        
        JLabel lblProducto=new JLabel("PRODUCTO: ");
        lblProducto.setBounds(350,20, 80, 30);
        panel.add(lblProducto);
        
        JLabel lblCantidad=new JLabel("CANTIDAD: ");
        lblCantidad.setBounds(350,65, 80, 30);
        panel.add(lblCantidad);
        
        JLabel lblTotal=new JLabel("TOTAL: ");
        lblTotal.setBounds(525,240, 80, 30);
        panel.add(lblTotal);
        
        total.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        total.setHorizontalAlignment(JLabel.CENTER);
        total.setBounds(570,240, 175, 30);
        panel.add(total);
    }
    
    private void initTextFieldsShop(JPanel panel){
        txtProducto=new JTextField();
        txtProducto.setText(producto.getNombre());
        txtProducto.setBounds(425, 20, 150, 30);
        panel.add(txtProducto);
        
        txtCantidad.setBounds(425, 65, 150, 30);
        panel.add(txtCantidad);
    }
}
