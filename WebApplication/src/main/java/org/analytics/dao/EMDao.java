package org.analytics.dao;

import org.analytics.model.ErosionMachin;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EMDao {

    private  static  final  String URL = "jdbc:postgresql://localhost:5433/em_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12345678";
    private static Connection connection;
    static{
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public List<ErosionMachin> index(){
        List<ErosionMachin> emlist = null;

        try{
            emlist = new ArrayList<>();
            Statement statement = connection.createStatement();
            String SQL = ("SELECT * FROM em");
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                ErosionMachin em = new ErosionMachin();
                em.setId(resultSet.getInt(1));
                em.setModel(resultSet.getString(2));
                em.setType(resultSet.getString(3));
                em.setSerialNumber(resultSet.getString(4));
                em.setMark(resultSet.getString(5));
                emlist.add(em);

            }

        }catch(SQLException throwables){throwables.printStackTrace();}

        return emlist;
    }
}
