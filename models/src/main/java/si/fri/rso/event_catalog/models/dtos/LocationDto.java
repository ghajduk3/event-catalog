package si.fri.rso.event_catalog.models.dtos;


public class LocationDto {
    private Integer id;
    private String address;
    private String country;
    private String city;
    private String street;
    private Float lat;
    private Float lon;

    public LocationDto(){}

    public LocationDto(Integer id, String address, String country, String city, String street, Float lat, Float lon) {
        this.id = id;
        this.address = address;
        this.country = country;
        this.city = city;
        this.street = street;
        this.lat = lat;
        this.lon = lon;
    }
    public LocationDto(String address, String country, String city, String street, Float lat, Float lon) {
        this.address = address;
        this.country = country;
        this.city = city;
        this.street = street;
        this.lat = lat;
        this.lon = lon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }
}
