package unsw.venues;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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
                //Room is free for date window
                return true;
            }
        }
        return true;
    }

    public String getSize() {
        return size;
    }

    public JSONArray resArray() {
        JSONArray newArray = new JSONArray();
        //sort
        for(Reservation res: reservationList) {
            newArray.put(res.resToJSON());
        }
        return newArray;
    }
}

    
