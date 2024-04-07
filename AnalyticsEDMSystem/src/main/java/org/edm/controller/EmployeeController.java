package org.edm.controller;

import org.edm.dao.EmployeeDAO;
import org.edm.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeDAO empdao;

    @Autowired
    public EmployeeController(EmployeeDAO empdao) {
        this.empdao = empdao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("employeeList", empdao.index());
        return "employeeView/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("employee",empdao.show(id));
        return "employeeView/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("employee",empdao.show(id));
        return"employeeView/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee")@Valid Employee employee, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "employeeView/edit";
        }
        empdao.update(id, employee);
        return"redirect:/employee";
    }
    @GetMapping("/new")
    public String newEmployee(Model model){
        model.addAttribute("employee",new Employee());
        return"employeeView/newEmployee";
    }
    @PostMapping()
    public String create(@ModelAttribute("employee")@Valid Employee employee,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return"employeeView/newEmployee";
        }
        empdao.save(employee);
        return"redirect:/employee";
    }

}
