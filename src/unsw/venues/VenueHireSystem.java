/**
 *
 */
package unsw.venues;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
//import java.io.File;

import org.json.JSONArray;
//import org.json.JSONArray;
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

    public void showRoomSizes(Venue v) {
        for(Venue venue: venueList) {
            if(v.getVenueName().equals(v)) {
                System.out.println("Venue" +v+"has "+v.getSmlRooms()+"sml\n"+v.getMedRooms()+"med\n"+v.getLrgRooms()+"lrg\n");
            }
        }
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

            case "change":
                String idChange = json.getString("id");
                LocalDate startChange = LocalDate.parse(json.getString("start"));
                LocalDate endChange = LocalDate.parse(json.getString("end"));
                int smallChange = json.getInt("small");
                int mediumChange = json.getInt("medium");
                int largeChange = json.getInt("large");

                JSONObject resultChange = request(idChange, startChange, endChange, smallChange, mediumChange,
                        largeChange);
                
                    System.out.println(resultChange.toString(2));
                break;
            case "cancel":
                String DeleteId = json.getString("id");
                delete(DeleteId);
                break;
            case "list":
                String listId = json.getString("venue");
                System.out.println("listing " + listId);
                list(listId);
                //JSONArray listPrint = list(listId);
                
                break;
            default:
                System.out.println("oi nup");
        }

    }

    public void addRoom(String venue, String room, String size) {
        for (Venue v : venueList) {
            if (v.getVenueName().equals(venue)) {
                System.out.println("adding room" + room + "to venue" + venue);
                v.appendRoom(room, size);
                showRoomSizes(v);
                //System.out.println("Room" + room + "is size"+ getSize(room) "and belongs to " + venue);
                return;
            }

        }
        Venue newVen = new Venue(venue);
        newVen.appendRoom(room, size);
        venueList.add(newVen);
        showRoomSizes(newVen);

        /*
         * JSONArray rooms = new JSONArray(); rooms.put("Penguin"); rooms.put("Hippo");
         * result.put("rooms", rooms);
         */
    }

    public JSONObject request(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();

        Reservation newRes = new Reservation(id, start, end, small, medium, large);
        for (Venue v : venueList) {
            if (v.attemptBooking(newRes)) {
                // Booking is valid, set temp flag to false
                newRes.setFlag();
                result.put("status", "success");
                result.put("venue", v);
            } else {
                result.put("status", "rejected");
            }
        }

        return result;
    }

    private void delete(String DeleteID) {

    }

    private void list(String listId) {
        for (Venue v : venueList) {
            if (v.getVenueName().equals(listId)) {
                System.out.println(v.venArray());
                
            }
        }
    }

    public static void main(String[] args) {
        //TODO CHANGE BACK TO STDIN, NO FILE READING IN FINAL YOU NUMPTY
        VenueHireSystem system = new VenueHireSystem();

        /*File fileObj = new File("input1.txt");
                Scanner fileReader = new Scanner(fileObj);
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
        */            
        //Scanner sc = new Scanner(System.in);
        File fileObj = new File("input1.txt");
        Scanner sc;
        try {
            sc = new Scanner(fileObj);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

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
