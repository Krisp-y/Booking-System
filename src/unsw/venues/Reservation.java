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
     * @param reservationName Name of reservation
     * @param bookedRoom Room assigned to reservation
     * @param startDate Begin date of a reservation
     * @param endDate End date of a reservation
     */
    public Reservation(String reservationName, Room bookedRoom, LocalDate startDate, LocalDate endDate) {
        this.reservationName = reservationName;
        this.tempBookingFlag = true;
        this.bookedRoomList = new ArrayList<Room>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.smlCount = 0;
        this.medCount = 0;
        this.lrgCount = 0;
    }

    public String getReservationName(Reservation reservation) {
        return this.reservationName;
    }

    public Room getRoom(Reservation reservation) {
        return this.bookedRoom;
    }

    public void setRoom(Room rm) {
        this.bookedRoom = rm;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setFlag() {
        this.tempBookingFlag = false;
    }

    public boolean getFlag() {
        return this.tempBookingFlag;
    }

    public int getSmlCount() {
        return smlCount;
    }

    public void setSmlCount(int src) {
        this.smlCount = src;
    }

    public int getMedCount() {
        return this.medCount;
    }

    public void setMedCount(int mrc) {
        this.medCount = mrc;
    }
    public int getLrgCount() {
        return this.lrgCount;
    }

    public void setLrgCount(int lrc) {
        this.lrgCount = lrc;
    }
}