package com.latin.proyecto.Service;

import com.latin.proyecto.Model.Venta;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public interface IVenta {
    public void create(Venta venta);
    public ArrayList<Venta> list();
    public ArrayList<Venta> getByParam(Venta parameter);
}
