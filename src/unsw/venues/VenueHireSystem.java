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

    public VenueHireSystem(VenueHireSystem VHS) {
        this();
        for(Venue v: VHS.getVenueList()) {
            this.venueList.add(new Venue(v));
        }
    }

    private VenueHireSystem processCommand(JSONObject json, VenueHireSystem system) {
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

                system = change(idChange, startChange, endChange, smallChange, mediumChange,
                        largeChange, system);
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
        return system;
    }

    public void addRoom(String venue, String room, String size) {
        for (Venue v : venueList) {
            if (v.getVenueName().equals(venue)) {
                System.out.println("adding room" + room + "to venue" + venue);
                v.appendRoom(room, size);
                //System.out.println("Room" + room + "is size"+ getSize(room) "and belongs to " + venue);
                return;
            }

        }
        Venue newVen = new Venue(venue);
        newVen.appendRoom(room, size);
        venueList.add(newVen);

        /*
         * JSONArray rooms = new JSONArray(); rooms.put("Penguin"); rooms.put("Hippo");
         * result.put("rooms", rooms);
         */
    }

    public JSONObject request(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();

        Reservation newRes = new Reservation(id, start, end, small, medium, large);
        for (Venue v : venueList) {
            //still looking through rest of the rooms even when a suitable venue has been
            //found and allocated :(
            System.out.println("Checking venue"+v.getVenueName());
            if (v.attemptBooking(newRes)) {
                // Booking is valid, set temp flag to false
                //System.out.println("This booking is valid booking to make");
                newRes.setFlag();
                result.put("status", "success");
                result.put("rooms", v.rmArray(id));
                result.put("venue", v.getVenueName());
                return result;
            } else {
                result.put("status", "rejected");
            }
        }

        return result;
    }

    public VenueHireSystem change(String id, LocalDate start, LocalDate end, int small, int medium, int large, VenueHireSystem oldSystem) {
        JSONObject changeResult = new JSONObject();
        //clone VHS
        VenueHireSystem cloneSystem = new VenueHireSystem(oldSystem);
        System.out.println("New VHS made");
        //in new system, remove existing booking
        cloneSystem.delete(id);
        //attempt new booking
        changeResult = cloneSystem.request(id, start, end, small, medium, large);
        //if booking suceeded, replace old system with new system
        //if JSON object status is success, set OG to be clone syste
        if(changeResult.getString("status").equals("success")) {
            System.out.println("about to copy clone to OG system");
            oldSystem = cloneSystem;
        }
        //if not, return JSON obj (status should already be rejected) and delete cloneSystem
        System.out.println(changeResult.toString(2));
        return oldSystem;
    }
    private void delete(String DeleteID) {
        //Look through VHS ven list
        for(Venue v: venueList) {
            v.deleteAllResID(DeleteID);
        }
    }

    private void list(String listId) {
        for (Venue v : venueList) {
            if (v.getVenueName().equals(listId)) {
                System.out.println(v.venArray());
                
            }
        }
    }

    public ArrayList<Venue> getVenueList() {
        return venueList;
    }

    public static void main(String[] args) {
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
            
            e.printStackTrace();
            return;
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system = system.processCommand(command, system);
            }
        }
        sc.close();
    }

}
