package com.iteso.pae.messenger.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iteso.pae.messenger.beans.MessageBean;
import com.iteso.pae.messenger.db.DataBase;

public class MessagesDao {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	
	public boolean deleteMessage(String id){
		PreparedStatement ps;
		try {
			String sql = "DELETE FROM pae.messages WHERE msg_id = ?";
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
	
	public MessageBean updateMessage(MessageBean bean){
		PreparedStatement ps;
		try {
			String sql = "UPDATE pae.messages SET msg = ?,updateDate = ? WHERE msg_id = ?";
			// Setup the connection with the DB
			connect = DataBase.getConnection();

			// Statements allow to issue SQL queries to the database
			ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String updateDate =  dateFormat.format(date); //2014/08/06 15:59:48
			
			ps.setString(1, bean.getMessage());
			ps.setString(2, updateDate);
			ps.setInt(3, Integer.parseInt(bean.getId()));
			// Result set get the result of the SQL query
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
			}
			bean.setId(""+generatedKey);
			bean.setUpdateDate(updateDate);
			return bean;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bean;
	}
	public List<MessageBean> getMessages() {
		List<MessageBean> list = new ArrayList<MessageBean>();
		try {
			// Setup the connection with the DB
			connect = DataBase.getConnection();

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from pae.messages");
			while (resultSet.next()) {
				MessageBean b = new MessageBean();
				b.setId(resultSet.getInt(1) + "");
				b.setMessage(resultSet.getString(2));
				list.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public MessageBean saveBean(MessageBean bean){
		PreparedStatement ps;
		try {
			String sql = "insert into pae.messages (msg,createDate) values (?,?)";
			// Setup the connection with the DB
			connect = DataBase.getConnection();

			// Statements allow to issue SQL queries to the database
			ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getMessage());
			ps.setString(2, bean.getCreateDate());
			// Result set get the result of the SQL query
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
			}
			bean.setId(""+generatedKey);
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
