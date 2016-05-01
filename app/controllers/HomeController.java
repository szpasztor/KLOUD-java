package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    public Result listAllSchools() {
        ArrayNode list = Json.newArray();
        try (Connection connection = db.getConnection()) {
            final ResultSet resultSet = connection.prepareStatement("SELECT * FROM school").executeQuery();
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

    public Result viewClassDetail(int id) {

        try (Connection connection = db.getConnection()) {
            final ResultSet resultSet = connection.prepareStatement("SELECT * FROM class where id="+id).executeQuery();
            resultSet.next();

            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            String name = resultSet.getString("name");
            int schoolId = resultSet.getInt("school_id");
            String timeStart = resultSet.getDate("time_start").toString();
            String timeEnd = resultSet.getDate("time_end").toString();
            int ownerId = resultSet.getInt("owner_id");

            models.Class classObj = new models.Class(id, name, schoolId, timeStart, timeEnd, ownerId);

            String json = gson.toJson(classObj);

            return ok(json);

        } catch (SQLException e) {
            e.printStackTrace();
            return internalServerError();
        }
    }

    public Result createClass() {
        String body = request().body().asText();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        models.Class obj = gson.fromJson(body, models.Class.class);

        try (Connection connection = db.getConnection()) {
            String query = String.format
                    ("INSERT INTO class (id, name, time_start, time_end, school_id, owner_id)" +
                            "VALUES (DEFAULT, '%s', '%s', '%s', %d, %d)" +
                            "RETURNING id;",
                            obj.getName(), obj.getTimeStart(), obj.getTimeEnd(), obj.getSchoolId(), obj.getOwnerId());
            final ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                System.out.println("generated "  + id);
            }

            obj.setId(id);
            String created = gson.toJson(obj);

            return created(created);
        } catch (SQLException e) {
            e.printStackTrace();
            return internalServerError();
        }
    }

}
