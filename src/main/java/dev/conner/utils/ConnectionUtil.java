package dev.conner.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection createConnection(){
        try{
            Connection conn;
            if(isJUnitTest()){
                conn = DriverManager.getConnection(System.getenv("AZURE_SQL_DB_TEST"));
            }else{
                conn = DriverManager.getConnection(System.getenv("AZURE_SQL_DB"));
            }

            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isJUnitTest() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }
}
