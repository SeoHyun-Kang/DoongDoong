package com.example.doongdoong;

public class User {

    public String name;
    public String gender;
    public String birth;
    public String doong_num;

    public User(){

    }

    public User(String name, String gender, String birth, String doong_num){
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.doong_num = doong_num;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }
    public String getGender() {
        return gender;
    }
    public String getDoong_num() {
        return doong_num;
    }
}
