package com.latin.proyecto.Service;

import com.latin.proyecto.Model.Persona;
import java.util.ArrayList;

/**
 *
 * @author Sandoval
 */
public interface IPersona {
    public ArrayList<Persona> list();
    public void create(Persona persona);
    public void update(Persona persona);
    public void delete(int id);
}
