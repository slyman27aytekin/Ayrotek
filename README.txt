---

# **Ayrotek Tax Calculator API**

Bu proje, kullanıcıların sahip oldukları ürünler için vergi hesaplamalarını sağlayan bir RESTful API uygulamasıdır. Proje Spring Boot kullanılarak geliştirilmiştir ve ürün bilgilerini SQL tabanlı **H2 Veritabanı**’nda, vergi loglarını ise **MongoDB** üzerinde saklar.

---

## **Proje Özellikleri**
- **Spring Boot** kullanılarak geliştirilmiş bir RESTful API.
- Ürün yönetimi: Ürün ekleme, silme, güncelleme ve listeleme.
- Kullanıcı yetkilendirmesi: Sadece ürün sahibi kendi ürünü için vergi hesaplayabilir.
- **H2 Veritabanı** ile SQL tabanlı ürün verisi saklama.
- **MongoDB** ile vergi loglarını kayıt altına alma.

---

## **Kullanılan Teknolojiler**
- **Java 17+**  
- **Spring Boot**  
- **H2 Database** (Geliştirme ve test için SQL tabanlı veritabanı)  
- **MongoDB** (Vergi hesaplama loglarının saklanması için)  
- **Maven** (Bağımlılık yönetimi)  

---

## **Kurulum**
1. **Projeyi klonlayın:**



2. **Maven bağımlılıklarını yükleyin:**
   ```bash
   mvn clean install
   ```

3. **H2 ve MongoDB Bağlantı Ayarları:**

   `application.properties` içinde H2 ve MongoDB ayarları şu şekilde yapılmıştır:

   ```properties
   # H2 Database Ayarları
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.h2.console.enabled=true
   spring.jpa.hibernate.ddl-auto=update

   # MongoDB Ayarları
   spring.data.mongodb.uri=mongodb://localhost:27017/ayrotekdb
   spring.data.mongodb.database=ayrotekdb
   ```

4. **MongoDB'nin çalıştığından emin olun:**
   - Terminalde şu komutu çalıştırın:
     ```bash
     mongod
     ```
   - MongoDB'ye **MongoDB Compass** veya `localhost:27017` üzerinden erişim sağlayabilirsiniz.

5. **Uygulamayı çalıştırın:**
   ```bash
   mvn clean install 
   java -jar target/AyroTek-0.0.1-SNAPSHOT.jar
   ```

6. **H2 Veritabanı Konsolu:**  
   - Adres: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
   - JDBC URL: `jdbc:h2:mem:testdb`

---

## **API Kullanımı**

---
### **1. Kullanıcı Ekleme**
- **Endpoint:**  
  `POST /api/products/add`

- **Request Body:**
   ```json
[
{
  "id": 2,
  "username": "ahmet"
}
]

   ```

---


---
### **2. Ürün Ekleme**
- **Endpoint:**  
  `POST /api/products/add`

- **Request Body:**
   ```json
[
{
    "name": "Laptop",
    "price": 4412,
    "owner": {
        "id": 5
    }
}
]

   ```

---


### **3.A Ürün Listeleme**
- **Endpoint:**  
  `GET /api/products/all`

- **Response:**
   ```json
   [
     {
       "id": 2,
       "name": "Laptop",
       "price": 1000.0
	"owner": {
            "id": 5,
            "username": "ahmet"
        }
     },
     {
       "id": 1,
       "name": "Telefon",
       "price": 500.0
	"owner": {
            "id": 1,
            "username": "Mehmet"
        }
     }
   ]
   ```

---


### **3.B User Ürün Listeleme**
- **Endpoint:**  
  `GET api/products/owner/`

- **Response:**
   ```json
   [
    {
        "id": 4,
        "name": "Telefon",
        "price": 4412.0,
        "owner": {
            "id": 5,
            "username": "ahmet"
        }
    },
    {
        "id": 5,
        "name": "Laptop",
        "price": 5912.0,
        "owner": {
            "id": 5,
            "username": "ahmet"
        }
    }
   ]
   ```

---

### **4. Ürün Güncelle**
- **Endpoint:**  
  `PUT /api/products/{productId}/owner/{ownerId}`

- **Örnek:**  
  `PUT /api/products/1/owner/1`
- **Request Body:**
   ```json
   [
    {
     "name": "Telefon",
     "price": 2255
    }

  ]

   ```
- **Response:**
   ```json
[
 {
    "id": 1,
    "name": "Telefon",
    "price": 2255.0,
    "owner": {
        "id": 1,
        "username": "Mehmet"
    }
 }
]
   ```
!!Bu endpoint, yalnızca ürün sahibi tarafından kullanılabilir.
---


### **5. Ürün SİL**
- **Endpoint:**  
  `DELETE /api/products/{productId}/owner/{ownerId}`

- **Örnek:**  
  `DELETE http://localhost:8080/api/products/1/owner/1`

- **Response:**
   ```json
   {
     "message": Product deleted successfully
   }
   ```

---




### **6. Vergi Hesaplama**
- **Endpoint:**  
  `GET /api/tax/calculate/{productId}/owner/{ownerId}`

- **Örnek:**  
  `GET /api/tax/calculate/1/owner/1`

- **Response:**
   ```json
   {
     "message": "Ürünün vergisi -> Laptop: 100.0"
   }
   ```

---

### **7. Vergi Loglarını Görüntüleme**
- MongoDB içinde `tax_logs` koleksiyonuna eklenen logları **MongoDB Compass** veya `mongosh` üzerinden görebilirsiniz:


---



## **Sorun Giderme**

- **MongoDB Bağlantı Hatası:**  
  Eğer MongoDB'ye bağlanamıyorsanız, `mongod` servisinin çalıştığından emin olun. Terminalde şu komutu çalıştırabilirsiniz:
  ```bash
  mongod --dbpath /path/to/your/db
  ```

- **Port Çakışması:**  
  8080 portu kullanılıyorsa `application.properties` içinde farklı bir port belirleyin:
  ```properties
  server.port=8081
  ```

---





## **İletişim**
Herhangi bir sorunuz olursa bana ulaşabilirsiniz:  
- **E-posta:** suleyman@ytekin.com 
- **GitHub:** https://github.com/slyman27aytekin

---
