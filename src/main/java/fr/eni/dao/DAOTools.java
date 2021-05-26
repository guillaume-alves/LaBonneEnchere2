package fr.eni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOTools {

    // Default constructor because final class
    private DAOTools() {
    }

   // Closing resultSet
    public static void silentClosing(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet closing failed : " + e.getMessage() );
            }
        }
    }

    // Closing Statement
    public static void silentClosing(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Statement closing failed : " + e.getMessage() );
            }
        }
    }

    // Closing connection
    public static void silentClosing(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.out.println("Connection closing failed : " + e.getMessage());
            }
        }
    }

    // Closing statement and connection
    public static void silentClosing(Statement statement, Connection connexion) {
    	silentClosing(statement);
    	silentClosing(connexion);
    }

    // Closing resulSet, statement and connection
    public static void silentClosing(ResultSet resultSet, Statement statement, Connection connexion) {
    	silentClosing(resultSet);
    	silentClosing(statement);
    	silentClosing(connexion);
    }

    // PreparedStatement request initialization
    public static PreparedStatement initPreparedStatement(Connection conn, String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i+1, objects[i]);
        }
        return preparedStatement;
    }
}
