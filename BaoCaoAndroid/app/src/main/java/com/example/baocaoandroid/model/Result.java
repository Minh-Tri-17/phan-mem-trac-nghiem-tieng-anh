package com.example.baocaoandroid.model;

public class Result {

    private int id;
    private Integer score;
    private String time;

    public Result(){}

    public Result(Integer score, String time) {
        this.score = score;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return " Điểm số : "+ getScore()+"                 " + " Thời gian :  "+ getTime();
    }
}
