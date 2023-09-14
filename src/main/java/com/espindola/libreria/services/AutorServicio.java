package com.espindola.libreria.services;

import com.espindola.libreria.entidades.Autor;
import com.espindola.libreria.excepciones.MiException;
import com.espindola.libreria.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {

        validar(nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutores() {

        
        List<Autor> autores = new ArrayList();

        autores = autorRepositorio.findAll();
        
        return autores;
    }

    public void modificarAutor(String nombre, String id) throws MiException {

        validar(nombre);

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);

        }

    }

    public Autor getOne(String id) {
        
        //return autorRepositorio.getOne(id);
        return autorRepositorio.findById(id).get();
        
    }

    private void validar(String nombre) throws MiException {

        if (nombre.trim().isEmpty() || nombre == null) {

            throw new MiException("el nombre no puede estar vacio");

        }
    }

}
