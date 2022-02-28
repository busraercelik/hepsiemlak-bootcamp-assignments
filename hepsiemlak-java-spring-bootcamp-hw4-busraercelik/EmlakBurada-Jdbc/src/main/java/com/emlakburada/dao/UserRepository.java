package com.emlakburada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emlakburada.entity.User;

public class UserRepository extends JdbcConnectionRepository {

	private static final String INSERT_USER = "INSERT INTO USER (ID, NAME, EMAIL, BIO) VALUES (?,?,?,?);";
	private static final String SELECT_ALL_USER = "SELECT * FROM USER";
	private static final String FIND_USER = "SELECT * FROM USER WHERE id = ?";

	public void save(User user) {

		Connection connection = connect();

		if (connection != null) {

			PreparedStatement prepareStatement = null;
			try {

				prepareStatement = connection.prepareStatement(INSERT_USER);
				prepareStatement.setInt(1, user.id);
				prepareStatement.setString(2, user.name);
				prepareStatement.setString(3, user.email);
				prepareStatement.setString(4, user.bio);

				int executeUpdate = prepareStatement.executeUpdate();

				System.out.println("result: " + executeUpdate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
					prepareStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Connection oluşturululamadı!");
		}

	}

	public List<User> findAll() {

		List<User> userList = new ArrayList<User>();

		Connection connection = connect();

		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_USER);

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {

				int id = result.getInt("id");
				String name = result.getString("name");
				String email = result.getString("email");
				String bio = result.getString("bio");

				userList.add(prepareUser(id, name, email, bio));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;

	}

	private User prepareUser(int id, String name, String email, String bio) {
		User user = new User();

		user.id = id;
		user.name = name;
		user.email = email;
		user.bio = bio;

		return user;
	}

	public User findOne(int id) {

		User user = null;

		Connection connection = connect();

		try {
			PreparedStatement prepareStatement = connection.prepareStatement(FIND_USER);
			prepareStatement.setInt(1, id);

			ResultSet result = prepareStatement.executeQuery();
			if (result.next()) {
				int userId = result.getInt("id");
				String name = result.getString("name");
				String email = result.getString("email");
				String bio = result.getString("bio");

				user = prepareUser(userId, name, email, bio);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}

}
