package com.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ex.domain.Restaurant;
import com.ex.neorepository.RestaurantNeoRepository;


public class RestaurantServiceImpl implements RestaurantService {

	@Autowired RestaurantNeoRepository restaurantNeoRepository;
	
	public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        restaurant.persist();
    }

	@Override
	public Restaurant findByName(String string) {
		return restaurantNeoRepository.findByName(string);
	}


	@Override
	public Iterable<Restaurant> findWithinDistance(int i, int j, int k) {
		return restaurantNeoRepository.findWithinDistance("latLon", i, j, k);
	}


}
