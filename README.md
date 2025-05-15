# 🧪 API Test Projesi – Rest Assured + JUnit4

Bu proje, [https://reqres.in](https://reqres.in) sahte REST API'si üzerinde **otomatik API testleri** yürütmek için oluşturulmuştur. Testler `Rest Assured` ve `JUnit4` kullanılarak yazılmıştır.

---

## 📌 Kullanılan Teknolojiler

- Java 17
- Maven
- JUnit4
- Rest Assured (v5.3.1)
- Reqres API (x-api-key: `reqres-free-v1`)

---

## 🔍 Test Senaryoları

### ✅ 1. Kullanıcı Listesi GET Testi
- Endpoint: `GET /users?page=2`
- Kontroller:
  - Status Code = 200
  - JSON'daki `page` alanı = 2
  - `data[]` listesi boş değil
  - Yanıt süresi 2000 ms'den kısa

### ✅ 2. Yeni Kullanıcı Oluşturma (POST)
- Endpoint: `POST /users`
- Body:
```json
{ "name": "user_TIMESTAMP", "job": "tester" }
```
- Kontroller:
  - Status Code = 201
  - Dönen `name` ve `job` doğru mu?
  - Yanıt süresi 2000 ms'den kısa

### ❌ 3. Geçersiz Kullanıcı (Negatif Test)
- Endpoint: `GET /users/99999`
- Kontroller:
  - Status Code = 404

---

## ⚙️ Projeyi Çalıştırmak

1. Projeyi klonlayın:
```bash
git clone https://github.com/kullanici_adiniz/reqres-api-tests-restassured-junit4.git
```

2. Maven bağımlılıklarını indirin:
```bash
mvn clean install
```

3. Testleri çalıştırın:
```bash
mvn test
```

---

## 📸 Örnek Çıktı
```
⏱️ Yanıt Süresi (ms): 208
📦 Status Kodu: 200
📨 Response Body:
{
  "page": 2,
  "data": [...]
}
```

---

## 📁 Proje Yapısı
```
src/test/java/org/example/ApiTest.java
pom.xml
README.md
```

---


**Gülsüm Demir**  
Bilişim Sistemleri Mühendisliği – Yazılım Test Projesi
