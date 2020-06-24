package unsw.venues;

import java.util.ArrayList;
import java.time.LocalDate;

public class Room {
    private String roomName;
    private String size;
    private ArrayList<Reservation> reservationList;

    /**
     * 
     * @param roomName
     * @param size
     */

    public Room(String roomName, String size) {
        this.roomName = roomName;
        this.size = size;
        this.reservationList= new ArrayList<Reservation>();

    } 

    get reserveration(id) //search through reList
    
    //Only called after reservation validity is confirmed
    public void storeReservation(String bookingId) {
        //if room available, store reservating in room    
        this.reservationList.add(r);
    }

    public boolean roomAvailable(Reservation r) {
        //Tentative reservation dates
        LocalDate TRstart = r.getStartDate();
        LocalDate TRend = r.getEndDate();
        for(Reservation res: reservationList) {
            //Start/end date for existing reservation in list
            LocalDate ERstart = res.getStartDate();
            LocalDate ERend = res.getEndDate();
            if(ERstart.isBefore(TRstart) && TRend.isBefore(ERend)) {
                return false;
            } else if(TRend.isBefore(ERstart) && TRend.isBefore(ERend)) {
                return false;
            } else if (TRstart.isBefore(ERend) && ERend.isAfter(TRstart)) {
                return false;
            } else {
                //Room is free for date window, add to list
                //
                if(r.getRoomList().size() == 0) {
                    r.setFlag();
                }
                r.addRoomToList(this);
                return true;
            }
        }

    }

    //for every reservation in room confirm booking
    void confirmRoom(Reservation r)
    for r in this.reservationList {
        setFlag(r)
        
}

    
