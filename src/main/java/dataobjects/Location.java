package dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"postCode", "country", "countryAbbreviation", "places"})
//@JsonIgnoreProperties("places")
public class Location {

    @JsonProperty("post code") // with space in JSON
    private String postCode;
    private String country; // w/o space in JSON
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    private final List<Place> places;

    public Location() {
        var places = new ArrayList<Place>();
        places.add(new Place());
        this.places = places;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setCountryAbbreviation(String abbrev) {
        countryAbbreviation = abbrev;
    }

    public List<Place> getPlaces() {
        return places;
    }

    @JsonPropertyOrder({"placeName", "state"})
    @JsonIgnoreProperties(ignoreUnknown = true) // "longitude", etc.
    public static class Place {

        @JsonProperty("place name")
        private String placeName;
        private String state;

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String name) {
            placeName = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String name) {
            state = name;
        }
    }
}