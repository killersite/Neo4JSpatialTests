// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ex.domain;

import com.ex.domain.Restaurant;

privileged aspect Restaurant_Roo_JavaBean {
    
    public String Restaurant.getName() {
        return this.name;
    }
    
    public void Restaurant.setName(String name) {
        this.name = name;
    }
    
    public String Restaurant.getLatLon() {
        return this.latLon;
    }
    
    public void Restaurant.setLatLon(String latLon) {
        this.latLon = latLon;
    }
    
}
