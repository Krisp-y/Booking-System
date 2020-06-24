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
        //Search res list
        for(int i = 0; i<reservationList.size(); i++) {
            LocalDate thisStart = this.reservationList.get(i).getStartDate();
            if(r.getStartDate() this.reservationList.get(i).getStartDate())
        }

    }

    //for every reservation in room confirm booking
    void confirmRoom(Reservation r)
    for r in this.reservationList {
        setFlag(r)
        
}

    
