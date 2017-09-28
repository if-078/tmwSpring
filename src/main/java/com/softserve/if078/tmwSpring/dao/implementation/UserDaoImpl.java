package com.softserve.if078.tmwSpring.dao.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.softserve.if078.tmwSpring.dao.DaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserve.if078.tmwSpring.entities.User;

@Component
public class UserDaoImpl implements DaoInterface<User> {
	
	private final String tabName = "tmw.user";

	@Autowired
	DataSource datasource;

	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM " + tabName;
		try (Statement stmt = datasource.getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(sql);) {

			List users = new ArrayList<>();
			while (rs.next()) {
				User user = extractUserFromResultSet(rs);
				users.add(user);
			}
			return users;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}


/*NEED CHANGE IMPLEMENTATION*/
	@Override
	public User get(User entity) {
		String sql = "SELECT * FROM "+ tabName + " WHERE user_id=" +entity.getId();
		try (Statement stmt = datasource.getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(sql);) {
			if (rs.next()) {
				return extractUserFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	private User extractUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setName(rs.getString("name"));
		user.setPass(rs.getString("pass"));
		user.setEmail(rs.getString("email"));

		return user;
	}

	@Override
	public void update(User entity) {
		String sql = "UPDATE "+tabName+" SET name=?, pass=?, email=? WHERE user_id=?";
		try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql);) {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getPass());
			ps.setString(3, entity.getEmail());
			ps.setInt(4, entity.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	@Override
	public void delete(User entity) {
		String sql = "DELETE FROM "+tabName+" WHERE user_id=" + entity.getId();
		try (Statement stmt = datasource.getConnection().createStatement();) {
		stmt.executeUpdate(sql);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void create(User entity) {
		String sql = "INSERT INTO "+tabName+" (name, pass, email)  VALUES (?, ?, ?)";
		try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql)) {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getPass());
			ps.setString(3, entity.getEmail());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
