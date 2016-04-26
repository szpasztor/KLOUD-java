package controllers;

import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

        connection.prepareStatement("INSERT INTO \"schools\" (name) VALUES (\'FAZEKAS\') ").execute();


        String x = "something should be here\n";

        final ResultSet resultSet = connection.prepareStatement("SELECT * FROM \"schools\"").executeQuery();
        //final ResultSet resultSet = connection.prepareStatement("SELECT * FROM pg_catalog.pg_tables").executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int id = resultSet.getInt("id");
            x += id + " " + name + "\n";
            System.out.println(x);
        }

        /*ResultSetMetaData metadata = resultSet.getMetaData();
        int columnCount = metadata.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(metadata.getColumnName(i) + ", ");
        }

        System.out.println();
        ResultSet rs = resultSet;
        while (rs.next()) {
            String row = "";
            for (int i = 1; i <= columnCount; i++) {
                row += rs.getString(i) + ", ";
            }
            System.out.println();


        }
        */

        connection.close();


        return ok(x);
    }

}
