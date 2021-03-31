package borisov_victor;

import java.util.ArrayList;

/**
 * A class-company that owns two bus companies.
 */
public class Bloggersville {
    private ArrayList<BusCompany> buses;

    public Bloggersville() {
        this.buses = new ArrayList<>();
    }

    public ArrayList<BusCompany> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<BusCompany> buses) {
        this.buses = buses;
    }
}
