package com.kloud.models;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Lesson {

    @Id
    public Integer id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    @ManyToOne
    @JsonProperty("class_id")
    public Integer classId;

    @ManyToOne
    @JsonProperty("teacher_id")
    public Integer teacherId;

    public static Model.Finder<Integer, Lesson> find = new Model.Finder<>(Lesson.class);

}
