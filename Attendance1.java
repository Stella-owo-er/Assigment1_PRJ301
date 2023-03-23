/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dell
 */
public class Attendance1 {
    private int aid;
    private Student stdid;
    private Session sesid;
    private Boolean present;
    private TimeSlot tdescription;

    public Attendance1() {
    }

    public Attendance1(int aid, Student stdid, Session sesid, Session attended, TimeSlot tdescription) {
        this.aid = aid;
        this.stdid = stdid;
        this.sesid = sesid;
        this.present = present;
        this.tdescription = tdescription;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Student getStdid() {
        return stdid;
    }

    public void setStdid(Student stdid) {
        this.stdid = stdid;
    }

    public Session getSesid() {
        return sesid;
    }

    public void setSesid(Session sesid) {
        this.sesid = sesid;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    

    public TimeSlot getTdescription() {
        return tdescription;
    }

    public void setTdescription(TimeSlot tdescription) {
        this.tdescription = tdescription;
    }
          
    

}
