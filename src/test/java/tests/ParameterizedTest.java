package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParameterizedTest {

    @Test(dataProvider = "zipCodesAndPlaces")
    public void checkResBody(String country, String zipCode, String place) {
        given().
            pathParam("country", country).pathParam("zipCode", zipCode).
        when().
            get("http://zippopotam.us/{country}/{zipCode}").
        then().
            assertThat().
            body("places[0].'place name'", equalTo(place));
    }

    @DataProvider
    public Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"us", "22222", "Arlington"}
        };
    }
}