/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.UI;

import BussinessLayer.Components.CheckInput;
import BussinessLayer.Entity.Event;
import BussinessLayer.Service.EventService;
import DataLayer.FileManager;
import java.util.ArrayList;
/**
 *
 * @author HoangNgocPhu
 */
public class Menu {
    
    private EventService eventService;
    private FileManager fileManager;
    private ArrayList<Event> events;

    public Menu(EventService eventService, FileManager fileManager, ArrayList<Event> events) {
        this.eventService = eventService;
        this.fileManager = fileManager;
        this.events = events;
    }

    public void processMenuForCustomer() {
        boolean run = true;
        try {
            do {
                System.out.println("******Event Management******\n1.Add Event\n2.Check Event Exit\n3.Find Event\n"
                        + "4.Update Event\n5.Export to file\n6.Print Event List\n7.Quit");
                int choice = CheckInput.getInt("Select:", 1, 7);
                switch (choice) {
                    case 1: {
                        eventService.addNewEvent();
                        break;
                    }
                    case 2: {
//                        eventService.updateEBetweenDates();
//                        eventService.searchByEventName();
//                        eventService.updateDEvent();
                        break;
                    }
                    case 3: {
                         eventService.searchByEventName();
//                        eventService.searchByEventLocation();
                        break;
                    }
                    case 4: {
                        updateMenu();
                        break;
                    }
                    case 5: {
                        FileManager.saveToFile("events.txt",events);
                        break;
                    }
                    case 6: {
                        eventService.displayEventList();
                        break;
                    }
                    case 7:
                        run = false;
                }
            } while (run);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateMenu() {
        boolean run = true;
        do {
            int choice = CheckInput.getInt("1.Update Event\n2.Delete Event\n3.Back to Menu\nSelect:", 1, 3);
            switch (choice) {
                case 1: {
                    eventService.updateEvent();
                    break;
                }
                case 2: {
                    eventService.deleteEvent();
                    break;
                }
                case 3:
                    run = false;
                    break;
            }
        } while (run);
    }
}
