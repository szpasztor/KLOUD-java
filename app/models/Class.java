package models;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class Class {

    @Id
    public Integer id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    @ManyToOne
    @JsonProperty("school_id")
    public Integer schoolId;

    @Constraints.Required
    @Formats.DateTime(pattern="yyyy-MM-dd")
    @JsonProperty("time_start")
    public String timeStart;

    @Constraints.Required
    @Formats.DateTime(pattern="yyyy-MM-dd")
    @JsonProperty("time_end")
    public String timeEnd;

    @Constraints.Required
    @ManyToOne
    @JsonProperty("owner_id")
    public Integer ownerId;

    public static Model.Finder<Integer, Class> find = new Model.Finder<>(Class.class);

}
