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
    public String money;

    public Comptes() {}

    public Comptes(String uid, String id, String name, String email, String money) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.email = email;
        this.money = money;
    }
}
