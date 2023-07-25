/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import minhnd.entity.Account;
import minhnd.entity.Product;
import minhnd.utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class AccountDAO {

    private static final String LOGIN = "SELECT * FROM Account WHERE [user] = ? AND pass = ?";
    private static final String CHECK_LOGIN = "SELECT * FROM Account WHERE [user] = ?";
    private static final String SIGNUP = "INSERT INTO Account VALUES (?,?,0,0)";
    public Account login(String user, String pass) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, user);
                ptm.setString(2, pass);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    return new Account(rs.getInt(1), rs.getString(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5));
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Account checkLogin(String user) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_LOGIN);
                ptm.setString(1, user);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    return new Account(rs.getInt(1), rs.getString(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5));
                }
            }
        } catch (Exception e) {

        }
        return null;
    }
    
    public void signUp (String user, String pass){
         Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SIGNUP);
                ptm.setString(1, user);
                ptm.setString(2, pass);
                ptm.executeUpdate();
                }
        } catch (Exception e) {

        }
    }
}
