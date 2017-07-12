package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import play.db.DB;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }
}