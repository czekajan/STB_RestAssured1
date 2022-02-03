import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplaceholderGETTest {

//    GIVEN - konfiguracja
//    WHEN - wysłanie requestu
//    THEN - asercje


    @Test  // GET wszystkich uzytkownikow
    public void jsonplaceholderReadAllUsers(){

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        //System.out.println(response.asString());

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");

        names.stream()
                .forEach(System.out::println);

        Assertions.assertEquals(10, names.size());

    }

        @Test  // GET jednego uzytkownika
    public void jsonplaceholderReadOneUser(){

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1");

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Bret", json.get("username"));
        Assertions.assertEquals("Sincere@april.biz", json.get("email"));
        Assertions.assertEquals("Kulas Light", json.get("address.street"));

        System.out.println(response.asString());
    }
}
