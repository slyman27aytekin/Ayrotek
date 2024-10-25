package com.deneme.ayroteksulo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AyroTekApplication.class)  // Test için ana uygulama sınıfı bağlanıyor
public class AyroTekApplicationTests {

    @Test
    public void contextLoads() {
        // Boş test metodu, sadece context'in başarıyla yüklendiğini doğrular.
    }
}
