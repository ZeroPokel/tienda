package com.zeropokel.springprojects.tienda.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeropokel.springprojects.tienda.model.Departamento;
import com.zeropokel.springprojects.tienda.model.Empleado;
import com.zeropokel.springprojects.tienda.services.DepartamentosService;
import com.zeropokel.springprojects.tienda.services.EmpleadosService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentosService departamentosService;

    @Autowired
    EmpleadosService empleadosService;

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

        Page<Departamento> page = departamentosService.findAll(pageable);

        List<Departamento> departamentos = page.getContent();

        ModelAndView modelAndView = new ModelAndView("departamentos/list");
        modelAndView.addObject("departamentos", departamentos);

        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Departamento departamento) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departamento", new Departamento());
        modelAndView.setViewName("departamentos/create");

        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Departamento departamento) throws IOException {


        departamentosService.insert(departamento);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + departamento.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Departamento departamento = departamentosService.findByID(codigo);
        List<Empleado> empleados = empleadosService.findAll();
        List<Empleado> empleadosDepartamento = departamento.getEmpleados();

        for (Empleado empleado : empleados){
            for (Empleado empDep : empleadosDepartamento){
                if (empleado.getCodigo() == empDep.getCodigo()){
                    empleado.setPerteneceDepartamento(true);
                } else {
                    empleado.setPerteneceDepartamento(false);
                }
            }
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departamento", departamento);
        modelAndView.addObject("empleados", empleados);
        modelAndView.setViewName("departamentos/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Departamento departamento) {

        departamentosService.update(departamento);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + departamento.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        departamentosService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/departamentos/list");

        return modelAndView;
    }
}
