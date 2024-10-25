package com.example.ayrotek;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UrunServisi {

    @Autowired
    private UrunlerRepository urunlerRepository;

     @Autowired
    private KullaniciRepository kullaniciRepository;

    public Kullanici findUserByUsername(String username) {
        return kullaniciRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanici bulunamadi: " + username));
    }


    public Urunler save(Urunler urun) {
        return urunlerRepository.save(urun);
    }

    public List<Urunler> getAllUrunler() {
        return urunlerRepository.findAll();
    }

    public Optional<Urunler> getUrunById(Long id) {
        return urunlerRepository.findById(id);
    }

    public Urunler addUrun(Urunler urun) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Ürünü oluşturan kullanıcıyı "owner" olarak ata
        Kullanici owner = new Kullanici();  // Bu kısımda kullanıcıyı mevcut kullanıcıya bağlamalısınız.
        owner.setUsername(currentUsername);
        urun.setOwner(owner);

        return urunlerRepository.save(urun);
    }

    public Urunler updateUrun(Long id, Urunler urun) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Optional<Urunler> existingUrun = urunlerRepository.findById(id);
        if (existingUrun.isPresent() && existingUrun.get().getOwner().getUsername().equals(currentUsername)) {
            Urunler updatedUrun = existingUrun.get();
            updatedUrun.setName(urun.getName());
            updatedUrun.setPrice(urun.getPrice());
            return urunlerRepository.save(updatedUrun);
        }
        throw new SecurityException("Yalnizca ürünün sahibi bu işlemi yapabilir.");
    }

    public void deleteUrun(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Optional<Urunler> existingUrun = urunlerRepository.findById(id);
        if (existingUrun.isPresent() && existingUrun.get().getOwner().getUsername().equals(currentUsername)) {
            urunlerRepository.deleteById(id);
        } else {
            throw new SecurityException("Yalnizca ürünün sahibi bu işlemi yapabilir.");
        }
    }
}
