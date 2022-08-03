package com.latin.proyecto.Service;

import com.latin.proyecto.Model.Persona;

/**
 *
 * @author Sandoval
 */
public interface IPersona {
    public void create(Persona persona);
    public void update(Persona persona);
    public void delete(int id);
}
