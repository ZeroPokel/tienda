package com.zeropokel.springprojects.tienda.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeropokel.springprojects.tienda.model.Contacto;
import com.zeropokel.springprojects.tienda.services.ContactosService;

@Controller
@RequestMapping("/contactos")
public class ContactosController {

    @Autowired
    ContactosService contactosService;

    @GetMapping(value = "/list")
    public ModelAndView listPage(Model model) {

        List<Contacto> contactos = contactosService.findAll();

        ModelAndView modelAndView = new ModelAndView("contactos/list");
        modelAndView.addObject("contactos", contactos);

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Contacto contacto) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contacto", new Contacto());
        modelAndView.setViewName("contactos/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Contacto contacto) throws IOException {

        Contacto ca = contactosService.insert(contacto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + ca.getId());

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{id}" })
    public ModelAndView edit(
            @PathVariable(name = "id", required = true) int id) {

        Contacto contacto = contactosService.findByID(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contacto", contacto);
        modelAndView.setViewName("contactos/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Contacto contacto) {

        contactosService.update(contacto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + contacto.getId());

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{id}" })
    public ModelAndView delete(
            @PathVariable(name = "id", required = true) int id) {

        contactosService.delete(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/contactos/list");

        return modelAndView;
    }

}
