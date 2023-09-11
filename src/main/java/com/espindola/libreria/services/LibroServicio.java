package com.espindola.libreria.services;

import com.espindola.libreria.entidades.Autor;
import com.espindola.libreria.entidades.Editorial;
import com.espindola.libreria.entidades.Libro;
import com.espindola.libreria.excepciones.MiException;
import com.espindola.libreria.repositorios.AutorRepositorio;
import com.espindola.libreria.repositorios.EditorialRepositorio;
import com.espindola.libreria.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer Ejemplares, String idAutor, String idEditorial) throws MiException {
        
        //agregar optional a los autor/editorial/ y el libro
        
        
        validar(isbn, titulo, idAutor, idEditorial, Ejemplares);

        Autor autor = autorRepositorio.findById(idAutor).get();

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(Ejemplares);
        libro.setAlta(new Date());

        libro.setAutor(autor);

        libro.setEditorial(editorial);

        libroRepositorio.save(libro);

    }

    public List<Libro> listarLibro() {

        List<Libro> libros = new ArrayList<>();
        libros = libroRepositorio.findAll();
        //List<Libro> libro = libroRepositorio.findAll(); PROBAR
        //return libroRepositorio.findAll();
        return libros;
    }

    public void modificaLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> restapuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> restapuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (restapuestaAutor.isPresent()) {

            autor = restapuestaAutor.get();

        }
        
        if (restapuestaEditorial.isPresent()) {

            editorial = restapuestaEditorial.get();

        }

        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();

            libro.setTitulo(titulo);

            libro.setAutor(autor);

            libro.setEditorial(editorial);

            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);

        }

    }

    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {

        if (isbn == null) {
            throw new MiException("el isbn no puede estar vacio");
        }

        if (ejemplares == null) {
            throw new MiException("los ejemplares no puede estar vacio");
        }

        if ( titulo == null||titulo.isEmpty()) {
            throw new MiException("el titulo no puede estar vacio");
        }

        if (idAutor == null||idAutor.isEmpty() ) {
            throw new MiException("el autor no puede estar vacio");
        }

        if (idEditorial == null || idEditorial.isEmpty()) {
            throw new MiException("la editorial no puede estar vacio");
        }
    }

}
