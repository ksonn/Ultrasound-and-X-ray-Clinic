/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Treatment;

/**
 *
 * @author Administrator
 */
public class TreatmentDBContext extends DBContext {

    public Treatment getTreatmentByPid(int pid) {
        try {
            String sql = "SELECT [tid]\n"
                    + "      ,[doctor]\n"
                    + "      ,[diagnose]\n"
                    + "      ,[solution]\n"
                    + "  FROM [Treatment]"
                    + " WHERE tid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Treatment t = new Treatment();
                t.setTid(pid);
                t.setDoctor(rs.getString("doctor"));
                t.setDiagnose(rs.getString("diagnose"));
                t.setSolution(rs.getString("solution"));
                return t;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreatmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void InsertPatient(Treatment t) {
        String sql = "INSERT INTO [Treatment]\n"
                + "           ([tid]\n"
                + "           ,[doctor]\n"
                + "           ,[diagnose]\n"
                + "           ,[solution])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, t.getTid());
            stm.setString(2, t.getDoctor());
            stm.setString(3, t.getDiagnose());
            stm.setString(4, t.getSolution());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PatientDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PatientDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PatientDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}