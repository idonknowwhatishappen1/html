package max.hoang;

import java.util.*;

public class City {
    private Set<Inhabitant> inhabitants;

    public City() {
        this.inhabitants = new HashSet<>();
    }

    public City(Set<Inhabitant> inhabitants) {
        this.inhabitants = inhabitants;
    }

    public Inhabitant searchLocal(String name) {
        for (Inhabitant inhabitant : inhabitants) {
            if (inhabitant.getName().equals(name)) {
                return inhabitant;
            }
        }
        return null; // Return null if inhabitant with given name is not found
    }

    public void addInhabitant(String name, String dob, String married) {
        Inhabitant newInhabitant = new Inhabitant(name, dob, married);
        if (!inhabitants.contains(newInhabitant)) {
            inhabitants.add(newInhabitant);
        }
    }

    public Set<Inhabitant> getInhabitants() {
        return inhabitants;
    }

    public String setMaritalStatus(String name, String married) {
        Inhabitant inhabitant = searchLocal(name);
        if (inhabitant != null) {
            inhabitant.setMarried(married);
            return name + "'s Marital Status is set to " + married;
        } else {
            return "No such inhabitant found";
        }
    }
}
