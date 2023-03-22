/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Student;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author sonnt
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> listSessionsDates(int id) {
        ArrayList<Session> listSessionsDates = new ArrayList<>();
        try {
            String sql = "SELECT sesid,date\n"
                    + "  FROM [Session] \n"
                    + "   WHERE gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setSesid(rs.getInt("sesid"));
                s.setDate(rs.getDate("date"));
                listSessionsDates.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSessionsDates;
    }

    public ArrayList<Session> filter(String lid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT ses.sesid,ses.[date],ses.attanded\n"
                    + "                    ,l.lid,l.lname\n"
                    + "                    ,g.gid,g.gname\n"
                    + "                    ,sub.subid,sub.subname\n"
                    + "                    ,r.rid,r.rname\n"
                    + "                    ,t.tid,t.tdescription\n"
                    + "                    FROM [Session] ses \n"
                    + "                    			INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "                    			INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "                    			INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "                   			INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "                   			INNER JOIN Time_Slot t ON t.tid = ses.tid\n"
                    + "                    WHERE l.lid = ? AND ses.[date] >= ? AND ses.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                Lecturer l = new Lecturer();
                Room r = new Room();
                Group g = new Group();
                TimeSlot t = new TimeSlot();
                Subject sub = new Subject();

                session.setSesid(rs.getInt("sesid"));
                session.setDate(rs.getDate("date"));
                session.setAttended(rs.getBoolean("attended"));

                l.setLid(rs.getString("lid"));
                l.setLname(rs.getString("lname"));
                session.setLid(l);

                g.setGid(rs.getInt("gid"));
                g.setGname(rs.getString("gname"));
                session.setGid(g);

                sub.setSubid(rs.getString("subid"));
                sub.setSubname(rs.getString("subname"));
                g.setSubid(sub);

                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                session.setRid(r);

                t.setTid(rs.getInt("tid"));
                t.setTdescription(rs.getString("tdescription"));
                session.setTid(t);

                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Session getFilter(String subid, int gid) {
        try {
            String sql = "SELECT g.gid,g.gname,sub.subid,sub.subname,s.stdid,s.stdcode,s.stdfirstname,s.stdmidname,s.stdlastname,s.stdfullname,s.stdgmail \n"
                    + "FROM Subject sub\n"
                    + "INNER JOIN [Group] g ON g.subid = sub.subid \n"
                    + "INNER JOIN Student_Group sg ON sg.gid = g.gid\n"
                    + "INNER JOIN Student s ON s.stdid = s.stdid\n"
                    + "WHERE g.sem = ? and g.year = ? and sub.subid = ? and g.gid = '1'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subid);
            stm.setInt(2, gid);
            ResultSet rs = stm.executeQuery();
            Session filter = null;
            while (rs.next()) {
                if (filter == null) {
                    filter = new Session();

                    Group g = new Group();
                    g.setGid(rs.getInt("gid"));
                    g.setGname(rs.getString("gname"));
                    g.setYear(rs.getInt("year"));
                    g.setSem(rs.getString("sem"));
                    filter.setGid(g);

                    Subject sub = new Subject();
                    sub.setSubid(rs.getString("subid"));
                    sub.setSubname(rs.getString("subname"));
                    g.setSubid(sub);

                }
                //read student
                Student s = new Student();
                s.setStdid(rs.getString("stdid"));
                s.setStdname(rs.getString("stdname"));

                s.setStdgmail(rs.getString("stdgmail"));
                //read attandance
                Attendance a = new Attendance();
                a.setStdid(s);
                a.setSesid(filter);
                filter.getAtts().add(a);
            }
            return filter;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Session] SET attanded = 1 WHERE sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getSesid());
            stm.executeUpdate();

            //remove old attandances
            sql = "DELETE Attandance WHERE sesid = ?";
            PreparedStatement stm_delete = connection.prepareStatement(sql);
            stm_delete.setInt(1, model.getSesid());
            stm_delete.executeUpdate();

            //insert new attandances
            for (Attendance att : model.getAtts()) {
                sql = "INSERT INTO [Attandance]\n"
                        + "           ([sesid]\n"
                        + "           ,[stdid]\n"
                        + "           ,[present]\n"
                        + "           ,[tdescription]\n"
                        + "           ,[record_time])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement stm_insert = connection.prepareStatement(sql);
                stm_insert.setInt(1, model.getSesid());
                stm_insert.setString(2, att.getStdid().getStdid());
                stm_insert.setBoolean(3, att.isPresent());
                stm_insert.setString(4, att.getDescription());
                stm_insert.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Session get(String id) {
try {
            String sql = "SELECT ses.sesid,ses.date,ses.attanded\n"
                    + "                    	,g.gid,g.gname,g.year,g.sem\n"
                    + "                    	,r.rid,r.rname\n"
                    + "                    	,t.tid,t.tdescription\n"
                    + "                    	,l.lid,l.lname\n"
                    + "                    	,sub.subid,sub.subname\n"
                    + "                    	,s.stdid,s.stdfirstname,s.stdmidname,s.stdlastname,s.stdcode,s.stdgmail,s.stdfullname\n"
                    + "                    	,ISNULL(a.present,0) present, ISNULL(a.[description],'') [description]\n"
                    + "                    		FROM [Session] ses\n"
                    + "                    		INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "                    	INNER JOIN Time_Slot t ON t.tid = ses.tid\n"
                    + "                    		INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "                    		INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "                    		INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "                    		INNER JOIN [Student_Group] sg ON sg.gid = g.gid\n"
                    + "                    	    INNER JOIN [Student] s ON s.stdid = sg.stdid\n"
                    + "                    		LEFT JOIN Attandance a ON s.stdid = a.stdid AND ses.sesid = a.sesid\n"
                    + "                    WHERE ses.sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            Session ses = null;
            while (rs.next()) {
                if (ses == null) {
                    ses = new Session();
                    Room r = new Room();
                    r.setRid(rs.getInt("rid"));
                    r.setRname(rs.getString("rname"));
                    ses.setRid(r);

                    TimeSlot t = new TimeSlot();
                    t.setTid(rs.getInt("tid"));
                    t.setTdescription(rs.getString("tdescription"));
                    ses.setTid(t);

                    Lecturer l = new Lecturer();
                    l.setLid(rs.getString("lid"));
                    l.setLname(rs.getString("lname"));
                    ses.setLid(l);

                    Group g = new Group();
                    g.setGid(rs.getInt("gid"));
                    g.setGname(rs.getString("gname"));
                    g.setYear(rs.getInt("year"));
                    g.setSem(rs.getString("sem"));
                    ses.setGid(g);

                    Subject sub = new Subject();
                    sub.setSubid(rs.getString("subid"));
                    sub.setSubname(rs.getString("subname"));
                    g.setSubid(sub);

                    ses.setDate(rs.getDate("date"));
                    ses.setAttended(rs.getBoolean("attanded"));
                }
                //read student
                Student s = new Student();
                s.setStdid(rs.getString("stdid"));
                s.setStdname(rs.getString("stdname"));
                s.setStdgmail(rs.getString("stdgmail"));
                //read attandance
                Attendance a = new Attendance();
                a.setStdid(s);
                a.setSesid(ses);
                a.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("description"));
                ses.getAtts().add(a);
            }
            return ses;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    }
}
