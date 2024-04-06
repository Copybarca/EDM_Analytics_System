package org.edm.controller;

import org.edm.dao.MachineEDMDAO;
import org.edm.models.MachineEDM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/edmMachine")
public class EDMController {

    private MachineEDMDAO edmDao;
    @Autowired
    public EDMController(MachineEDMDAO edmDao){
        this.edmDao = edmDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("machineList",edmDao.index());
        return"edmView/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("machine", edmDao.show(id));
        return"edmView/show";

    }

    @GetMapping("/new")
    public String newMachine(Model model){
        model.addAttribute("machine",new MachineEDM());
        return"edmView/newMachine";
    }
    @PostMapping()
    public String create(@ModelAttribute("machine")@Valid MachineEDM machine, BindingResult bindingResult){//Добавить валидацию в класс станка эрозионного
        if(bindingResult.hasErrors()){
            return"edmView/newMachine";
        }
        edmDao.save(machine);
        return "redirect:/edmMachine";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("machine", edmDao.show(id));
        return"edmView/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("machine")@Valid MachineEDM machine,BindingResult bindingResult,@PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return"edmView/edit";
        }
        edmDao.update(id,machine);
        return "redirect:/edmMachine";
    }
}
