package com.latin.proyecto.View;

import com.latin.proyecto.Controller.VentaController;
import com.latin.proyecto.Model.Venta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
public class VentaView {
    protected JPanel globalPanel=new JPanel();
    protected JPanel filterPanel=new JPanel();
    protected JPanel shopPanel=new JPanel();
    private VentaController ventaController;
    private Venta venta;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtNombre,txtNit,txtFactura,txtFecha;
    private JButton btnFiltro;
    
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
        
        initButtons(filterPanel);
        
        txtNombre=new JTextField();
        txtNit=new JTextField();
        txtFecha=new JTextField();
        initTextFields(filterPanel);
        initTable();
    }
    
    private void initTable(){
        table=new JTable();
        model=new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NO. CLIENTE");
        model.addColumn("NOMBRE");
        model.addColumn("NIT");
        model.addColumn("FECHA");
        model.addColumn("TOTAL");
        
        ventaController=new VentaController();
        venta=new Venta();
        venta.setNombre(txtNombre.getText());
        venta.setNit(txtNit.getText());
        venta.setDate(txtFecha.getText());
        try{
            if(venta.getDate().equals("")){
                System.out.println("Date null");
            }
        }catch(NullPointerException e){
            System.out.println("Error ");
        }
        for(Venta v : ventaController.getByParam(venta)){
            Object[] obj={
                v.getCodigo(),v.getCodigoCliente(),v.getNombre(),
                v.getNit(),v.getFecha(),v.getTotal()
            };
            model.addRow(obj);
        }
        table.setModel(model);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(105);
        table.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        
        DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <=5 ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRender);
        }
        
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(70, 30, 650, 220);        
        shopPanel.add(scrollPane);
    }
    
    private void initTextFields(JPanel panel){        
        txtNombre.setBounds(210, 20, 200, 30);
        panel.add(txtNombre);

        txtNit.setBounds(520, 20, 200, 30);
        panel.add(txtNit);
        
        txtFactura=new JTextField();
        txtFactura.setBounds(210, 60, 200, 30);
        panel.add(txtFactura);

        txtFecha.setBounds(520, 60, 200, 30);
        panel.add(txtFecha);

    }
    
    private void initButtons(JPanel panel){
        btnFiltro=new JButton("FILTRAR");
        btnFiltro.setBounds(260, 100, 400, 30);
        panel.add(btnFiltro);
        ActionListener filter=(ActionEvent e) -> {
            initTable();         
        };
        btnFiltro.addActionListener(filter);
    }
    
    private void initLabelsFilter(JPanel panel){
        JLabel lblFiltro=new JLabel("Filtrado por: ");
        lblFiltro.setBounds(40, 20, 100, 30);
        panel.add(lblFiltro);
        
        JLabel lblNombre=new JLabel("Nombre: ");
        lblNombre.setBounds(140, 20, 80, 30);
        panel.add(lblNombre);
        
        JLabel lblNit=new JLabel("Nit: ");
        lblNit.setBounds(450, 20, 80, 30);
        panel.add(lblNit);
        
        JLabel lblFactura=new JLabel("No. Factura: ");
        lblFactura.setBounds(140, 60, 80, 30);
        panel.add(lblFactura);
        
        JLabel lblFecha=new JLabel("Fecha: ");
        lblFecha.setBounds(450, 60, 80, 30);
        panel.add(lblFecha);

    }
}
