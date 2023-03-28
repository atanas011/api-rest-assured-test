package tests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PlaceNameTest {

    private final String url = "http://zippopotam.us/us/90210";

    @Test
    public void verifyPlaceName() {
        assertResponse().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void verifyStatusCode() {
        assertResponse().statusCode(200);
    }

    @Test
    public void verifyContentType() {
        assertResponse().
                contentType(ContentType.JSON); // ("application/json");
    }

    @Test
    public void logReqResDetails() {
        given().log().all().when().get(url).then().log().body();
    }

    @Test
    public void verifyResBody() {
        var jsonPathState = "places[0].state";
        var jsonPathPlace = "places[0].'place name'"; // 2 words, hence ''
        assertResponse().body(jsonPathState, equalTo("California"));
        assertResponse().body(jsonPathPlace, equalTo("Beverly Hills"));
        assertResponse().body("places.'place name'", hasItem("Beverly Hills"));
        assertResponse().body("places.'place name'", hasSize(1));
    }

    private ValidatableResponse assertResponse() {
        return given().when().get(url).then().assertThat();
    }
}