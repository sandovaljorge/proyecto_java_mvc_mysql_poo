package com.latin.proyecto.Service;

import com.latin.proyecto.Model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public interface IUsuario {
    public ArrayList<Usuario> list();
    public Usuario getById(int codigo);
    public int login(Usuario usuario);
    public void create(Usuario usuario);
    public void update(Usuario usuario);
    public void delete(int id);
}
