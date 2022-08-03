package com.latin.proyecto.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sandoval
 */
public class Usuario {
    private int codigo;
    private String usuario;
    private String password;
    private Date fechaCreacion;
    private short role;
    private boolean estado;
    private String date;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaCreacion() {
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(fechaCreacion);
        return fechaCreacion=java.sql.Date.valueOf(this.date);
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public short getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
