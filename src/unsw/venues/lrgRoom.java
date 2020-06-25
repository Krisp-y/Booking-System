package unsw.venues;

public class lrgRoom extends Room {
    public Room(String roomName, String size, ArrayList<Reservation> reservationList) {
        super(roomName,size,reservationList);
    }
    public lrgRoom (String size) {
        this.getsize() = "large";
    }
}