package com.latin.proyecto.Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sandoval
 */
public class Conexion {
    private Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/db_proyecto";
    private static final String USER = "sandoval";
    private static final String PASSWORD = "12345";

    public Connection getConexion() {
        return conexion;
    }

    public void setMiconexion(Connection miconexion) {
        this.conexion = miconexion;
    }

    public void openConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            //System.out.println("Conexión con exito");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en conexion" + ex);
        }
    }

    public void closeConexion() {
        try {
            if (conexion != null) {
                if (conexion.isClosed() == false) {
                    conexion.close();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar: " + ex);
        }
    }
}
