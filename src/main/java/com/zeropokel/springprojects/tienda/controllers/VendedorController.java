package com.zeropokel.springprojects.tienda.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.zeropokel.springprojects.tienda.model.Vendedor;
import com.zeropokel.springprojects.tienda.services.VendedoresService;

@Controller
@RequestMapping("/vendedores")
@PreAuthorize("hasAnyAuthority('ADMIN','VENDEDORES')")
public class VendedorController {

    @Autowired
    VendedoresService vendedoresService;

    @Value("${pagination.size}")
    int sizePage;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {
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

        Page<Vendedor> page = vendedoresService.findAll(pageable);

        List<Vendedor> vendedores = page.getContent();

        ModelAndView modelAndView = new ModelAndView("vendedores/list");
        modelAndView.addObject("vendedores", vendedores);


        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

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
    public ModelAndView save(Vendedor vendedor) throws IOException{

        vendedoresService.insert(vendedor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + vendedor.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Vendedor vendedor = vendedoresService.findByID(codigo);
                
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vendedor", vendedor);
        modelAndView.setViewName("vendedores/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Vendedor vendedor) {

        vendedoresService.update(vendedor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + vendedor.getCodigo());
        
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        vendedoresService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/vendedores/list");

        return modelAndView;
    }

}

