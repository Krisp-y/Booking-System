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
        //When venue can never satisfy room requirements, exit
        if(!hasEnoughRooms(r)) {
            System.out.println(this.venueName+"does not have enough rooms for reservation"+r.getReservationName());
            return false;    
        }
        int smlNeeded = r.getSmlCount();
        int medNeeded = r.getMedCount();
        int lrgNeeded = r.getLrgCount();

            for(Room prospectRoom: roomList) {
                if(prospectRoom.roomAvailable(r)) {
                    //add valid room to res room list
                    if(smlNeeded > 0 && prospectRoom.getSize() == "Small") {
                        smlNeeded--;
                    }
                    if(medNeeded > 0 && prospectRoom.getSize() == "Medium") {
                        medNeeded--;
                    }
                    if(lrgNeeded > 0 && prospectRoom.getSize() == "Large") {
                        lrgNeeded--;
                    }
                    r.getRoomList().add(prospectRoom);
                    prospectRoom.storeReservation(r);
                    //add reservation r to prospect instead
                }
            }
            //Not enough rooms are available at this venue during date range
            if(smlNeeded != 0 || medNeeded != 0 || lrgNeeded != 0) {
                //go through all rooms in this venues list, clear r as it can't be booked
                for(Room toClear: roomList) {
                    toClear.removeReservation(r);
                }
                return false;    
            }
                
        return true;    
    }

    public boolean hasEnoughRooms(Reservation r) {
        if(r.getSmlCount() > this.sml || r.getMedCount() > this.med || r.getLrgCount() > this.lrg) {
                return false;
            } else {
                return true;
            }
    }

    public ArrayList<Room> getLrgRooms() {
        ArrayList<Room> LR = new ArrayList<>();
        for(Room r: roomList) {
            if(r.getSize() == "Large") {
                LR.add(r);
            }
        }
        return LR;
    }

    public ArrayList<Room> getMedRooms() {
        ArrayList<Room> MR = new ArrayList<>();
        for(Room r: roomList) {
            if(r.getSize() == "Medium") {
                MR.add(r);
            }
        }
        return MR;
    }

    public ArrayList<Room> getSmlRooms() {
        ArrayList<Room> SR = new ArrayList<>();
        for(Room r: roomList) {
            if(r.getSize() == "Small") {
                SR.add(r);
            }
        }
        return SR;
    }

    public String getVenueName() {
        return venueName;
    }

    public ArrayList<Room> getVenueRoomList() {
        return roomList;
    }
 
}

