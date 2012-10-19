package com.ex.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ex.domain.Restaurant;
import com.ex.neorepository.RestaurantNeoRepository;


public class RestaurantServiceImpl implements RestaurantService {

	@Autowired RestaurantNeoRepository restaurantNeoRepository;
	
	public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}
