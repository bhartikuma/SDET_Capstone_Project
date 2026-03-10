package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    public static Object[][] getDBData() throws Exception {

        Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/flightdb",
        "root",
        "Bharti@1234");

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
        "SELECT name,city,card_number FROM bookings");

        List<Object[]> dataList = new ArrayList<>();

        while(rs.next()) {

            String name = rs.getString("name");
            String city = rs.getString("city");
            String card = rs.getString("card_number");

            System.out.println("DATA SOURCE : DATABASE");
            System.out.println("Name: "+name+" City: "+city+" Card: "+card);

            dataList.add(new Object[]{name,city,card});
        }

        con.close();

        return dataList.toArray(new Object[0][0]);
    }
}