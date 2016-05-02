package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.Class;
import models.School;
import play.db.Database;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class HomeController extends Controller {

    @Inject
    Database db;

    public Result listAllSchools() {
        ArrayNode jsonArray = Json.newArray();
        School.find.all().forEach(x -> jsonArray.add(Json.toJson(x)));
        return ok(jsonArray);
    }

    public Result viewClassDetail(int id) {
        return ok(Json.toJson(Class.find.byId(id)));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createClass() {
        Class obj = Json.fromJson(request().body().asJson(), Class.class);
        obj.id = (Integer) Ebean.nextId(Class.class);
        Ebean.save(obj);
        return ok(Json.toJson(obj));
    }

}
