package unsw.venues;

//import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public Venue(Venue v) {
        this(v.getVenueName());
        this.roomList = new ArrayList<Room>();
        for(Room rm: v.roomList) {
            this.roomList.add(new Room(rm));
        }
        this.sml = v.getSmlCount();
        this.med = v.getMedCount();
        this.lrg = v.getLrgCount();

    }
    //Checks if venue satisfies date/room requirements of booking
    public boolean attemptBooking (Reservation r){
        //When venue can never satisfy room requirements, exit
        if(!hasEnoughRooms(r)) {
            //System.out.println(this.venueName+"does not have enough rooms for reservation"+r.getReservationName());
            return false;    
        }
        int smlNeeded = r.getSmlCount();
        int medNeeded = r.getMedCount();
        int lrgNeeded = r.getLrgCount();
        //System.out.println("This booking needs "+smlNeeded+"sml, "+medNeeded+"med, "+lrgNeeded+"lrg");

        
            for(Room prospectRoom: roomList) {
                //Enough rooms have been allocated
                if(smlNeeded == 0 && medNeeded == 0 && lrgNeeded == 0) {
                    return true;
                }
                 //System.out.println("Checking "+prospectRoom.getName());
                if(prospectRoom.roomAvailable(r)) {
                    //add valid room to res room list
                    if(smlNeeded > 0 && prospectRoom.getSize().equals("small")) {
                        //System.out.println("an available small room has been found");
                        prospectRoom.storeReservation(r);
                        smlNeeded--;
                    }
                    if(medNeeded > 0 && prospectRoom.getSize().equals("medium")) {
                        //System.out.println("an available medium room has been found");
                        prospectRoom.storeReservation(r);
                        medNeeded--;
                    }
                    if(lrgNeeded > 0 && prospectRoom.getSize().equals("large")) {
                        //System.out.println("an available large room has been found");
                        prospectRoom.storeReservation(r);
                        lrgNeeded--;
                    }
                    //r.getRoomList().add(prospectRoom);
                    //prospectRoom.storeReservation(r);
                    //System.out.println("A room of size "+prospectRoom.getSize()+"has been tentatively allocated");
                    //System.out.println("We still need "+smlNeeded+"s, "+medNeeded+"m, "+lrgNeeded+"l.");
                    //add reservation r to prospect instead
                }
            }
            //Not enough rooms are available at this venue during date range
            if(smlNeeded != 0 || medNeeded != 0 || lrgNeeded != 0) {
                //go through all rooms in this venues list, clear r as it can't be booked
                //System.out.println("Not enough rooms are avaialble during this date window, "+venueName+"can't accomodate"+r.getReservationName());
                //System.out.println("Still need "+smlNeeded+"sml, "+medNeeded+"med, "+lrgNeeded+"lrg");
                for(Room toClear: roomList) {
                    toClear.removeReservation(r);
                }
                return false;    
            }
                
        return true;    
    }

    public boolean hasEnoughRooms(Reservation r) {
        //System.out.println("Venue being check for enough rooms, "+venueName+ " has"+sml+"sml "+med+"med "+lrg+"lrg");
        if(r.getSmlCount() > sml || r.getMedCount() > med || r.getLrgCount() > lrg) {
                return false;
            } else {
                return true;
            }
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    //Delete all res objects from room list of certain ID
    public void deleteAllResID(String DeleteID) {
        for(Room rm: roomList) {
            //System.out.println("Checking room"+rm.getName());
            if(rm.resInList(DeleteID)) {
                //System.out.println("Room Id"+rm.getName()+"has been found");
                rm.removeResByID(DeleteID);
                //return true;
            }
        }
        //return false;
    }

    public ArrayList<Room> getLrgRooms() {
        ArrayList<Room> LR = new ArrayList<>();
        for(Room r: roomList) {
            if(r.getSize().equals("Large")) {
                LR.add(r);
            }
        }
        return LR;
    }

    public ArrayList<Room> getMedRooms() {
        ArrayList<Room> MR = new ArrayList<>();
        for(Room r: roomList) {
            if(r.getSize().equals("Medium")) {
                MR.add(r);
            }
        }
        return MR;
    }

    public ArrayList<Room> getSmlRooms() {
        ArrayList<Room> SR = new ArrayList<>();
        for(Room r: roomList) {
            if(r.getSize().equals("Small")) {
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
 
    public JSONArray venArray() {
        JSONArray newVenArray = new JSONArray();
        //sort
        for(Room rm: roomList) {
            JSONObject newObj = new JSONObject();
            newObj.put("reservations", rm.sortedArray());
            newObj.put("room", rm.getName());
            //need to put in date order
            newVenArray.put(newObj);
        }
        return newVenArray;
    }

  
    public void appendRoom (String room, String size) {
        Room rm = new Room(room, size);
        roomList.add(rm);
        //System.out.println("The room "+ rm.getName()+ "being added is of size "+rm.getSize());
        if(rm.getSize().equals("small")) {
            sml++;
        } else if (rm.getSize().equals("medium")) {
            med++;
        } else if(rm.getSize().equals("large")){
            lrg++;
        }
    }

    public int getSmlCount() {
        return sml;
    }

    public int getMedCount() {
        return med;
    }

    public int getLrgCount() {
        return lrg;
    }

  

    public ArrayList<String> rmArray(String bookingID) {
        //JSONObject bookingRooms = new JSONObject();
        //sort
        ArrayList<String> rooms = new ArrayList<String>();
        for(Room rm: roomList) {
            if(rm.getRoomByID(bookingID)) {
                rooms.add(rm.getName());  
            }
        }
        return rooms;
        //bookingRooms.put("rooms", rooms.toString());
        //return bookingRooms;
    }

    //
}

