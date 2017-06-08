package com.example.chethan.fdb.Data;

/**
 * Created by Chethan on 5/24/2017.
 */

public class Person {


    int id;
    String name;
    String done;






    public Person(int id, String name){
        this.id = id;
        this.name = name;
        this.done =done;

    }

    public Person() {

    }



    public int getID(){
        return this.id;
    }


    public void setID(int id){
        this.id = id;
    }


    public String getName(){
        return this.name;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}