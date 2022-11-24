package com.zeropokel.springprojects.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeropokel.springprojects.tienda.model.Proveedor;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @RequestMapping(value = "/list")
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedores", getProveedores());
        modelAndView.setViewName("proveedores/list");

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Proveedor proveedor) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedor", new Proveedor());
        modelAndView.setViewName("proveedores/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Proveedor proveedor){

        List<Proveedor> proveedores = getProveedores();
        proveedores.add(proveedor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedores", proveedores);
        modelAndView.setViewName("proveedores/list");

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedor", getProveedor(codigo));
        modelAndView.setViewName("proveedores/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Proveedor proveedor) {

        List<Proveedor> proveedores = getProveedores();

        int indexOf = proveedores.indexOf(proveedor);

        proveedores.set(indexOf, proveedor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedores", proveedores);
        modelAndView.setViewName("proveedores/list");
        
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        List<Proveedor> proveedores = getProveedores();
        proveedores.remove(getProveedor(codigo));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedores", proveedores);
        modelAndView.setViewName("proveedores/list");

        return modelAndView;
    }

    private Proveedor getProveedor(int codigo) {

        List<Proveedor> proveedores = getProveedores();
        int indexOf = proveedores.indexOf(new Proveedor(codigo));

        return proveedores.get(indexOf);
    }

    private List<Proveedor> getProveedores() {

        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();

        proveedores.add(new Proveedor(1, "Miguel Angel", "Femenia Vazquez"));
        proveedores.add(new Proveedor(2, "Jose Carlos", "Lopez Lopez"));
        proveedores.add(new Proveedor(3, "Maria del Carmen", "Bellido Sanchez"));

        return proveedores;
    }
}
