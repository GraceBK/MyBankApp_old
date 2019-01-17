/*
 * Created by Grace BK on 1/13/19 11:20 AM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp.models;

public class Comptes {

    public String uid;
    public String id;
    public String name;
    public String email;
    public float money;
    public float beforeMoney;

    public Comptes() {}

    public Comptes(String uid, String id, String name, String email, float money, float beforeMoney) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.email = email;
        this.money = money;
        this.beforeMoney = beforeMoney;
    }

    public void add(float value) {
        this.money += value;
    }

    @Override
    public String toString() {
        return "compte : \n{" +
                "uid : " + uid + "\n" +
                "id : " + id + "\n" +
                "name : " + name + "\n" +
                "email : " + email + "\n" +
                "money : " + money + "\n" +
                "money before : " + beforeMoney + "\n" +
                "}";
    }
}
