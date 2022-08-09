
package com.latin.proyecto.Service;

import com.latin.proyecto.Model.Sucursal;
import java.util.List;


/**
 *
 * @author Sandoval
 */
public interface ISucursal {
    public List<Sucursal> list();
    public void create(Sucursal sucursal);
    public void update(Sucursal sucursal);
    public void delete(int codigo);
}
