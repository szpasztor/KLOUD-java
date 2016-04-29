package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.School;
import play.db.Database;
import play.libs.Json;
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
    public Result index()  {
        return TODO;
    }

    public Result listAllSchools() {
        ArrayNode list = Json.newArray();
        try (Connection connection = db.getConnection()) {
            final ResultSet resultSet = connection.prepareStatement("SELECT * FROM \"schools\"").executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String address = resultSet.getString("address");

                School school = new School(id,name,country,city,address);
                JsonNode schoolJson = Json.toJson(school);

                list.add(schoolJson);
            }
            return ok(list);

        } catch (SQLException e) {
            e.printStackTrace();
            return internalServerError("SQL exception");
        }
    }

}
