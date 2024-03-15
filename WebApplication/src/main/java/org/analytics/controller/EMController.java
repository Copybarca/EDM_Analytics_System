package org.analytics.controller;

import org.analytics.dao.EMDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/erosionMachines")
public class EMController {

    private EMDao emDao;
    @Autowired
    public EMController(EMDao emDao){
        this.emDao=emDao;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("er_machines_list",emDao.index());
        return "erosionMachines/index";
    }
}
