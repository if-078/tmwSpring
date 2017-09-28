package com.softserve.if078.tmwSpring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserve.if078.tmwSpring.entities.User;

@Component
public class UserDaoImpl implements AbstractDAO<User> {
	
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

	@Override
	public User get(int id) {
		String sql = "SELECT * FROM "+ tabName + " WHERE user_id=" + id;
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
	public void delete(int id) {
		String sql = "DELETE FROM "+tabName+" WHERE user_id=" + id;
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

	public List<User> getUsersByEmail (String email){
		String q = "SELECT id, name, pass FROM " + tabName + " WHERE email = ?";
		List<User> result = new ArrayList<>();
		try (PreparedStatement statement = datasource.getConnection().prepareStatement(q)){
			statement.setString(1 , email);
			try (ResultSet set = statement.executeQuery()){
				while (set.next()){
					User user = new User();
					user.setId(set.getInt(1));
					user.setName(set.getString(2));
					user.setPass(set.getString(3));
					user.setEmail(email);
					result.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
