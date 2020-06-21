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
        reservationList= new ArrayList<>();

    } 
    

    public void addReservation() {
        if(roomAvailable()) {

        }
    }

    public boolean roomAvailable(Reservation r) {
        //Search res list
        for(int i = 0; i<reservationList.size(); i++) {
            
        }

    }
}

    
