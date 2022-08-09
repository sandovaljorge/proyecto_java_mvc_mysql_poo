package com.latin.proyecto.View;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.latin.proyecto.Controller.ClienteController;
import com.latin.proyecto.Model.Cliente;
import java.awt.Color;
import java.io.File;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ClientesView {
    protected JPanel panelClientes=new JPanel();
    private JScrollPane scrollPane;
    private ClienteController clienteController;
    private Cliente cliente;
    private JTable table;
    private DefaultTableModel model;
    private int row,role;
    private JButton btnCreate,btnLoad,btnUpdate,btnDelete,btnExport;
    
    public void execute(int role){
        this.role=role;
        init();
    }
    
    private void init(){
        panelClientes.setLayout(null);
        panelClientes.setBackground(Color.gray);
        initButtons();
        initTable();
    }
    
    private void initButtons(){
        
        btnCreate=new JButton("Crear");
        btnCreate.setBounds(500, 25, 150, 50);
        panelClientes.add(btnCreate);
        ActionListener create=(ActionEvent e) -> {
            this.cliente=null;
//            create(model,row);
        };
        btnCreate.addActionListener(create);
        
        btnLoad=new JButton("Cargar");
        btnLoad.setEnabled(false);
        btnLoad.setBounds(700, 25, 150, 50);
        panelClientes.add(btnLoad);
        
        btnUpdate=new JButton("Actualizar");
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(500, 100, 150, 50);
        panelClientes.add(btnUpdate);
        
        btnDelete=new JButton("Eliminar");
        btnDelete.setEnabled(false);
        btnDelete.setBounds(700, 100, 150, 50);
        panelClientes.add(btnDelete);
        
        btnExport=new JButton("Exportar a PDF");
        btnExport.setEnabled(false);
        btnExport.setBounds(500, 180, 350, 50);
        panelClientes.add(btnExport);
        ActionListener export=(ActionEvent e) -> {
            try {
                pdf();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClientesView.class.getName()).log(Level.ALL.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(ClientesView.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        btnExport.addActionListener(export);
        
        if(role==1){
            btnExport.setEnabled(true);
            btnLoad.setEnabled(true);
        }else{
            btnExport.setEnabled(false);
            btnLoad.setEnabled(false);
        }
    }
    
    private void initTable(){
        table=new JTable();
        model=new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NOMBRE");
        model.addColumn("NIT");
        model.addColumn("CORREO");
        model.addColumn("GENERO");
        clienteController =new ClienteController();
        for(Cliente c : clienteController.list()){
            Object[] obj= {
                c.getCodigo(),c.getNombre(),c.getNit(),
                c.getCorreo(),c.getGenero()
            };
            model.addRow(obj);
        }
        table.setModel(model);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(137);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        
        DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <=4 ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRender);
        }
        
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 20, 450, 450);
        panelClientes.add(scrollPane);

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
            cliente = new Cliente();
            cliente.setCodigo(Integer.parseInt(table.getValueAt(row, 0).toString()));
            cliente.setNombre(table.getValueAt(row, 1).toString());
            cliente.setNit(table.getValueAt(row, 2).toString());
            cliente.setCorreo(table.getValueAt(row, 3).toString());
            cliente.setGenero(table.getValueAt(row, 4).toString());
            if(role==1){
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
            }else{
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
            }
        }
    }
    
    public void pdf() throws FileNotFoundException, DocumentException {

        FileOutputStream gen = new FileOutputStream("Clientes.pdf");
        Document documento = new Document();

        PdfWriter.getInstance(documento, gen);
        documento.open();

        Paragraph parrafo = new Paragraph("Reporte de Clientes Base de datos");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph("\n"));
        clienteController=new ClienteController();
        try {
            for(Cliente c : clienteController.list()){
                documento.add(new Paragraph("CODIGO: " + c.getCodigo()));
                documento.add(new Paragraph("NOMBRE: " + c.getNombre()));
                documento.add(new Paragraph("NIT: " + c.getNit()));
                documento.add(new Paragraph("CORREO: " + c.getCorreo()));
                documento.add(new Paragraph("GENERO: " + c.getGenero()));
                documento.add(new Paragraph("\n\n"));
            }
        } catch (Exception e) {
            System.out.println("Errorr: "+e);
        }

        documento.close();
        JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
        try {
            File sucursales_doc = new File("Clientes.pdf");
            Desktop.getDesktop().open(sucursales_doc);
        } catch (Exception e) {
            System.out.println("Errer: "+e);
        }
    }
}
