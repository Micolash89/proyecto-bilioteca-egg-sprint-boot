/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espindola.libreria.services;

import com.espindola.libreria.entidades.Imagen;
import com.espindola.libreria.excepciones.MiException;
import com.espindola.libreria.repositorios.ImagenRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JAVIER ESPINDOLA
 */
@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    public Imagen guardar(MultipartFile archivo) throws MiException {

        if (archivo != null) {

            try {

                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());

                imagen.setNombre(archivo.getName());

                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    public Imagen actualizar(MultipartFile archivo, String idImagen){
    
        if (archivo != null) {

            try {

                Imagen imagen = new Imagen();

                if(idImagen!=null){
                    
                    Optional <Imagen> respuesta = imagenRepositorio.findById(idImagen);
                    
                    if(respuesta.isPresent()){
                        
                        imagen = respuesta.get();
                    
                    }
                
                }
                
                imagen.setMime(archivo.getContentType());

                imagen.setNombre(archivo.getName());

                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
        
    }
    
}
