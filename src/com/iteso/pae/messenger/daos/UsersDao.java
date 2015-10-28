package com.iteso.pae.messenger.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iteso.pae.messenger.beans.UserBean;
import com.iteso.pae.messenger.db.DataBase;

public class UsersDao {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	
	public boolean delete(String id){
		PreparedStatement ps;
		try {
			String sql = "DELETE FROM pae.users WHERE user_id = ?";
			// Setup the connection with the DB
			connect = DataBase.getConnection();

			// Statements allow to issue SQL queries to the database
			ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(3, Integer.parseInt(id));
			// Result set get the result of the SQL query
			return ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
	public UserBean update(UserBean bean){
		PreparedStatement ps;
		try {
			String sql = "UPDATE pae.users SET username = ?,password = ? WHERE user_id = ?";
			// Setup the connection with the DB
			connect = DataBase.getConnection();

			// Statements allow to issue SQL queries to the database
			ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getPassword());
			ps.setInt(3, bean.getId());
			// Result set get the result of the SQL query
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
			}
			//bean.setId(generatedKey);
			return bean;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bean;
	}
	public List<UserBean> getUsers() {
		List<UserBean> list = new ArrayList<UserBean>();
		try {
			// Setup the connection with the DB
			connect = DataBase.getConnection();

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from pae.users");
			while (resultSet.next()) {
				UserBean b = new UserBean();
				b.setId(resultSet.getInt(1));
				b.setUsername(resultSet.getString(2));
				b.setPassword(resultSet.getString(3));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public UserBean save(UserBean bean){
		PreparedStatement ps;
		try {
			String sql = "insert into pae.users (username,password) values (?,?)";
			// Setup the connection with the DB
			connect = DataBase.getConnection();

			// Statements allow to issue SQL queries to the database
			ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getPassword());
			// Result set get the result of the SQL query
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
			}
			bean.setId(generatedKey);
			return bean;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
