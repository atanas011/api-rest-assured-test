package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ReusableVarsTest {

    private RequestSpecification reqSpec;
    private ResponseSpecification resSpec;

    private final String baseUri = "http://api.zippopotam.us/";
    private final String countryAndCode = "us/90210";
    private final String url = baseUri + countryAndCode;
    private final String jsonPathPlace = "places[0].'place name'";

    @BeforeClass
    public void createReqSpec() { // reusable
        reqSpec = new RequestSpecBuilder().
                setBaseUri(baseUri).
                build();
    }

    @BeforeClass
    public void createResSpec() { // reusable
        resSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void verifyResBodyWithReqSpec() {
        getResponse(countryAndCode).
            assertThat().statusCode(200);
    }

    @Test
    public void verifyResBodyWithResSpec() {
        getResponse(url).
            spec(resSpec).
        and().
            assertThat().
            body(jsonPathPlace, equalTo("Beverly Hills"));
    }

    @Test
    public void extractPlaceNameFromResBody() { // extract value for reuse
        var place = getResponse(url).extract().path(jsonPathPlace);
        assertEquals(place, "Beverly Hills");
    }

    private ValidatableResponse getResponse(String param) {
        return given().spec(reqSpec).when().get(param).then();
    }
}