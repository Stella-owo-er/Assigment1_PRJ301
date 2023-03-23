/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 *
 * @author sonnt
 */
public class Student {
    private String stdid;
    
    private String stdname;
    private String stdgmail;
    
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Attendance> atts = new ArrayList<>();

    public Student() {
    }

    public Student(String stdid, String stdname, String stdgmail) {
        this.stdid = stdid;
        this.stdname = stdname;
        this.stdgmail = stdgmail;
    }

    
    public String getStdid() {
        return stdid;
    }

    public void setStdid(String stdid) {
        this.stdid = stdid;
    }

    public String getStdname() {
        return stdname;
    }

    public void setStdname(String stdname) {
        this.stdname = stdname;
    }

    public String getStdgmail() {
        return stdgmail;
    }

    public void setStdgmail(String stdgmail) {
        this.stdgmail = stdgmail;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Attendance> getAtts() {
        return atts;
    }

    public void setAtts(ArrayList<Attendance> atts) {
        this.atts = atts;
    }

    
    
}
