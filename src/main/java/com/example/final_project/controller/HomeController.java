package com.example.final_project.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Index"; // 與 templates 下的文件名對應
    }

    @GetMapping("/Index.html")
    public String home2() {
        return "Index"; // 與 templates 下的文件名對應
    }

    @GetMapping("/Info.html")
    public String info() {
        return "Info"; // 與 templates 下的文件名對應
    }

    @GetMapping("/Cart.html")
    public String cart() {
        return "Cart"; // 與 templates 下的文件名對應
    }

    @GetMapping("/List.html")
    public String list() {
        return "List"; // 與 templates 下的文件名對應
    }
}
