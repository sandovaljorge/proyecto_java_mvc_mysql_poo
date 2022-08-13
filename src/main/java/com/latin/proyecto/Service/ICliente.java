
package com.latin.proyecto.Service;

import com.latin.proyecto.Model.Cliente;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public interface ICliente {
    public ArrayList<Cliente> list();
    public ArrayList<Cliente> list(String parameter);
    public Cliente clientByName(String parameter);
    public Cliente clienteById(int codigo);
    public void create(Cliente cliente);
    public void update(Cliente cliente);
    public void delete(int codigo);
}
