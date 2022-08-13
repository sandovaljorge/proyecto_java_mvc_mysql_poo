package com.latin.proyecto.Model;

/**
 *
 * @author Sandoval
 */
public class Cliente extends Patern{
   
    private String nit;
    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNit(){
        return nit;
    }
    
    public void setNit(String nit) {
        this.nit = nit;
    }
    
    
}
