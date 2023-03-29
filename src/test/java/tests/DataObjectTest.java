package tests;

import dataobjects.Location;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class DataObjectTest {

    private final String url = "http://api.zippopotam.us/us/90210";

    @Test // deserialization
    public void verifyPlaceNameInDataObject() {
        var location =
                given().
                when().
                    get(url).
                    as(Location.class);
        assertEquals(location.getPlaces().get(0).getPlaceName(), "Beverly Hills");
    }

    @Test  // serialization
    public void verifyPlaceNameInResBody() {
        var location = new Location();
        location.setPostCode("90210");
        location.getPlaces().get(0).setPlaceName("Beverly Hills");
        location.getPlaces().get(0).setState("California");

        given().
            contentType(ContentType.JSON).
            body(location).
            log().body(). // JSON from data-object
        when().
            get(url). // post() gives "Method Not Allowed"
        then().
//            log().body(). // JSON from url
            assertThat().
            body("places[0].state",equalTo(location.getPlaces().get(0).getState()));
    }
}