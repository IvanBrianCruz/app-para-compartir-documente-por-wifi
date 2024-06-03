package com.example.proyecto;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileNotFoundException;

@Controller
public class FileController {

    private String ip = ":8080"; // Define tu IP aquí

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ip", ip);
        return "index";
    }


    @GetMapping("/programas")
    public String programas(Model model) {
        model.addAttribute("ip", ip);
        return "programas";
    }
    
    @GetMapping("/isos")
    public String isos(Model model) {
        model.addAttribute("ip", ip);
        return "isos";
    }
    @GetMapping("/acerca")
    public String acerca(Model model) {
        model.addAttribute("ip", ip);
        return "acerca de";
    }



    @RequestMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws FileNotFoundException {
        // Ruta completa del archivo
        String filePath = "C:\\Users\\NOBODY\\Desktop\\INSTALADORES\\iso\\" + filename;
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("El archivo no existe");
        }

        // Crear el recurso del archivo
        Resource resource = new FileSystemResource(file);

        // Establecer los encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

        // Devolver la respuesta con el archivo
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
