package com.qianfeng.gamehelper.modules.game.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
@Table(name="Gameinfo")
public class Gameinfo {

    @Column(name="_id",isId = true)
    private int _id;
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="icon")
    private String icon;
    @Column(name="score")
    private int score;
    @Column(name="size")
    private String size;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Column(name="number")

    private String number;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
