package com.zeropokel.springprojects.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeropokel.springprojects.tienda.model.Producto;
import com.zeropokel.springprojects.tienda.services.ProductosService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    ProductosService productosService;

    @RequestMapping(value = "/list")
    public ModelAndView list(Model model) {

        List<Producto> productos = productosService.findAll();

        ModelAndView modelAndView = new ModelAndView("productos/list");
        modelAndView.addObject("productos", productos);
        modelAndView.addObject("title", "productos");

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Producto producto) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("producto", new Producto());
        modelAndView.setViewName("productos/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Producto producto){

        productosService.insert(producto);
        List<Producto> productos = productosService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productos", productos);
        modelAndView.setViewName("productos/list");

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Producto producto = productosService.findByID(codigo);
                
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("producto", producto);
        modelAndView.setViewName("productos/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Producto producto) {

        productosService.update(producto);
        List<Producto> productos = productosService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productos", productos);
        modelAndView.setViewName("productos/list");
        
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        productosService.delete(codigo);
        List<Producto> productos = productosService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productos", productos);
        modelAndView.setViewName("productos/list");

        return modelAndView;
    }
    
}
