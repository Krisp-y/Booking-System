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
        ArrayList<Room> SR = getSmlRooms();
        ArrayList<Room> MR = getMedRooms();
        ArrayList<Room> LR = getLrgRooms();
        int smlNeeded = r.getSmlCount();
        int medNeeded = r.getMedCount();
        int lrgNeeded = r.getLrgCount();

            for(Room prospectRoom: roomList) {
                if(prospectRoom.roomAvailable(r)) {
                    //add valid room to res room list
                    r.getRoomList().add(prospectRoom);
                    //Update counter for rooms still needed
                    if (SR.contains(prospectRoom)) {
                        smlNeeded--;
                    } else if (MR.contains(prospectRoom)) {
                        medNeeded--;
                    } else {
                        lrgNeeded--;
                    }

                }
                //Not enough rooms are available at this venue during date range
                if(smlNeeded != 0 || medNeeded != 0 || lrgNeeded != 0) {
                    return false;
                }
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
    /*
    public confirmAllReservation() {
        //add all temp=false booking objs to masterlist
        for room in roomList
        room.confirmRoom
    }
    */
}

