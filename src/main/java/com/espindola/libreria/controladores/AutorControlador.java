package com.espindola.libreria.controladores;

import com.espindola.libreria.entidades.Autor;
import com.espindola.libreria.excepciones.MiException;
import com.espindola.libreria.services.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {

            autorServicio.crearAutor(nombre);
            modelo.put("exito", "El libro fue cargado ok");
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "autor_form.html";
        }

        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
    
        List<Autor> autores = autorServicio.listarAutores();
        
        modelo.addAttribute("autores", autores);
        
        return "autor_list.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("autor",autorServicio.getOne(id));
        
        return "autor_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,String nombre, ModelMap modelo){
    
        try {
            autorServicio.modificarAutor(nombre, id);
            modelo.put("exito","se modifico correctamente");
            return "redirect:../lista";
        } catch (MiException ex) {
             modelo.put("autor",autorServicio.getOne(id));
        
            modelo.put("error",ex.getMessage());
            return "autor_modificar.html";
        }
    
    }
    
}