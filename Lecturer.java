/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import jakarta.websocket.Session;
import java.security.acl.Group;
import java.util.ArrayList;

/**
 *
 * @author sonnt
 */
public class Lecturer {
    private String lid;
    private String lname;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();
    private String lgmail;

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public String getLgmail() {
        return lgmail;
    }

    public void setLgmail(String lgmail) {
        this.lgmail = lgmail;
    }
    
    
    
}
