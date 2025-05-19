package com.kbsystems.zadanie.matusfila.krtkoland.models;

import java.util.List;

public class BestTimeAndPathResult {

    public static final String pathError = "pathError";
    public static final String pathNotFountError = "pathNotFoundError";

    private float pathTime;
    private List<String> path;
    private String msg;

    public BestTimeAndPathResult() {
    }

    public BestTimeAndPathResult(float pathTime, List<String> path) {
        this.pathTime = pathTime;
        this.path = path;
    }

    public BestTimeAndPathResult(String msg) {
        this.msg = msg;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public float getPathTime() {
        return pathTime;
    }

    public void setPathTime(float pathTime) {
        this.pathTime = pathTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
