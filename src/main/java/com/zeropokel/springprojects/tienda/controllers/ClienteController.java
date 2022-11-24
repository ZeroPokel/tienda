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

import com.zeropokel.springprojects.tienda.model.Cliente;
import com.zeropokel.springprojects.tienda.services.ClientesService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClientesService clientesService;

    @RequestMapping(value = "/list")
    public ModelAndView list(Model model) {

        List<Cliente> clientes = clientesService.findAll();

        ModelAndView modelAndView = new ModelAndView("clientes/list");
        modelAndView.addObject("clientes", clientes);
        modelAndView.addObject("title", "clientes");

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Cliente cliente) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cliente", new Cliente());
        modelAndView.setViewName("clientes/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Cliente cliente){

        clientesService.insert(cliente);
        List<Cliente> clientes = clientesService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("clientes/list");

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Cliente cliente = clientesService.findByID(codigo);
                
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cliente", cliente);
        modelAndView.setViewName("clientes/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Cliente cliente) {

        clientesService.update(cliente);
        List<Cliente> clientes = clientesService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("clientes/list");
        
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        clientesService.delete(codigo);
        List<Cliente> clientes = clientesService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("clientes/list");

        return modelAndView;
    }

    /* Metodo sin usar bases de datos

        private Cliente getCliente(int codigo) {

            List<Cliente> clientes = getClientes();
            int indexOf = clientes.indexOf(new Cliente(codigo));

            return clientes.get(indexOf);
        }

        private List<Cliente> getClientes() {

            ArrayList<Cliente> clientes = new ArrayList<Cliente>();

            clientes.add(new Cliente(1, "Miguel Angel", "Femenia Vazquez", "777888999", "Calle Falsa 123", false));
            clientes.add(new Cliente(2, "Jose Carlos", "Lopez Lopez", "555666777", "Calle España 82", true));
            clientes.add(new Cliente(3, "Maria del Carmen", "Bellido Sanchez", "333444555", "Calle Numancia 2", false));

            return clientes;
        }
        
    */
}
