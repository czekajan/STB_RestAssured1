import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderGETTwoTest {

    private final String BASE_URL =  "https://jsonplaceholder.typicode.com";
    private final String USERS = "users";


    @Test
    public void jsonplaceholderReadAllUsersTwo(){

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");
        assertEquals(10, names.size());
    }

    @Test
    public void jsonplaceholderReadOneUserTwo(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS + "/1")
                .then()
                .statusCode(200)
// jeden ze sposobow pisania testow, ale rekomenduje sie uzywac .extract() i .response()
//                .body("name", Matchers.equalTo("Leanne Graham"))
//                .body("username", Matchers.equalTo("Bret"))
//                .body("email", Matchers.equalTo("Sincere@april.biz"))
//                .body("address.street", Matchers.equalTo("Kulas Light"));
                .extract()
                .response();


        JsonPath json = response.jsonPath();
        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));
        assertEquals("Kulas Light", json.get("address.street"));

//        System.out.println(response.asString());
    }


    @Test
    public void jsonplaceholderReadOneUserWithPathVariable(){

        Response response = given()
                .pathParam("userID", 1)
                .when()
                .get(BASE_URL + "/" + USERS + "/{userID}")
                .then()
                .statusCode(200)
                .extract()
                .response();


        JsonPath json = response.jsonPath();
        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));
        assertEquals("Kulas Light", json.get("address.street"));
    }


    @Test
    public void jsonplaceholderReadUsersWithQueryParams(){

        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Leanne Graham", json.getList("name").get(0));
        assertEquals("Bret", json.getList("username").get(0));
        assertEquals("Sincere@april.biz", json.getList("email").get(0));
        assertEquals("Kulas Light", json.getList("address.street").get(0));
    }
}
