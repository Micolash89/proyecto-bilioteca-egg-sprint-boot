/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espindola.libreria.controladores;

import com.espindola.libreria.excepciones.MiException;
import com.espindola.libreria.services.AutorServicio;
import com.espindola.libreria.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author JAVIER ESPINDOLA
 */
@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialService editorialServicio;

    @GetMapping("/registrar")
    public String registrar() {

        return "editorial_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {

            editorialServicio.crearEditorial(nombre);
            modelo.put("exito", "El editorial fue cargado ok");
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "editorial_form.html";
        }

        return "index.html";
    }
}
