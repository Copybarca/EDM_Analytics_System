package org.edm.controller;

import org.edm.dao.MachineEDMDAO;
import org.edm.models.MachineEDM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
