package com.lolplanet.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/userName={name}")
    public String summonerDetail(Model model, @PathVariable(name = "name") String summonerName) {
        model.addAttribute("summonerName", summonerName);
        return "summoner-detail";
    }
}
