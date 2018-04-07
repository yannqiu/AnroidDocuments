package com.example.peiyu_wang.databinding.entity;

/**
 * Created by peiyu_wang on 2017/7/27.
 */

public class Student {

    private String name;

    private String address;

    private String company;

    public Student() {
    }

    public Student(String name, String address, String company) {
        this.name = name;
        this.address = address;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
