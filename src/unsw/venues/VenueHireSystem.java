/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author z5165630 
 *
 */
public class VenueHireSystem {

    ArrayList<Venue> venueList;

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    public VenueHireSystem() {
        this.venueList = new ArrayList<Venue>();
    }

    private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            JSONObject result = request(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;

        // TODO Implement other commands
        case "change":
            String idChange = json.getString("id");
            LocalDate startChange = LocalDate.parse(json.getString("start"));
            LocalDate endChange = LocalDate.parse(json.getString("end"));
            int smallChange = json.getInt("small");
            int mediumChange = json.getInt("medium");
            int largeChange = json.getInt("large");

            JSONObject resultChange = request(idChange, startChange, endChange, smallChange, mediumChange, largeChange);
            
        case "cancel":
            String DeleteId = json.getString("id");
            delete(DeleteId);
        case "list":
            

        }
    }

    private void addRoom(String venue, String room, String size) {
        // TODO Process the room command
        //check if room exists

        //find relevant venue, call venue.addroom(new room)
        //Update Venue's relevant room count
    }
   
    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        JSONObject result = new JSONObject();

        // TODO Process the request commmand
        //Look through venues in master list
        Reservation newRes = new Reservation(id, start, end, small, medium, large);
        //Check 
        for(Venue v: venueList) {
            if(v.attemptBooking(newRes)) {
                //Booking is valid, set temp flag to false
                newRes.setFlag();
            } else {
                return; //JSON object -> rejected 
            }
        }

        // FIXME Shouldn't always produce the same answer
        result.put("status", "success");
        result.put("venue", "Zoo");

        JSONArray rooms = new JSONArray();
        rooms.put("Penguin");
        rooms.put("Hippo");

        result.put("rooms", rooms);
        return result;
    }

    private void delete(String DeleteID) {
        //TODO
    }
    

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
