/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLayer.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HoangNgocPhu
 */
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    private int ID;
    private String name;
    private Date date;
    private String location;
    private int numberOfAttendees;
    private boolean status;
        public Event(int ID, String name, Date date, String location, int numberOfAttendees, boolean status) {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.location = location;
        this.numberOfAttendees = numberOfAttendees;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
//    public String getThamgia() {
//        return status ? "Tham gia" : "Không tham gia";
//    }
    //thêm chức năng mưới tìm tất cả các event mà tên event chứa chuỗi s 
    //( s nhập từ bàn phím không phần biệt hoa thường )
}
