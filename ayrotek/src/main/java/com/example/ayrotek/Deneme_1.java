package com.example.ayrotek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/urunler")
public class Deneme_1 {

    @Autowired
    private UrunServisi urunServisi;

    @PostMapping
    public ResponseEntity<Urunler> createProduct(@RequestBody Urunler urun, Authentication authentication) {
        // Kullanıcıyı Authentication ile buluyoruz
        Kullanici kullanici = urunServisi.findUserByUsername(authentication.getName());

        urun.setOwner(kullanici);  // Ürünün sahibini ayarlıyoruz
        urunServisi.save(urun);  // Ürünü kaydediyoruz
        return new ResponseEntity<>(urun, HttpStatus.CREATED); // 201 Created yanıtı dönüyoruz
    }
}
