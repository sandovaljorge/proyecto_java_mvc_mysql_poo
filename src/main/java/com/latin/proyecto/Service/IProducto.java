package com.latin.proyecto.Service;

import com.latin.proyecto.Model.Producto;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public interface IProducto {
    public ArrayList<Producto> list();
    public void create(Producto producto);
    public void update(Producto producto);
    public void delete(int codigo);
}
