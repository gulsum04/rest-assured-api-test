package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class ApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification requestSpec = given()
                .header("x-api-key", "reqres-free-v1");
        RestAssured.requestSpecification = requestSpec;
    }

    // Yardımcı metot: Yanıt bilgilerini logla
    private void logResponse(Response response) {
        System.out.println("\n==============================");
        System.out.println("⏱️ Yanıt Süresi (ms): " + response.time());
        System.out.println("📦 Status Kodu: " + response.statusCode());
        System.out.println("📨 Response Body:\n" + response.getBody().asPrettyString());
        System.out.println("==============================\n");
    }

    // ✅ GET: Kullanıcı listesi (sayfa 2)
    @Test
    public void testGetUserList() {
        Response response = given()
                .when()
                .get("/users?page=2");

        logResponse(response);

        response.then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data.size()", greaterThan(0))
                .time(lessThan(2000L));
    }

    // ✅ POST: Yeni kullanıcı oluşturma
    @Test
    public void testCreateUser() {
        String name = "user_" + System.currentTimeMillis();
        String job = "tester";
        String jsonBody = String.format("{ \"name\": \"%s\", \"job\": \"%s\" }", name, job);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/users");

        logResponse(response);

        response.then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("job", equalTo(job))
                .time(lessThan(2000L));
    }

    // ❌ Negatif Test: Geçersiz kullanıcı
    @Test
    public void testUserNotFound() {
        Response response = given()
                .when()
                .get("/users/99999");

        logResponse(response);

        response.then()
                .statusCode(404);
    }
}
