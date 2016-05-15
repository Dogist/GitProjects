/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.dao;

import at.htlpinkafeld.rs_personresource.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Martin Six
 */
public class PersonJdbcDao extends JdbcBaseDao<Person> implements PersonDao {

    public PersonJdbcDao() throws SQLException {
        super("PERSON", "ID");
    }

    @Override
    protected Person getPojoFromResultSet(ResultSet result) throws SQLException {
        return new Person(result.getInt("id"), result.getString("firstname"), result.getString("lastName"), result.getString("email"));
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection c, Person p) throws SQLException {
        String sql = "UPDATE " + getTablename() + " SET FIRSTNAME=?, LASTNAME=?, EMAIL=? WHERE " + getPkName() + "=?;";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setString(1, p.getFirstName());
        stmt.setString(2, p.getLastName());
        stmt.setString(3, p.getLastName());
        stmt.setInt(4, p.getId());
        return stmt;
    }

    @Override
    protected PreparedStatement getInsertStatement(Connection c, Person p) throws SQLException {
        String sql = "INSERT INTO " + getTablename() + " (FIRSTNAME, LASTNAME, EMAIL) VALUES (?,?,?);";
        PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, p.getFirstName());
        stmt.setString(2, p.getLastName());
        stmt.setString(3, p.getEmail());
        return stmt;
    }

}
