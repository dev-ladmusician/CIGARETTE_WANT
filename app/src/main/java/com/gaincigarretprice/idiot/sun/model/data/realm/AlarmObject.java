package com.gaincigarretprice.idiot.sun.model.data.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jihoon on 2016. 4. 9..
 */
public class AlarmObject extends RealmObject {

    @PrimaryKey
    private int _alarmid;
    private int hour;
    private int min;
    private String ringtone_title;
    private String ringtone_url;
    private boolean sun;
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thur;
    private boolean fri;
    private boolean sat;
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

    public String getRingtone_title() {
        return ringtone_title;
    }

    public void setRingtone_title(String ringtone_title) {
        this.ringtone_title = ringtone_title;
    }

    public String getRingtone_url() {
        return ringtone_url;
    }

    public void setRingtone_url(String ringtone_url) {
        this.ringtone_url = ringtone_url;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThur() {
        return thur;
    }

    public void setThur(boolean thur) {
        this.thur = thur;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
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
