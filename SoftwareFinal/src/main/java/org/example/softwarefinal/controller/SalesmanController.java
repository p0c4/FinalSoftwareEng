package org.example.softwarefinal.controller;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.softwarefinal.entity.ItemType;
import org.example.softwarefinal.entity.Salesman;
import org.example.softwarefinal.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class SalesmanController {

    @Autowired
    private SalesmanRepository salesmanRepository;

    @GetMapping(path = "/index")
    public String summaries(Model model) {

        List<Salesman> salesmans = salesmanRepository.findAll();
        model.addAttribute("salesmans", salesmans);
        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("currentDate", LocalDate.now().toString());

        return"main";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        salesmanRepository.deleteById(id);
        return "redirect:/index";
    }
    @GetMapping("/editSales")
    public String editSales(Model model, Long id, HttpSession session){
        session.setAttribute("info", 0);
        Salesman salesman = salesmanRepository.findById(id).orElse(null);
        if(salesman==null) throw new RuntimeException("Patient does not exist");
        model.addAttribute("salesman", salesman);
        return "redirect:/index";
    }


    @PostMapping("/submitSale")
    public String submitSale(@RequestParam("itemType") ItemType itemType,
                                   @RequestParam("name") String name,
                                   @RequestParam("amount") Double amount,
                                   @RequestParam("date") String date,
                                   RedirectAttributes redirectAttributes) {

        LocalDate localDate = LocalDate.parse(date);

        Salesman salesman = new Salesman(amount, localDate, itemType,name);
        // Save the entity to the repository
        salesmanRepository.save(salesman);


        return "redirect:/index";
    }


}
