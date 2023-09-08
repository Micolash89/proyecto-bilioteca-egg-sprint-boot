/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espindola.libreria.controladores;

import com.espindola.libreria.entidades.Autor;
import com.espindola.libreria.entidades.Editorial;
import com.espindola.libreria.excepciones.MiException;
import com.espindola.libreria.services.AutorServicio;
import com.espindola.libreria.services.EditorialService;
import com.espindola.libreria.services.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialService editorialServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {
    //public String registrar() {

                System.out.println("---------------------------------------------------***************************************************");
        
        
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditorial();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";
       
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
            @RequestParam String idEditorial, ModelMap modelo) {

        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado ok");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditorial();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            //Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libro_form.html";
        }
        return "index.html";
    }
}
