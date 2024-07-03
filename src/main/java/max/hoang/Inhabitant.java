package max.hoang;

public class Inhabitant {
    private String name;
    private String dob;
    private String married;

    public Inhabitant(String name, String dob, String married) {
        this.name = name;
        this.dob = dob;
        this.married = married;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

}