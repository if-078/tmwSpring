package com.softserve.if078.tmwSpring.dao;

import java.sql.Connection;
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

	@Autowired
	DataSource datasource;

	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM `task management wizard`.Users";
		//String sql = "SELECT * FROM \"task management wizard\".\"Users\"";
		Connection connection = null;
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
	public User get(Integer id) {
		String sql = "SELECT * FROM `task management wizard`.Users WHERE user_id=" + id;
		//String sql = "SELECT * FROM \"task management wizard\".\"Users\" WHERE user_id=" + id;
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

	@Override
	public boolean update(User entity, Integer id) {
		String sql = "UPDATE Users SET name=?, pass=?, email=? WHERE user_id=?";
		//String sql = "UPDATE \"task management wizard\".\"Users\" SET name=?, pass=?, email=? WHERE user_id=?";
		try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql);) {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getPass());
			ps.setString(3, entity.getEmail());
			ps.setInt(4, id);
			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(Integer id) {
		String sql = "DELETE FROM Users WHERE user_id=" + id;
		//String sql = "DELETE FROM \"Users\" WHERE user_id=" + id;
		try (Statement stmt = datasource.getConnection().createStatement();) {
			int i = stmt.executeUpdate(sql);
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean create(User entity) {
		//String sql = "INSERT INTO \"Users\" VALUES (NULL, ?, ?, ?)";
		String sql = "INSERT INTO Users VALUES (NULL, ?, ?, ?)";
		try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql)) {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getPass());
			ps.setString(3, entity.getEmail());
			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private User extractUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setName(rs.getString("name"));
		user.setPass(rs.getString("pass"));
		user.setEmail(rs.getString("email"));

		return user;
	}
}
