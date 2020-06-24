package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venue {
    private String venueName;
    private ArrayList<Room> roomList;
    private int sml;
    private int med;
    private int lrg;
    
    

    public Venue (String venueName){
        this.venueName = venueName;
        roomList = new ArrayList<>();
        this.sml = 0;
        this.med = 0;
        this.lrg = 0;

    }

    //Checks if venue satisfies date/room requirements of booking
    public boolean attemptBooking (Reservation r){
        //check venue has enough rooms
        //look through room list. If available, tentative book
        //if clash, keep looking
        //if not, set tentitive booking flag
        checkSize
        room.booking.setFlag
    }

    public boolean hasEnoughRooms(Reservation r) {
        if(r.getSmlCount() > this.sml || r.getMedCount() > this.med || r.getLrgCount() > this.lrg) {
                return false;
            } else {
                return true;
            }
    }
//returns a list of rooms available 
public ArrayList<Room> getVailable(LocalDate givenDate, int size) {

    for room in roomList
    room.get res(string)
    append to a list
}

    public confirmAllReservation() {
        //add all temp=false booking objs to masterlist
        for room in roomList
        room.confirmRoom
    }
}

