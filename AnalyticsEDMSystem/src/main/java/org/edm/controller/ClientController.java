package org.edm.controller;

import org.edm.dao.ClientDAO;
import org.edm.loggerRedis.RedisLogger;
import org.edm.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller()
@RequestMapping("/clients")
public class ClientController {
    private ClientDAO clientDAO;
    private RedisLogger redisLogger;
    @Autowired
    public ClientController(ClientDAO clientDAO, RedisLogger redisLogger){
        this.clientDAO=clientDAO;
        this.redisLogger = redisLogger;
    }
    @GetMapping()
    public String index(Model model){
        List<Client> list = clientDAO.index();
        model.addAttribute("clientList",list);

        for(Client ob: list){
            String name = ob.getName();
            String info = ob.toString();
            redisLogger.saveString(name,info,100);
        }

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
