package com.espindola.libreria.services;

import com.espindola.libreria.entidades.Editorial;
import com.espindola.libreria.excepciones.MiException;
import com.espindola.libreria.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {

    @Autowired
    EditorialRepositorio editorialRespositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {

        validar(nombre);

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRespositorio.save(editorial);
    }

    public List<Editorial> listarEditorial() {

        //probar el otro modo pro
        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRespositorio.findAll();

        
        System.out.println("---------------------------------------------------***************************************************");
        editoriales.forEach((e)->System.out.println(e));
        
        
        
        return editoriales;
    }

    public void modificarEditorial(String nombre, String id) throws MiException {

        validar(nombre);

        Optional<Editorial> respuesta = editorialRespositorio.findById(id);

        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRespositorio.save(editorial);

        }

    }

    private void validar(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {

            throw new MiException("el nombre no puede estar vacio");

        }
    }

}
