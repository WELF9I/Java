package org.example.tp4javafx.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connection cn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String login = "root";
            String password = "";
            String url = "jdbc:mysql://root:@localhost/tp4javafx";
            cn = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
        }
    }

    public static Connection getCn() {
        return cn;
    }
}
