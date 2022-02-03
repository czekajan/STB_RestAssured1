import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderPUTPATCHTest {

    @Test
    public void jsonplaceholderUpdatePUTUser(){

        String jsonBody = "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Ana Wait PUT\",\n" +
                "    \"username\": \"Ana PUT\",\n" +
                "    \"email\": \"ankaPUT@o2.com\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Blue\",\n" +
                "      \"suite\": \"Apt. 556\",\n" +
                "      \"city\": \"Gwenborough\",\n" +
                "      \"zipcode\": \"92998-3874\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-37.3159\",\n" +
                "        \"lng\": \"81.1496\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Romaguera-Crona\",\n" +
                "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "      \"bs\": \"harness real-time e-markets\"\n" +
                "    }\n" +
                "  }";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
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

        String jsonBody = "  {\n" +
                 "    \"email\": \"ankaPATCH@o2.com\"\n" +
                 "  }";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
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
