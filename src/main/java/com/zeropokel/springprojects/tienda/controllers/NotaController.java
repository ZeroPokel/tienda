package com.zeropokel.springprojects.tienda.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zeropokel.springprojects.tienda.model.Nota;
import com.zeropokel.springprojects.tienda.services.NotasService;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'NOTAS')")
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    NotasService notasService;
    
    @GetMapping(value = "/list")
    public ModelAndView listPage(Model model) {

        List<Nota> notas = notasService.findAll();

        ModelAndView modelAndView = new ModelAndView("notas/list");
        modelAndView.addObject("notas", notas);

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Nota nota) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nota", new Nota());
        modelAndView.setViewName("notas/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Nota nota) throws IOException {

        Nota nt = notasService.insert(nota);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + nt.getId());

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{id}" })
    public ModelAndView edit(
            @PathVariable(name = "id", required = true) int id) {

        Nota nota = notasService.findByID(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nota", nota);
        modelAndView.setViewName("notas/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Nota nota) {

        notasService.update(nota);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + nota.getId());

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{id}" })
    public ModelAndView delete(
            @PathVariable(name = "id", required = true) int id) {

        notasService.delete(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/notas/list");

        return modelAndView;
    }

    @GetMapping(value="/buscar")
    public ModelAndView findByCriteria(@RequestParam("titulo") String titulo,
        @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date fecha) {

        List<Nota> notasEncontradas = notasService.findByCriteria(titulo, fecha);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("notas", notasEncontradas);
        modelAndView.setViewName("notas/list");
        
        return modelAndView;
    }
}
