package unsw.venues;

import java.time.LocalDate;
//import java.util.ArrayList;

//import org.json.JSONArray;
import org.json.JSONObject;

public class Reservation {
    private String reservationName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean tempBookingFlag;
    private int smlCount;
    private int medCount;
    private int lrgCount;

    
    public Reservation(String reservationName, LocalDate startDate, LocalDate endDate, int sml, int med, int lrg) {
        this.reservationName = reservationName;
        this.tempBookingFlag = true;
        this.startDate = startDate;
        this.endDate = endDate;
        this.smlCount = sml;
        this.medCount = med;
        this.lrgCount = lrg;
    }

    public Reservation(Reservation R) {
        this(R.getReservationName(),R.getStartDate(),R.getEndDate(),R.getSmlCount(),R.getMedCount(),R.getLrgCount());
    }
    public String getReservationName() {
        return reservationName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setFlag() {
        this.tempBookingFlag = false;
    }

    public boolean getFlag() {
        return tempBookingFlag;
    }

    public int getSmlCount() {
        return smlCount;
    }

    public void setSmlCount(int src) {
        this.smlCount = src;
    }

    public int getMedCount() {
        return medCount;
    }

    public void setMedCount(int mrc) {
        this.medCount = mrc;
    }
    public int getLrgCount() {
        return lrgCount;
    }

    public void setLrgCount(int lrc) {
        this.lrgCount = lrc;
    }

    public JSONObject resToJSON() {
        JSONObject newObj = new JSONObject();
        newObj.put("id", reservationName);
        newObj.put("start", startDate.toString());
        newObj.put("end", endDate.toString()); 
        return newObj;
    }
    

}