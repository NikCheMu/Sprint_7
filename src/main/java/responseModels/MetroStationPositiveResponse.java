package responseModels;

public class MetroStationPositiveResponse {

    private String number;

    private String name;

    private String color;

    public MetroStationPositiveResponse(String number, String name, String color) {
        this.number = number;
        this.name = name;
        this.color = color;
    }

    public MetroStationPositiveResponse() {
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
