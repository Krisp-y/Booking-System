package unsw.venues;

import java.util.ArrayList;

public class Venue {
    private String venueName;
    private ArrayList<Room> roomList;
    

    public Venue (String venueName){
        this.venueName = venueName;
        roomList = new ArrayList<>();
    }
}

