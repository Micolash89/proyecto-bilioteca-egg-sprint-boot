
package com.espindola.libreria.controladores;

import com.espindola.libreria.entidades.Usuario;
import com.espindola.libreria.services.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/dashboard")
    public String dashBoard(){
        return "panel.html";
    }
 
    
    @GetMapping("/usuarios")
    public String listar(ModelMap modelo){
        
        List<Usuario> usuarios = usuarioServicio.getAll();
        
        modelo.addAttribute("usuarios",usuarios);
    
        return "usuario_list";
        
    }
    
     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id){
        
        usuarioServicio.cambiarRol(id);
        
        return "redirect:/admin/usuarios";
    
    }
    
}
