package Application.UI;
import BussinessLayer.Entity.Event;
import BussinessLayer.Service.EventService;
import DataLayer.FileManager;
import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        FileManager fileManager = new FileManager("events.txt");
        FileManager.loadFromFile("./events.txt", events);
        EventService eventService = new EventService(events); 
        Menu menu = new Menu(eventService, fileManager, events);
        menu.processMenuForCustomer();
    }
}
