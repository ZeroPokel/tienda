package com.zeropokel.springprojects.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeropokel.springprojects.tienda.model.Cliente;
import com.zeropokel.springprojects.tienda.model.DetallePedido;
import com.zeropokel.springprojects.tienda.model.Pedido;
import com.zeropokel.springprojects.tienda.model.Producto;
import com.zeropokel.springprojects.tienda.services.ClientesService;
import com.zeropokel.springprojects.tienda.services.ProductosService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    

    @Autowired
    ClientesService clientesService;

    @Autowired
    ProductosService productosService;

    @GetMapping(path = { "/comprar/{codigo}"})
    public ModelAndView comprar(
        @PathVariable(name = "codigo", required = true) int codigo, HttpSession session){

            Producto producto = productosService.findByID(codigo);
            boolean cesta = false;
            
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("cesta", cesta);
            modelAndView.addObject("producto", producto);
            modelAndView.setViewName("productos/edit");

        return modelAndView;
    }

    @GetMapping(path = { "/facturar/{codigo}"})
    public ModelAndView facturar(
        @PathVariable(name = "codigo", required = true) int codigo, HttpSession session)
        {

            Cliente cliente = (Cliente) session.getAttribute("cliente");
            Producto producto = productosService.findByID(codigo);

            DetallePedido detallePedido = new DetallePedido();

            detallePedido.setProducto(producto);
            
            ArrayList<DetallePedido> pedidoArray = new ArrayList<>();

            if (session.getAttribute("pedidoTotal") != null){
                pedidoArray = (ArrayList<DetallePedido>) session.getAttribute("pedidoTotal");
            }
            pedidoArray.add(detallePedido);

            session.setAttribute("pedidoTotal", pedidoArray);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("pedidoTotal", pedidoArray);
            modelAndView.addObject("cliente", cliente);
            modelAndView.setViewName("pedidos/cesta");

        return modelAndView;
    }

    @GetMapping(value = "/cesta")
    public ModelAndView cesta(Model model){
        ModelAndView modelAndView = new ModelAndView("cesta");

        return modelAndView;
    }
}
