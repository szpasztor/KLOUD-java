package models;


public class Class {

    private int id;

    private String name;
    private int schoolId;
    private String timeStart; // YYY MM DD
    private String timeEnd;
    private int ownerId;

    public Class(int id, String name, int schoolId, String timeStart, String timeEnd, int ownerId) {
        this.id = id;
        this.name = name;
        this.schoolId = schoolId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.ownerId = ownerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSchoolId() {
        return schoolId;
    }
}
