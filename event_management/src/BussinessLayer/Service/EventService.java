/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLayer.Service;

import BussinessLayer.Components.CheckInput;
import BussinessLayer.Entity.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author HoangNgocPhu
 */
public class EventService {

    private ArrayList<Event> events;

    public EventService(ArrayList<Event> events) {
        this.events = events;
    }

    private final Random random = new Random();

    public void displayEventList() {
        if (events.isEmpty()) {
            System.out.println("No events available to display.");
            return;
        }
        Collections.sort(events, Comparator.comparing(Event::getDate).thenComparing(Event::getName));
//,reO()
        CheckInput.displayEventList(events);
    }

    public void addNewEvent() {
        boolean run = true;
        do {
            String eventName = CheckInput.getString("Enter Event Name: ", "Name cannot be empty.");
            Date eventDate = CheckInput.getValidatedDate("Enter Event Date (yyyy-MM-dd):", "Date invalid . Please enter Date (yyyy-MM-dd):");
            String eLocation = CheckInput.getString("Enter Event Location: ", "Location cannot be empty.");
            int numberOfAttendees = CheckInput.getInt("Enter Number of Attendees: ", 0);
            boolean status = CheckInput.getStringreg("Is the event active? (y/n): ", "^[ynYN]$", "Invalid input. Please enter 'y' or 'n'.", "Invalid input. Please enter 'y' or 'n'.").equalsIgnoreCase("y");
            int id = generateUniqueID();
            Event newEvent = new Event(id, eventName, eventDate, eLocation, numberOfAttendees, status);
            events.add(newEvent);
            System.out.println("Event added successfully with ID: " + id);
            run = CheckInput.run();
        } while (run);
    }

    private int generateUniqueID() {
        int id;
        do {
            id = random.nextInt(100);
        } while (CheckInput.checkExistEvent(id, events));
        return id;
    }

    public void eventStatus() {
        boolean run = true;
        do {
            int searchEventID = CheckInput.getInt("Enter the Event ID to check exist :", "ID cannot be empty");
            boolean status = CheckInput.checkExistEvent(searchEventID, events);
            if (!status) {
                System.out.println("No Event Found!");
            } else {
                System.out.println("Exist Event");
            }
            run = CheckInput.run();
        } while (run);
    }

    public void deleteEvent() {
        boolean run = true;
        do {
            int delete_id = CheckInput.getInt("Enter the Event ID to delete:", "ID cannot be empty");

            boolean isDeleted = false;
            for (Event e : events) {
                if (CheckInput.checkExistEvent(delete_id, events)) {
                    events.remove(e);
                    isDeleted = true;
                    System.out.println("Event with ID " + delete_id + " deleted successfully.");
                    break;
                }
            }
            if (!isDeleted) {
                System.out.println("Event with ID " + delete_id + " not found.");
            }
            run = CheckInput.run();
        } while (run);
    }

    public void updateEvent() {
        boolean run = true;
        do {
            int eventID = CheckInput.getInt("Enter Event ID to update:", "ID cannot be empty");

            Event eUpdate = null;

            for (Event e : events) {
                if (e.getID() == eventID) {
                    eUpdate = e;
                    break;
                }
            }

            if (eUpdate == null) {
                System.out.println("Event does not exist");
                return;
            }

            System.out.println("Leave blank to keep the current value.");
            //name---------------------------------------------------------------------
            String newName = CheckInput.getString("Enter new Event Name :");
            if (!newName.isEmpty()) {
                eUpdate.setName(newName);
            }
            //date---------------------------------------------------------------------
            Date newDate = CheckInput.getValidatedDate("Enter date:", "Date invalid . Please enter Date (yyyy-MM-dd):", eUpdate.getDate());
            if (newDate != null) {
                eUpdate.setDate(newDate);
            }
            //location-----------------------------------------------------------------
            String newLocation = CheckInput.getString("Enter new Event Location :");
            if (!newLocation.isEmpty()) {
                eUpdate.setLocation(newLocation);
            }
            //attendees----------------------------------------------------------------
            Integer newAttendees = null;
            while (true) {
                String input = CheckInput.getString("Enter new Number of Attendees :");
                if (input.isEmpty()) {
                    break;
                }
                try {
                    newAttendees = Integer.parseInt(input);
                    if (newAttendees >= 0) {
                        eUpdate.setNumberOfAttendees(newAttendees);
                        break;
                    } else {
                        System.out.println("Number of attendees cannot be negative. Please enter a valid number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
            //status--------------------------------------------------------------------
            String status = CheckInput.getStringreg("Is the event active? (y/n): ", "^[ynYN]?$", "Invalid input. Please enter 'y' or 'n'.");
            if (!status.isEmpty()) {
                boolean newStatus = status.equalsIgnoreCase("y");;
                eUpdate.setStatus(newStatus);
            }
            System.out.println("Event nulld successfully.");
            run = CheckInput.run();
        } while (run);
    }

    public void searchByEventLocation() {
        boolean run = true;
        do {
            String searchEventLocation = CheckInput.getString("Enter Event Address to search: ", "");
            ArrayList<Event> e = new ArrayList<>();
            //đối tượng event tên là *** duyệt theo events ( list events có kiểu Event)
            for (Event event : events) {
                //sửa------------------------------------------------------------
                if (event.getLocation().contains(searchEventLocation)) {
                    e.add(event);
                }
            }
            
            if (!e.isEmpty()) {
                Collections.sort(e, Comparator.comparing(Event::getNumberOfAttendees));
                
                CheckInput.displayEventList(e);
            } else {
                System.out.println("No events available to display.");
            }
            
            run = CheckInput.run();
        } while (run);
    }
    public void searchByEventName() {
        boolean run = true;
        do {
            String eventName = CheckInput.getString("Enter Event Name: ", "Name cannot be empty.").toLowerCase();
            ArrayList<Event> e = new ArrayList<>();
            for (Event event : events) {
                if (event.getName().toLowerCase().contains(eventName)) {
                    e.add(event);
                }
            }
            
            if (!e.isEmpty()) {
                Collections.sort(e, Comparator.comparing(Event::getNumberOfAttendees));
                
                CheckInput.displayEventList(e);
            } else {
                System.out.println("No events available to display.");
            }
            
            run = CheckInput.run();
        } while (run);
    }
}









//    public void updateDEvent() {
//
//        Date eventDate = CheckInput.getValidatedDate("Enter Event Date to update:", "Date cannot be empty");
//        Event eUpdate = null;
//        String input = CheckInput.getString("Enter new Number of Attendees :");
//        for (Event e : events) {
//            if (e.getDate().equals(eventDate)) {
//                eUpdate = e;
//
//                Integer newAttendees = null;
//                if (input.isEmpty()) {
//                    break;
//                }
//                try {
//                    newAttendees = Integer.parseInt(input);
//                    if (newAttendees >= 0) {
//                        eUpdate.setNumberOfAttendees(newAttendees);
//                    } else {
//                        System.out.println("Number of attendees cannot be negative. Please enter a valid number.");
//                    }
//                } catch (NumberFormatException z) {
//                    System.out.println("Invalid input. Please enter a valid integer.");
//                }
//            }
//            System.out.println("Event does not exist");
//        } else {
//            System.out.println("Event update successfully.");
//        }
//    }
    
    
    
    
    
    
//    public List<Event> searchE(Date startDate, Date endDate) {
//        List<Event> foundEvents = new ArrayList<>();
//        for (Event event : events) {
//            if (!event.getDate().before(startDate) && !event.getDate().after(endDate)) {
//                foundEvents.add(event);
//            }
//        }
//        return foundEvents;
//    }
//
//    public void updateEBetweenDates() {
//        Date startDate = CheckInput.getValidatedDate("Enter start date (yyyy-MM-dd):", "Invalid date format.");
//        Date endDate = CheckInput.getValidatedDate("Enter end date (yyyy-MM-dd):", "Invalid date format.");
//        if (startDate.after(endDate)) {
//            System.out.println("Start date cannot be after end date.");
//            return;
//        }
//        List<Event> eventsRange = searchE(startDate, endDate);
//        if (eventsRange.isEmpty()) {
//            System.out.println("No events found in range.");
//            return;
//        }
//        int newAttendees = CheckInput.getInt("Enter the new number of attendees: ", 0);
//        for (Event event : eventsRange) {
//            event.setNumberOfAttendees(newAttendees);
//        }
//        System.out.println("Updated the number of attendees for all events in the specified date range.");
//    }

