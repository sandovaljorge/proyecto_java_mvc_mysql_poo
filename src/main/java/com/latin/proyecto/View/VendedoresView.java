package com.latin.proyecto.View;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.latin.proyecto.Controller.VendedorController;
import com.latin.proyecto.Model.Vendedor;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sandoval
 */
public class VendedoresView {
    protected JPanel panelVendedores=new JPanel();
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private int row;
    private VendedorController vendedorController;
    private Vendedor vendedor;
    
    public void execute(){
        init();
    }
    
    private void init(){
        panelVendedores.setLayout(null);
        panelVendedores.setBackground(Color.gray);
        initButtons();
        initTable();
    }
    
    private void initButtons(){
        JButton btnCreate=new JButton("Crear");
        btnCreate.setBounds(500, 25, 150, 50);
        panelVendedores.add(btnCreate);
        
        JButton btnLoad=new JButton("Cargar");
        btnLoad.setBounds(700, 25, 150, 50);
        panelVendedores.add(btnLoad);
        
        JButton btnUpdate=new JButton("Actualizar");
        btnUpdate.setBounds(500, 100, 150, 50);
        panelVendedores.add(btnUpdate);
        
        JButton btnDelete=new JButton("Eliminar");
        btnDelete.setBounds(700, 100, 150, 50);
        panelVendedores.add(btnDelete);
        
        JButton btnExport=new JButton("Exportar a PDF");
        btnExport.setBounds(500, 180, 350, 50);
        panelVendedores.add(btnExport);
        ActionListener export=(var e) -> {
            try {
                pdf();
            } catch (FileNotFoundException | DocumentException ex) {
                Logger.getLogger(ClientesView.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        btnExport.addActionListener(export);
    }
    
    private void initTable(){
        table=new JTable();
        model=new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NOMBRE");
        model.addColumn("CAJA");
        model.addColumn("VENTAS");
        model.addColumn("GENERO");
        vendedorController =new VendedorController();
        for(Vendedor v : vendedorController.list()){
            Object[] obj= {
                v.getCodigo(),v.getNombre(),v.getCaja(),
                v.getVenta(),v.getGenero()
            };
            model.addRow(obj);
        }
        table.setModel(model);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(45);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        
        DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <=4 ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRender);
        }
        
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 20, 450, 450);
        panelVendedores.add(scrollPane);

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
            vendedor=new Vendedor();
            vendedor.setCodigo(Integer.parseInt(table.getValueAt(row, 0).toString()));
            vendedor.setNombre(table.getValueAt(row, 1).toString());
            vendedor.setCaja(Integer.parseInt(table.getValueAt(row, 2).toString()));
            vendedor.setVenta(Integer.parseInt(table.getValueAt(row, 3).toString()));
            vendedor.setGenero(table.getValueAt(row, 4).toString());
            
        }
    }
    
    public void pdf() throws FileNotFoundException, DocumentException {

        FileOutputStream gen = new FileOutputStream("Vendedores.pdf");
        Document documento = new Document();

        PdfWriter.getInstance(documento, gen);
        documento.open();

        Paragraph parrafo = new Paragraph("Reporte de Vendedores Base de datos");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph("\n"));
        vendedorController=new VendedorController();
        try {
            for(Vendedor v : vendedorController.list()){
                documento.add(new Paragraph("CODIGO: " + v.getCodigo()));
                documento.add(new Paragraph("NOMBRE: " + v.getNombre()));
                documento.add(new Paragraph("NIT: " + v.getCaja()));
                documento.add(new Paragraph("CORREO: " + v.getCaja()));
                documento.add(new Paragraph("GENERO: " + v.getGenero()));
                documento.add(new Paragraph("\n\n"));
            }
        } catch (Exception e) {
            System.out.println("Errorr: "+e);
        }

        documento.close();
        JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
        try {
            File sucursales_doc = new File("Vendedores.pdf");
            Desktop.getDesktop().open(sucursales_doc);
        } catch (Exception e) {
            System.out.println("Errer: "+e);
        }
    }
}
