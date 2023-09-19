
package com.espindola.libreria.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorControlador implements ErrorController{
    
    @RequestMapping(value="/error", method={RequestMethod.GET , RequestMethod.POST})
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest){
        
        ModelAndView errorPage = new ModelAndView("error");
        
        String errorMag = "";
        
        int httpErrorCode = getErrorCode(httpRequest);
        
        switch(httpErrorCode){
        
            case 400:
            
                errorMag = "El recurso solicitado no existe.";
                break;
            
            
            case 403:
            
                errorMag = "No tiene permisos para acceder al recurso.";
                break;
            
            
            case 401:
            
                errorMag = "No se encuentra autorizado.";
                break;
            
            
            case 404:
            
                errorMag = "El recurso solicitado no fue encontrado.";
                break;
            
            
            case 500:
            
                errorMag = "Ocurrió un error interno.";
                break;
            
        
            default:
                errorMag = "Ocurrió un error interno. que no es ninguno de los anterirores";
            
        }
        
        errorPage.addObject("codigo",httpErrorCode);
        errorPage.addObject("mensaje",errorMag);
        
        return errorPage;
        
    }
    
    private int getErrorCode(HttpServletRequest httpRequest){
        
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    
    }
    
    public String getErrorPatch(){
        return "/error.html";
    }
    
}
