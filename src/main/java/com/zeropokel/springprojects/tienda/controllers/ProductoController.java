package com.zeropokel.springprojects.tienda.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.zeropokel.springprojects.tienda.model.DetallePedido;
import com.zeropokel.springprojects.tienda.model.Pedido;
import com.zeropokel.springprojects.tienda.model.Producto;
import com.zeropokel.springprojects.tienda.services.ProductosService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    ProductosService productosService;

    @Value("${pagination.size}")
    int sizePage;
    
    @GetMapping(value = "/list")
    public ModelAndView list(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:list/1/codigo/asc");
        return modelAndView;
    }
  
    @GetMapping(value = "/list/{numPage}/{fieldSort}/{directionSort}")
    public ModelAndView listPage(Model model,
            @PathVariable("numPage") Integer numPage,
            @PathVariable("fieldSort") String fieldSort,
            @PathVariable("directionSort") String directionSort) {


        Pageable pageable = PageRequest.of(numPage - 1, sizePage,
            directionSort.equals("asc") ? Sort.by(fieldSort).ascending() : Sort.by(fieldSort).descending());

        Page<Producto> page = productosService.findAll(pageable);

        List<Producto> productos = page.getContent();

        ModelAndView modelAndView = new ModelAndView("productos/list");
        modelAndView.addObject("productos", productos);


        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

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
    public ModelAndView save(Producto producto, 
        @RequestParam("imageForm") MultipartFile multipartFile) throws IOException{
        
        byte[] imagen = multipartFile.getBytes();

        producto.setImg(imagen);

        productosService.insert(producto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + producto.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {
        Producto producto = productosService.findByID(codigo);
                
        boolean cesta = true;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cesta", cesta);
        modelAndView.addObject("producto", producto);
        modelAndView.setViewName("productos/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Producto producto, 
        @RequestParam("imageForm") MultipartFile multipartFile) throws IOException {

        byte[] imagen = multipartFile.getBytes();

        producto.setImg(imagen);

        productosService.update(producto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + producto.getCodigo());
        
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        productosService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/productos/list");

        return modelAndView;
    }

    @GetMapping(value = "/addcesta/{codigo}/{cantidad}")
    public ModelAndView addCliente(
        @PathVariable(name = "codigo", required = true) int codigo, @PathVariable(name = "cantidad", required = true) int cantidad,HttpSession session) {

            Producto producto = productosService.findByID(codigo);

            Pedido pedido = (Pedido) session.getAttribute("pedido");

            if(pedido == null){
                pedido = new Pedido();
            }

            if(pedido.getDetallepedidos() == null){
                List<DetallePedido> detallePedidos = new ArrayList<DetallePedido>();
                pedido.setDetallepedidos(detallePedidos);
            }

            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setSubtotal(cantidad*producto.getPrecio());
            detalle.setPedido(pedido);
            pedido.getDetallepedidos().add(detalle);

            session.setAttribute("pedido", pedido);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/cesta/edit");
            return modelAndView;
    }
    
}
