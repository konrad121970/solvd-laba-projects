package route;

public class Location {
    private String city;
    private String streetName;

    public Location(String city, String streetName) {
        this.city = city;
        this.streetName = streetName;
    }

    public String getStreetName(){
        return streetName;
    }

    public void setStreetName(String streetName){
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
