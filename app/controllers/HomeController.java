package controllers;

import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    Database db;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() throws SQLException {

        Connection connection = db.getConnection();

        connection.prepareStatement("INSERT INTO \"schools\" (name, country) VALUES (\'FAZEKAS\', \'hun\') ").execute();


        String x = "something should be here\n";

        final ResultSet rs = connection.prepareStatement("SELECT * FROM \"schools\"").executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String country = rs.getString("country");
            int id = rs.getInt("id");
            x += id + " " + name + " " + country + "\n";
            System.out.println(x);
        }


        connection.close();


        return ok(x);
    }

}
