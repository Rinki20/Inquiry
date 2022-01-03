
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finlogic.spring.inquiry.buissness;

import com.finlogic.spring.inquiry.constant.FloorType;
import com.finlogic.spring.inquiry.constant.RoofType;


/**
 *
 * @author njuser
 */
public class InquiryEntityBean {
    String inquiryid;
    String firstname;
    String lastname;
    String dob;
    String address;
    String state;
    String city;
    String pincode;
    int carpetarea;
    RoofType roof;
    FloorType floor;
    int safety;
    int year;
    String offerid;
    int basecoverage;
    int basepremium;
    int maxcoverage;
    int mincoverage;

    public int getBasecoverage() {
        return basecoverage;
    }

    public void setBasecoverage(int basecoverage) {
        this.basecoverage = basecoverage;
    }

    public int getBasepremium() {
        return basepremium;
    }

    public void setBasepremium(int basepremium) {
        this.basepremium = basepremium;
    }

    public int getMaxcoverage() {
        return maxcoverage;
    }

    public void setMaxcoverage(int maxcoverage) {
        this.maxcoverage = maxcoverage;
    }

    public int getMincoverage() {
        return mincoverage;
    }

    public void setMincoverage(int mincoverage) {
        this.mincoverage = mincoverage;
    }

    public String getOfferid() {
        return offerid;
    }

    public void setOfferid(String offerid) {
        this.offerid = null ;
    }
    

    public String getInquiryid() {
        return inquiryid;
    }

    public void setInquiryid(String Inquiryid) {
        this.inquiryid = Inquiryid;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public int getCarpetarea() {
        return carpetarea;
    }

    public void setCarpetarea(int carpetarea) {
        this.carpetarea = carpetarea;
    }

    public RoofType getRoof() {
        return roof;
    }

    public void setRoof(RoofType roof) {
        this.roof = roof;
    }

    public FloorType getFloor() {
        return floor;
    }

    public void setFloor(FloorType floor) {
        this.floor = floor;
    }

    public int getSafety() {
        return safety;
    }

    public void setSafety(int safety) {
        this.safety = safety;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    
}
