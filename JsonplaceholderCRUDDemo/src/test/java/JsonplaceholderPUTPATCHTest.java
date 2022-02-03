import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderPUTPATCHTest {

    @Test
    public void jsonplaceholderUpdatePUTUser(){

        JSONObject user = new JSONObject();
        user.put("name", "Ana Wait PUT");
        user.put("username", "Ana PUT");
        user.put("email", "ankaPUT@o2.com");
        user.put("phone", "1-770-736-8031 x56442");
        user.put("website", "hildegard.org");

        JSONObject geo = new JSONObject();
        geo.put("lat", "-37.3159");
        geo.put("lng", "81.1496");

        JSONObject address = new JSONObject();
        address.put("Street", "Blue");
        address.put("suite", "Apt. 556");
        address.put("city", "Gwenborough");
        address.put("zipcode", "92998-3874");
        address.put("geo", geo);

        user.put("address", address);

        JSONObject company = new JSONObject();
        company.put("name", "Romaguera-Crona");
        company.put("catchPhrase", "Multi-layered client-server neural-net");
        company.put("bs", "harness real-time e-markets");

        user.put("company", company);


        System.out.println(user.toString());

        Response response = given()
                .contentType("application/json")
                .body(user.toString())
                .when()
                .put("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();


        JsonPath json = response.jsonPath();
        assertEquals("Ana Wait PUT", json.get("name"));
        assertEquals("Ana PUT", json.get("username"));
        assertEquals("ankaPUT@o2.com", json.get("email"));

    }


    @Test
    public void jsonplaceholderUpdatePATCHUser(){

        JSONObject email = new JSONObject();
        email.put("email", "ankaPATCH@o2.com");

        System.out.println(email);

        Response response = given()
                .contentType("application/json")
                .body(email.toString())
                .when()
                .patch("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("ankaPATCH@o2.com", json.get("email"));
    }

}
