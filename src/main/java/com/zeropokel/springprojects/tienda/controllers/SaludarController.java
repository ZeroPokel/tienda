/* package com.zeropokel.springprojects.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zeropokel.springprojects.tienda.model.Producto;

@Controller
@RequestMapping("/saludar")
public class SaludarController {
    
    @GetMapping(value={"/hola","/buenas"})
    public String hola(){
        return "hola";
    }

    @GetMapping(value={"/adios","/hastaluego"})
    public String adios(){
        return "adios";
    }

    @GetMapping(value="/inicio")
    public String inicio(Model model){
        model.addAttribute("productos", getProductos());
        return "indice";
    }

    @GetMapping(path = {"/producto"})
    public ModelAndView productos(        
        @RequestParam(name = "codigo", required = true) int codigo
    ){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("producto", getProducto(codigo));
        modelAndView.setViewName("producto");

        return modelAndView;
    }

    @GetMapping(value="product/{codigo}")
    public ModelAndView product(@PathVariable(name = "codigo", required = true) int codigo) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("producto", getProducto(codigo));
        modelAndView.setViewName("producto");

        return modelAndView;
    }

    private Producto getProducto(int codigo){

        List<Producto> productos = getProductos();
        int indexOf = productos.indexOf(new Producto(codigo));

        return productos.get(indexOf);
    }

    private List<Producto> getProductos(){

        ArrayList<Producto> productos = new ArrayList<Producto>();

        productos.add(new Producto(1, "Cocacola", "Esto es una Cocacola", "/img/coca-cola.jpg", null));
        productos.add(new Producto(2, "Pepsi", "Esto es una Pepsi", "/img/pepsi.jpg", null));
        productos.add(new Producto(3, "Fanta", "Esto es una Fanta", "/img/fanta.jpg", null));
        productos.add(new Producto(4, "7-up", "Esto es un Seven-Up", "/img/seven-up.jpg", null));

        return productos;
    }


}
 */