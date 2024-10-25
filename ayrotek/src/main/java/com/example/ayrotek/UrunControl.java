package com.example.ayrotek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 




@RestController
@RequestMapping("/urunler")
public class UrunControl {

    @Autowired
    private UrunServisi urunServisi;

    @GetMapping
    public List<Urunler> getAllUrunler() {
        return urunServisi.getAllUrunler();
    }

    @PostMapping
    public Urunler addUrun(@RequestBody Urunler urun) {
        return urunServisi.save(urun);
    }
}

