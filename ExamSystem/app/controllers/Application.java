package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import play.db.DB;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void testConnect() {
    	Connection con = DB.getConnection();
    	try {
			ResultSet resultSet = con.createStatement().executeQuery("select t_name from t_user");
			if (resultSet.next() == true) {
				System.out.println(resultSet.getString(1));
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void addUser() {
    	User user = new User("changsha", "123");
    	user.save();
    }
}