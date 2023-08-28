package com.eacuamba.dev.domain.repository;

import com.eacuamba.dev.config.ApplicationConfig;
import com.eacuamba.dev.domain.model.Access;
import com.eacuamba.dev.domain.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class UserRepository {

    public static List<User> findAll() {
       List<User> userList = new ArrayList<>();
        String getUserByIdQuery = "select u.id, u.name, u.username, u.password from user u;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = ApplicationConfig.getConnection().prepareStatement(getUserByIdQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                user.setId(id);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);

                List<Access> accessList = findAllAccessByUserId(user.getId());
                for (Access access : accessList) {
                    access.setUser(user);
                }
                user.setAccessList(accessList);

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (nonNull(resultSet))
                    resultSet.close();

                if (nonNull(preparedStatement))
                    preparedStatement.close();
            } catch (SQLException sqlException) {
                System.out.println("SLQ Error: " + sqlException);
            }
        }



        return userList;
    }
    public static Optional<User> getUserByUsername(String username) {
        if (isNull(username) || "".equals(username)) {
            return Optional.empty();
        }

        User user = null;

        String getUserByIdQuery = "select u.id, u.name, u.username, u.password from user u where u.username = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = ApplicationConfig.getConnection().prepareStatement(getUserByIdQuery);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                username = resultSet.getString("username");
                String password = resultSet.getString("password");

                user = new User();
                user.setId(id);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (nonNull(resultSet))
                    resultSet.close();

                if (nonNull(preparedStatement))
                    preparedStatement.close();
            } catch (SQLException sqlException) {
                System.out.println("SLQ Error: " + sqlException);
            }
        }

        if (nonNull(user)) {
            List<Access> accessList = findAllAccessByUserId(user.getId());
            for (Access access : accessList) {
                access.setUser(user);
            }
            user.setAccessList(accessList);
        }

        return Optional.ofNullable(user);
    }


    private static List<Access> findAllAccessByUserId(Integer userId) {
        List<Access> accessList = new ArrayList<>();
        String getUserByIdQuery = "select a.id, a.resource, a.date_time  from access a where a.user_id = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = ApplicationConfig.getConnection().prepareStatement(getUserByIdQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Access access = new Access();
                access.setId(resultSet.getInt("id"));
                access.setResource(resultSet.getString("resource"));
                access.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());

                accessList.add(access);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (nonNull(resultSet))
                    resultSet.close();

                if (nonNull(preparedStatement))
                    preparedStatement.close();
            } catch (SQLException sqlException) {
                System.out.println("SLQ Error: " + sqlException);
            }
        }

        return accessList;
    }

    public static void saveAccess(Access access) {
        String getUserByIdQuery = "insert into access (resource, date_time, user_id) values (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = ApplicationConfig.getConnection().prepareStatement(getUserByIdQuery);
            preparedStatement.setString(1, access.getResource());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(access.getDateTime()));
            preparedStatement.setInt(3, access.getUser().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {

                if (nonNull(preparedStatement))
                    preparedStatement.close();
            } catch (SQLException sqlException) {
                System.out.println("SLQ Error: " + sqlException);
            }
        }
    }
}
