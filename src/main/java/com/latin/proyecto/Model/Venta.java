package com.latin.proyecto.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sandoval
 */
public class Venta {
    private int codigo;
    private int codigoCliente;
    private Date fecha;
    private String date;
    private double total;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Date getFecha() {
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        return fecha=java.sql.Date.valueOf(this.date);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
