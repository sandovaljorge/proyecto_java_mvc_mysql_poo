package com.latin.proyecto.Service;

import com.latin.proyecto.Model.Vendedor;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public interface IVendedor {
    public ArrayList<Vendedor> list();
    public Vendedor getById(int codigo);
    public void create(Vendedor vendedor);
    public void update(Vendedor vendedor);
    public void delete(int codigo);
}
