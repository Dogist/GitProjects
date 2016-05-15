/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dm.dao;

import at.htlpinkafeld.dm.pojo.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class CustomerDAO {

    private static final String CUSTOMER_ID = "customer_id";
    private static final String DISCOUNT_CODE = "discount_code";
    private static final String ZIP = "zip";
    private static final String NAME = "name";
    private static final String ADDRESSLINE1 = "addressline1";
    private static final String ADDRESSLINE2 = "addressline2";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String PHONE = "phone";
    private static final String FAX = "fax";
    private static final String EMAIL = "email";
    private static final String CREDIT_LIMIT = "credit_limit";

    public List<Customer> getEntityList() {
        List<Customer> retVal = new LinkedList<>();

        try (Connection con = ConnectionManager.getInst().getConn();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from customer")) {
            while (rs.next()) {
                retVal.add(new Customer(rs.getInt(CUSTOMER_ID), rs.getString(DISCOUNT_CODE).charAt(0), rs.getInt(ZIP), rs.getString(NAME), rs.getString(ADDRESSLINE1), rs.getString(ADDRESSLINE2), rs.getString(CITY), rs.getString(STATE), rs.getString(PHONE), rs.getString(FAX), rs.getString(EMAIL), rs.getInt(CREDIT_LIMIT)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retVal;
    }

    public Customer findByID(int customer_id) {
        Customer retVal = null;

        try (Connection con = ConnectionManager.getInst().getConn();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from customer where customer_id=" + customer_id)) {
            if (rs.next()) {
                retVal = new Customer(rs.getInt(CUSTOMER_ID), rs.getString(DISCOUNT_CODE).charAt(0), rs.getInt(ZIP), rs.getString(NAME), rs.getString(ADDRESSLINE1), rs.getString(ADDRESSLINE2), rs.getString(CITY), rs.getString(STATE), rs.getString(PHONE), rs.getString(FAX), rs.getString(EMAIL), rs.getInt(CREDIT_LIMIT));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retVal;
    }

}
