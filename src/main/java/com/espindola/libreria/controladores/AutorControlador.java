/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espindola.libreria.controladores;

import com.espindola.libreria.excepciones.MiException;
import com.espindola.libreria.services.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author JAVIER ESPINDOLA
 */
@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar")
    public String registrar() {

        return "autor_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) throws MiException {
        try {

            autorServicio.crearAutor(nombre);
        } catch ( MiException e) {
           
            return "autor_form.html";
        }

        return "index.html";

        //System.out.println("nombre "+ nombre);
       // return "autor_form";

    }

}
