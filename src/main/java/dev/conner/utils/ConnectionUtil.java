package dev.conner.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){
        try{
            
            Connection conn = DriverManager.getConnection("jdbc:postgresql://conner-sql-server.postgres.database.azure.com:5432/postgres?user=dillonconner&password=Rhodes23$&ssl=false");
            //Connection conn = DriverManager.getConnection(System.getenv("AZURE_SQL_DB"));
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}
