package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
    private String reservationName;
    private ArrayList<Room> bookedRoomList;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean tempBookingFlag;
    private int smlCount;
    private int medCount;
    private int lrgCount;

    /**
     * 
     * @param reservationName
     * @param startDate
     * @param endDate
     * @param sml
     * @param med
     * @param lrg
     */
    public Reservation(String reservationName, LocalDate startDate, LocalDate endDate, int sml, int med, int lrg) {
        this.reservationName = reservationName;
        this.tempBookingFlag = true;
        this.bookedRoomList = new ArrayList<Room>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.smlCount = sml;
        this.medCount = med;
        this.lrgCount = lrg;
    }

    public String getReservationName() {
        return reservationName;
    }

    public ArrayList<Room> getRoomList() {
        return bookedRoomList;
    }

    public void addRoomToList(Room rm) {
        this.bookedRoomList.add(rm);
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
}