package com.example.ayrotek;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageControl {

    @GetMapping("/")
    public String anaSayfa() {
        return "Hoş Geldiniz! Uygulama Başariyla Çalişiyor.";
    }
}
