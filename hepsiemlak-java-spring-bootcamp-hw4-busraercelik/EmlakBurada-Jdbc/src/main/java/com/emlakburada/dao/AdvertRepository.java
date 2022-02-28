package com.emlakburada.dao;

import com.emlakburada.entity.Advert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvertRepository extends JdbcConnectionRepository{

    private static final String INSERT_ADVERT = "INSERT INTO ADVERT (id, adNo, description, duration) VALUES (?,?,?,?);";
    private static final String SELECT_ALL_ADVERTS = "SELECT * FROM ADVERT";
    private static final String FIND_ADVERT= "SELECT * FROM ADVERT WHERE id = ?";

    public void saveAdvert(Advert advert) {
        Connection connection = connect();

        if (connection != null) {
            PreparedStatement prepareStatement = null;

            try {
                prepareStatement = connection.prepareStatement(INSERT_ADVERT);
                prepareStatement.setLong(1, advert.getId());
                prepareStatement.setInt(2, advert.getAdNo());
                prepareStatement.setString(3, advert.getDescription());
                prepareStatement.setInt(4, advert.getDuration());

                int executeUpdate = prepareStatement.executeUpdate();
                System.out.println("result: " + executeUpdate);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    prepareStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Uoh Connection failed!");
        }
    }

    public List<Advert> findAll() {
        List<Advert> userList = new ArrayList<>();
        Connection connection = connect();
        PreparedStatement prepareStatement = null;

        try {
            prepareStatement = connection.prepareStatement(SELECT_ALL_ADVERTS);
            ResultSet result = prepareStatement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                int adNo = result.getInt("adNo");
                String description = result.getString("description");
                int duration = result.getInt("duration");

                userList.add(prepareAdvert(id, adNo, description, duration));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                prepareStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            return userList;
        }

    private Advert prepareAdvert(int id, int adNo, String description, int duration) {
        Advert advert = new Advert();
        advert.setId(id);
        advert.setAdNo(adNo);
        advert.setDescription(description);
        advert.setDuration(duration);

        return advert;
    }

    public Advert findOne(int id) {
        Advert advert = null;
        Connection connection = connect();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(FIND_ADVERT);
            prepareStatement.setInt(1, id);

            ResultSet result = prepareStatement.executeQuery();
            if (result.next()) {
                int advertId = result.getInt("id");
                int adNo = result.getInt("adNo");
                String description = result.getString("description");
                int duration = result.getInt("duration");

                advert = prepareAdvert(advertId, adNo, description, duration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return advert;
    }
}
