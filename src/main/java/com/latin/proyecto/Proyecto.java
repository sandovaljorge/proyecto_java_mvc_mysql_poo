package com.latin.proyecto;


import com.latin.proyecto.View.LoginView;



/**
 *
 * @author Sandoval
 */
public class Proyecto {

    public static void main(String[] args) {
//        Conexion con=new Conexion();
//        try {
//            con.openConexion();
//        } catch (SQLException ex) {
//            System.out.println("Error "+ex);
//        }finally{
//            con.closeConexion();
//        }
        LoginView login=new LoginView();
        login.setVisible(true);
//        UsuarioController c=new UsuarioController();
//        Usuario u=new Usuario();
//        u.setUsuario("jsandoval");
//        u.setPassword("123456");
//        if(c.login(u)!=null){
//            System.out.println("Codigo: "+c.login(u).getCodigo()+" "
//                + "Nombre: "+c.login(u).getUsuario()+" "
//                + "Fecha: "+c.login(u).getFechaCreacion()+" "
//                + "Estado: "+c.login(u).getEstado());
//        }else{
//            System.out.println("Usuario o contraseña incorrecta.");
//        }
    }
}
