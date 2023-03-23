/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author sonnt
 */
public class Session {
    private int sesid;
    private Group gid;
    private Room rid;
    private Lecturer lid;
    private TimeSlot tid;
    private ArrayList<Attendance> atts = new ArrayList<>();
    private Date date;
    private boolean attended;

    public Session() {
    }

    public Session(int sesid, Group gid, Room rid, Lecturer lid, TimeSlot tid, Date date, boolean attended) {
        this.sesid = sesid;
        this.gid = gid;
        this.rid = rid;
        this.lid = lid;
        this.tid = tid;
        this.date = date;
        this.attended = attended;
    }

    
    public int getSesid() {
        return sesid;
    }

    public void setSesid(int sesid) {
        this.sesid = sesid;
    }

    public Group getGid() {
        return gid;
    }

    public void setGid(Group gid) {
        this.gid = gid;
    }

    public Room getRid() {
        return rid;
    }

    public void setRid(Room rid) {
        this.rid = rid;
    }

    public Lecturer getLid() {
        return lid;
    }

    public void setLid(Lecturer lid) {
        this.lid = lid;
    }

    public TimeSlot getTid() {
        return tid;
    }

    public void setTid(TimeSlot tid) {
        this.tid = tid;
    }

    public ArrayList<Attendance> getAtts() {
        return atts;
    }

    public void setAtts(ArrayList<Attendance> atts) {
        this.atts = atts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    

   

    
    
}
