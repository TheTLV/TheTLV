package BussinessLayer.Components;

import BussinessLayer.Entity.Event;
import java.util.*;
import java.text.SimpleDateFormat;

public class CheckInput {

    public static String getString(String welcome) {
        System.out.print(welcome);
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();
        return result;
    }

    public static String getString(String welcome, String msg) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getStringreg(String welcome, String pattern, String msg) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {

            System.out.print(welcome);
            result = sc.nextLine();
            if (!result.matches(pattern)) {
                System.out.println(msg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getStringreg(String welcome, String pattern, String msg, String msgreg) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {

            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else if (!result.matches(pattern)) {
                System.out.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String validatePhoneNumber(String prompt, String errorMessage) {
        while (true) {
            String phoneNumber = CheckInput.getString(prompt, errorMessage);
            if (phoneNumber.replaceAll("\\s", "").matches("\\d+")) {
                return phoneNumber;
            } else {
                System.out.println("Invalid phone number. Please enter numeric digits only.");
            }
        }
    }

    public static int getInt(String welcome) {
        System.out.print(welcome);
        Scanner sc = new Scanner(System.in);
        int number = Integer.parseInt(sc.nextLine());
        return number;
    }

    public static int getInt(String welcome, String errorMessage) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        } while (check);
        return number;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large or equal than " + min);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large or equal than " + min);
                }
                if (number > max) {
                    System.out.println("Number must be less or equal than " + max);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static float getfloat(String welcome, int min) {
        boolean check = true;
        float number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Float.parseFloat(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large than " + min);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static boolean checkExistEvent(int eventID, ArrayList<Event> events) {
        if (events.isEmpty()) {
            return false;
        }
        for (Event event : events) {
            if (event.getID() == (eventID)) {
                return true;
            }
        }
        return false;
    }

    public static Date getValidatedDate(String welcome,String msg ) {
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);  // Ensure strict parsing
        Date date = null;

        while (date == null) {
            String dateString = sc.nextLine();
            try {
                date = dateFormat.parse(dateString);
            } catch (Exception e) {
                System.out.println(msg);
            }
        }
        return date;
    }

    public static Date getValidatedDate(String welcome, String msg, Date dt) {
        Scanner sc = new Scanner(System.in);
            
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //input date canbe update
        Date date = null;
        do {
            System.out.print(welcome);
            String input = sc.nextLine();
            if (!input.isEmpty()) {

                try {
                    date = dateFormat.parse(input);
                } catch (Exception e) {
                    System.out.println(msg);
                }
            }
            else {
                break;
            }
        } while (date == null);
        return date;
    }

    public static void displayEventList(List<Event> events) {
        if (events == null || events.isEmpty()) {
            System.out.println("No events available to display.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s%-20s%-20s%-30s%-15s%-10s%n",
                "Event ID", "Event Name", "Event Date", "Location", "Attendees", "Status");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Event event : events) {
            System.out.printf("%-30d%-20s%-20s%-30s%-15d%-10s%n",
                    event.getID(),
                    event.getName(),
                    new SimpleDateFormat("yyyy-MM-dd").format(event.getDate()),
                    event.getLocation(),
                    event.getNumberOfAttendees(),
                    event.isStatus() ? "Active" : "Inactive");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public static void displayTEventList(List<Event> events) {
    if (events == null || events.isEmpty()) {
        System.out.println("No events available to display.");
        return;
    }

    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-30s%-20s%-20s%-30s%-15s%-10s%-15s%n",
            "Event ID", "Event Name", "Event Date", "Location", "Attendees", "Status", "Tham gia");
    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    for (Event event : events) {
        System.out.printf("%-30d%-20s%-20s%-30s%-15d%-10s%-15s%n",
                event.getID(),
                event.getName(),
                new SimpleDateFormat("yyyy-MM-dd").format(event.getDate()),
                event.getLocation(),
                event.getNumberOfAttendees(),
                event.isStatus() ? "Active" : "Inactive",
                event.isStatus() ? "Tham gia" : "Không tham gia");
    }
    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
}


    public static boolean run() {
        boolean run = true;
        int choice = CheckInput.getInt("Do you want to contunue ?\n1.Continue\n2.Back to Menu\nSelect:", 1, 2);
        switch (choice) {
            case 1:
                break;
            case 2:
                run = false;
        }
        return run;
    }
    
    public static boolean checkdate(){
        
        return true;
    }
    
}
