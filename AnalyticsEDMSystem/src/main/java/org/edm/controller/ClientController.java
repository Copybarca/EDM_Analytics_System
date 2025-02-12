package org.edm.controller;

import org.edm.aop.timeLogging.annotations.LogMethodExecutionTime;
import org.edm.dao.ClientDAO;
import org.edm.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller()
@RequestMapping("/clients")
public class ClientController {
    private ClientDAO clientDAO;
    @Autowired
    public ClientController(ClientDAO clientDAO){this.clientDAO=clientDAO;}
    @LogMethodExecutionTime
    @GetMapping()
    public String index(Model model){
        model.addAttribute("clientList",clientDAO.index());
        return"clientsView/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("client",clientDAO.show(id));
        return"clientsView/show";
    }
    @GetMapping("/new")
    public String newClient(Model model){
        model.addAttribute("client",new Client());
        return"clientsView/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("client")@Valid Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return"clientsView/new";
        }
        clientDAO.save(client);
        return"redirect:/clients";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id")int id){
        model.addAttribute("client", clientDAO.show(id));
        return"clientsView/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client")@Valid Client client,BindingResult bindingResult, @PathVariable("id")int id){
        if(bindingResult.hasErrors()){
            return "clientsView/edit";
        }
        clientDAO.update(id,client);
        return"redirect:/clients";
    }

}
