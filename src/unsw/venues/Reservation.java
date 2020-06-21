package unsw.venues;

import java.time.LocalDate;

public class Reservation {
    private String reservationName;
    private Room bookedRoom;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * 
     * @param reservationName Name of reservation
     * @param bookedRoom Room assigned to reservation
     * @param startDate Begin date of a reservation
     * @param endDate End date of a reservation
     */
    public Reservation(String reservationName, Room bookedRoom, LocalDate startDate, LocalDate endDate) {
        this.reservationName = reservationName;
        this.bookedRoom = bookedRoom;
        this.startDate = startDate;
        this.endDate = endDate;
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
}