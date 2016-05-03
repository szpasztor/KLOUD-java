package models;


import com.avaje.ebean.Model.Finder;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "school")
public class School {

    @Id
    public Integer id;
    @Constraints.Required
    public String name;
    public String country;
    public String city;
    public String address;

    public static Finder<Integer, School> find = new Finder<>(School.class);




}
