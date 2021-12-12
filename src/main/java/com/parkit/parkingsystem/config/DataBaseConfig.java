package com.parkit.parkingsystem.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseConfig");

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        logger.info("Create DB connection");

        Properties properties = new Properties();
        try {
            FileInputStream in = new FileInputStream("src/main/resources/db.properties");
            properties.load(in);
            in.close();
        } catch (Exception e) {
            logger.error("Can't read the file",e);
        }
        String driver = properties.getProperty("jdbc.driver");
        if (driver != null ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }

        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String passwd = properties.getProperty("jdbc.passwd");

        return DriverManager.getConnection(url, user, passwd);
    }

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection",e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.error("Error while closing prepared statement",e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.error("Error while closing result set",e);
            }
        }
    }
}
