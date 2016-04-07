package com.gaincigarretprice.idiot.sun.model.data.dto;

/**
 * Created by ladmusician on 4/5/16.
 */
public class AlarmDTO {
    private int _alarmid;
    private int hour;
    private int min;
    private String ringtone;
    private int sun;
    private int mon;
    private int tue;
    private int wed;
    private int thur;
    private int fri;
    private int sat;
    private int isdeprecated;
    private int for_userid;

    public int get_alarmid() {
        return _alarmid;
    }
    public void set_alarmid(int _alarmid) {
        this._alarmid = _alarmid;
    }
    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public String getRingtone() {
        return ringtone;
    }
    public void setRingtone(String ringtone) {
        this.ringtone = ringtone;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public int getMon() {
        return mon;
    }

    public void setMon(int mon) {
        this.mon = mon;
    }

    public int getTue() {
        return tue;
    }

    public void setTue(int tue) {
        this.tue = tue;
    }

    public int getWed() {
        return wed;
    }

    public void setWed(int wed) {
        this.wed = wed;
    }

    public int getThur() {
        return thur;
    }

    public void setThur(int thur) {
        this.thur = thur;
    }

    public int getFri() {
        return fri;
    }

    public void setFri(int fri) {
        this.fri = fri;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public int getIsdeprecated() {
        return isdeprecated;
    }

    public void setIsdeprecated(int isdeprecated) {
        this.isdeprecated = isdeprecated;
    }

    public int getFor_userid() {
        return for_userid;
    }
    public void setFor_userid(int for_userid) {
        this.for_userid = for_userid;
    }
}
