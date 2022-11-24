package com.zeropokel.springprojects.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeropokel.springprojects.tienda.model.Vendedor;

@Controller
@RequestMapping("/vendedores")
public class VendedorController {

    @RequestMapping(value = "/list")
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vendedores", getVendedores());
        modelAndView.setViewName("vendedores/list");

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Vendedor vendedor) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vendedor", new Vendedor());
        modelAndView.setViewName("vendedores/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Vendedor vendedor){

        List<Vendedor> vendedores = getVendedores();
        vendedores.add(vendedor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vendedores", vendedores);
        modelAndView.setViewName("vendedores/list");

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vendedor", getVendedor(codigo));
        modelAndView.setViewName("vendedores/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Vendedor vendedor) {

        List<Vendedor> vendedores = getVendedores();

        int indexOf = vendedores.indexOf(vendedor);

        vendedores.set(indexOf, vendedor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vendedores", vendedores);
        modelAndView.setViewName("vendedores/list");
        
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        List<Vendedor> vendedores = getVendedores();
        vendedores.remove(getVendedor(codigo));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vendedores", vendedores);
        modelAndView.setViewName("vendedores/list");

        return modelAndView;
    }

    private Vendedor getVendedor(int codigo) {

        List<Vendedor> vendedores = getVendedores();
        int indexOf = vendedores.indexOf(new Vendedor(codigo));

        return vendedores.get(indexOf);
    }

    private List<Vendedor> getVendedores() {

        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

        vendedores.add(new Vendedor(1, "Miguel Angel", "Femenia Vazquez", "34"));
        vendedores.add(new Vendedor(2, "Jose Carlos", "Lopez Lopez", "2"));
        vendedores.add(new Vendedor(3, "Maria del Carmen", "Bellido Sanchez", "50"));

        return vendedores;
    }
}

