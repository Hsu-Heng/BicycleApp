package com.example.hsu.youth_knight.schule;

/**
 * Created by hsu on 2017/1/18.
 */
public class information {
    String name;
    String date;
    String dateend;
    String time;

    public String getDate() {
        return date;
    }

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String id;
    int day;
    long sqliteId;
    String jsoncontent;

    public long getSqliteId() {
        return sqliteId;
    }

    public String getJsoncontent() {
        return jsoncontent;
    }

    public void setJsoncontent(String jsoncontent) {
        this.jsoncontent = jsoncontent;
    }

    public void setSqliteId(long sqliteId) {
        this.sqliteId = sqliteId;
    }

    public int getDay() {
        return day;

    }

    public void setDay(int day) {
        this.day = day;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }
}