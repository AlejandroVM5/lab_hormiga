package com.example.hormiga.controller;

import com.example.hormiga.dto.ConfiguracionDto;
import com.example.hormiga.dto.TableroDto;
import com.example.hormiga.model.Simulacion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hormiga")
@SessionAttributes("simulacion")
public class HormigaController {

    @GetMapping("/configurar")
    public String mostrarConfiguracion(Model model) {
        model.addAttribute("config", new ConfiguracionDto());
        return "index";
    }

    @PostMapping("/iniciar")
    public String iniciarSimulacion(@ModelAttribute("config") ConfiguracionDto config, Model model) {
        try {
            // Reemplazamos posibles 'x', '-' o espacios por comas para evitar errores de tipeo
            String dimStr = config.getDimension().replaceAll("[xX\\- ]", ",");
            String[] dim = dimStr.split(",");
            int rows = Integer.parseInt(dim[0].trim());
            int cols = Integer.parseInt(dim[1].trim());

            String posStr = config.getPosicionInicial().replaceAll("[xX\\- ]", ",");
            String[] pos = posStr.split(",");
            int startY = Integer.parseInt(pos[0].trim()) - 1; 
            int startX = Integer.parseInt(pos[1].trim()) - 1;

            Simulacion simulacion = new Simulacion(
                    rows, cols, startX, startY, 
                    config.getDireccionInicial().charAt(0), 
                    config.getAumentoVision()
            );

            // Guardamos en la sesión
            model.addAttribute("simulacion", simulacion);
            model.addAttribute("pasos", config.getMovimientosVista());

            return "redirect:/hormiga/simulador";
            
        } catch (Exception e) {
            // Si el usuario escribe mal (ej: "5" en vez de "5,5")
            model.addAttribute("error", "Error de formato. Usa comas (ej: 5,5) para la Dimensión y Posición.");
            return "index";
        }
    }

    @GetMapping("/simulador")
    public String simulador(@ModelAttribute("simulacion") Simulacion simulacion, Model model) {
        // Envolvemos el estado en el DTO para mandarlo a la vista
        TableroDto dto = new TableroDto(
                simulacion.getMatriz(), 
                simulacion.getAntX(), 
                simulacion.getAntY(), 
                String.valueOf(simulacion.getAntDir())
        );
        model.addAttribute("tablero", dto);
        return "simulacion";
    }

    @PostMapping("/avanzar")
    public String avanzar(@ModelAttribute("simulacion") Simulacion simulacion, @RequestParam("pasos") int pasos) {
        simulacion.avanzar(pasos);
        return "redirect:/hormiga/simulador";
    }
}
