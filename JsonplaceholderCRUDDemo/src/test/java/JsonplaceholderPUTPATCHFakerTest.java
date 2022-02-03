import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderPUTPATCHFakerTest {

    private static Faker faker;
    private String fakeEmail;
    private String fakeName;
    private String fakeUserName;
    private String fakeWWW;
    private String fakePhone;

    @BeforeAll
    public static void beforeAll(){
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        fakeEmail = faker.internet().emailAddress();
        fakeName = faker.name().name();
        fakeUserName = faker.name().username();
        fakeWWW = faker.internet().url();
        fakePhone = faker.phoneNumber().phoneNumber();
    }

    @Test
    public void jsonplaceholderUpdatePUTUser(){

        JSONObject user = new JSONObject();
        user.put("name", fakeName);
        user.put("username", fakeUserName);
        user.put("email", fakeEmail);
        user.put("phone", fakePhone);
        user.put("website", fakeWWW);

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
        assertEquals(fakeName, json.get("name"));
        assertEquals(fakeUserName, json.get("username"));
        assertEquals(fakeEmail, json.get("email"));

    }


    @Test
    public void jsonplaceholderUpdatePATCHUser(){

        JSONObject email = new JSONObject();
        email.put("email", fakeEmail);

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
        assertEquals(fakeEmail, json.get("email"));
    }

}
