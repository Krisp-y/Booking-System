package unsw.venues;

import java.util.ArrayList;

import org.json.JSONArray;
//import org.json.JSONObject;

import java.time.LocalDate;

public class Room {
    private String roomName;
    private String size;
    private ArrayList<Reservation> reservationList;

    public Room(String roomName, String size) {
        this.roomName = roomName;
        this.size = size;
        this.reservationList= new ArrayList<Reservation>();

    } 

    public Room(Room rm) {
        this(rm.getName(),rm.getSize());
        for(Reservation r: rm.getReservations()) {
            this.reservationList.add(new Reservation(r));
        }
    }

    public String getName() {
        return roomName;
    }
    //Only called after reservation validity is confirmed
    public void storeReservation(Reservation r) { 
        this.reservationList.add(r);
    }

    public ArrayList<Reservation> getReservations(){
        return reservationList;
    }

    public void removeReservation(Reservation r) {
        reservationList.remove(r);
    }

    public boolean roomAvailable(Reservation r) {
        //Tentative reservation dates
        //System.out.println("Checking for reservation "+r.getReservationName());
        LocalDate TRstart = r.getStartDate();
        LocalDate TRend = r.getEndDate();
        //empty list case
        /*
        if(reservationCount() == 0) {
            System.out.println("This room has no reservations so it can be booked");
            return true;
            
        }
        */
        for(Reservation res: reservationList) {
            //Start/end date for existing reservation in list
            //System.out.println("In reslist for loop");
            LocalDate ERstart = res.getStartDate();
            LocalDate ERend = res.getEndDate();
            if(TRstart.isEqual(ERstart) || TRend.isEqual(ERstart) || TRstart.isEqual(ERend) || TRend.isEqual(ERend)) {
                //System.out.println("DATE CLASH");
                return false;
            } else if(TRstart.isAfter(ERstart) && TRend.isBefore(ERend)) {
                //System.out.println("existing res occupies whole window");
                return false;
            } else if (TRstart.isBefore(ERend) && TRend.isAfter(ERstart)) {
                //System.out.println("existing res starts before yours ends");
                return false;
            } else if (TRstart.isBefore(ERstart) && TRend.isAfter(ERend)) {
                //System.out.println("existing res starts and ends during your window");
                return false;
            } else if (TRstart.isAfter(ERstart) && TRend.isBefore(ERend)) {
                //System.out.println("existing res not done yet");
                return false;
            } 
        }
        return true;
    }

    public String getSize() {
        return size;
    }

    public int reservationCount() {
        return reservationList.size();
    }

    public JSONArray resArray() {
        JSONArray newArray = new JSONArray();
        //sort
        for(Reservation res: reservationList) {
            newArray.put(res.resToJSON());
        }
        return newArray;
    }


    public boolean resInList(String resID) {
        for(Reservation r: reservationList) {
            if(r.getReservationName().equals(resID)) {
                return true;
            }
        }
        return false;
    }

    public void removeResByID(String resID) {
        Reservation marker = null;
        for(Reservation r: reservationList) {
            if(r.getReservationName().equals(resID)) {
                marker = r;
                break;
            }
        }
        if (marker != null) {
            removeReservation(marker);
        }
    }
}

    
