// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ex.service;

import com.ex.domain.Restaurant;
import com.ex.service.RestaurantService;
import java.util.List;

privileged aspect RestaurantService_Roo_Service {
    
    public abstract long RestaurantService.countAllRestaurants();    
    public abstract void RestaurantService.deleteRestaurant(Restaurant restaurant);    
    public abstract Restaurant RestaurantService.findRestaurant(Long id);    
    public abstract List<Restaurant> RestaurantService.findAllRestaurants();    
    public abstract List<Restaurant> RestaurantService.findRestaurantEntries(int firstResult, int maxResults);    
    public abstract void RestaurantService.saveRestaurant(Restaurant restaurant);    
    public abstract Restaurant RestaurantService.updateRestaurant(Restaurant restaurant);    
}