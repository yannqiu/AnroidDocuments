package com.example.peiyu_wang.databinding.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.peiyu_wang.databinding.BR;

/**
 * Created by peiyu_wang on 2017/7/27.
 */

public class StudentObserve extends BaseObservable {

    private String name;

    private String address;

    private String phone;

    public StudentObserve() {
    }

    public StudentObserve(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    @Bindable
    public String getName() {
        return name;
    }
    @Bindable
    public String getAddress() {
        return address;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }


    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }


    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }


    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }
}
