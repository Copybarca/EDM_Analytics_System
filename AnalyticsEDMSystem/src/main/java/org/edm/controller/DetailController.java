package org.edm.controller;

import org.edm.dao.DetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/details")
public class DetailController {

    DetailDAO detailDAO;
    public DetailController(){}
    @Autowired
    public DetailController(DetailDAO detailDAO){
        this.detailDAO = detailDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("detailList",detailDAO.index());
        return"detailView/index";

    }
}
