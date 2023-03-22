/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.TimeSlot;
import model.Subject;

/**
 *
 * @author sonnt
 */
public class LecturerDBContext extends DBContext<Lecturer> {

    public ArrayList<Session> getSessions(int lid) {
        String sql = "SELECT ses.sesid,ses.date,ses.attended,g.gid,g.gname,s.subid,s.subname,r.rid,r.rname,t.tid,t.tdescription \n"
                + "FROM [Session] ses INNER JOIN [Group] g ON ses.gid = g.gid\n"
                + "					INNER JOIN Subject s ON s.subid = g.subid\n"
                + "					INNER JOIN Room r ON r.rid = ses.rid\n"
                + "					INNER JOIN Time_Slot t ON t.tid = ses.tid\n"
                + "WHERE ses.lid = ?";
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setDate(rs.getDate("date"));
                session.setAttended(rs.getBoolean("attened"));
                session.setSesid(rs.getInt("sesid"));
                
                Group g = new Group();
                g.setGid(rs.getInt("gid"));
                g.setGname(rs.getString("gname"));
                session.setGid(g);
                
                Subject s = new Subject();
                s.setSubid(rs.getString("subid"));
                s.setSubname(rs.getString("cname"));
                g.setSubid(s);
                
                Room r = new Room();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                session.setRid(r);
                
                TimeSlot t = new TimeSlot();
                t.setTid(rs.getInt("tid"));
                t.setTdescription(rs.getString("tdescription"));
                session.setTid(t);
                
                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(sessions.size());
        return sessions;
    }

    @Override
    public void insert(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    public ArrayList<Lecturer> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Lecturer> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lecturer get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
