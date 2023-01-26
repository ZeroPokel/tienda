package com.zeropokel.springprojects.tienda.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.zeropokel.springprojects.tienda.services.PedidosService;
import com.zeropokel.springprojects.tienda.services.ProductosService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    ClientesService clientesService;
    
    @Autowired
    PedidosService pedidosService;

    @Value("${pagination.size}")
    int sizePage;


    @GetMapping(value = "/list")
    public ModelAndView list(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:list/1/codigo/desc");
        return modelAndView;
    }
  
    @GetMapping(value = "/list/{numPage}/{fieldSort}/{directionSort}")
    public ModelAndView listPage(Model model,
            @PathVariable("numPage") Integer numPage,
            @PathVariable("fieldSort") String fieldSort,
            @PathVariable("directionSort") String directionSort) {


        Pageable pageable = PageRequest.of(numPage - 1, sizePage,
            directionSort.equals("asc") ? Sort.by(fieldSort).ascending() : Sort.by(fieldSort).descending());

        Page<Pedido> page = pedidosService.findAll(pageable);

        List<Pedido> pedidos = page.getContent();

        ModelAndView modelAndView = new ModelAndView("pedidos/list");
        modelAndView.addObject("pedidos", pedidos);


		modelAndView.addObject("numPage", numPage);
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("totalElements", page.getTotalElements());

		modelAndView.addObject("fieldSort", fieldSort);
		modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo, final Locale locale) {

        Pedido pedido = pedidosService.findByID(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pedido", pedido);

        modelAndView.setViewName("pedidos/edit");
        return modelAndView;
    }

    @GetMapping(path = { "/save" })
    public ModelAndView save(HttpSession session)
            throws IOException {

        Pedido pedido = (Pedido) session.getAttribute("pedido");
        
        pedidosService.save(pedido);
        session.removeAttribute("pedido");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:list");

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
        @PathVariable(name = "codigo", required = true) int codigo, HttpSession session)
        throws IOException {

            /*detallePedidosService.delete(codigo);*/
            pedidosService.delete(codigo);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/pedidos/list");;
            return modelAndView;
        }

}
