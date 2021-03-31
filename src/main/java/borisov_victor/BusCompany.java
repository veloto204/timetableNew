package borisov_victor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * The class contains all possible transports.
 */
public class BusCompany {
    private String name;
    private Date startTime;
    private Date finishTime;


    public BusCompany(String name, Date startTime, Date finishTime) {
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusCompany that = (BusCompany) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(finishTime, that.finishTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, finishTime);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return name + " " +
                formatter.format(startTime) + " " + formatter.format(finishTime);
    }
}
