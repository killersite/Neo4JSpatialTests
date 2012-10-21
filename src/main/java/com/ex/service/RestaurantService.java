package com.ex.service;

import org.springframework.roo.addon.layers.service.RooService;

import com.ex.domain.Restaurant;

@RooService(domainTypes = { com.ex.domain.Restaurant.class })
public interface RestaurantService {

	Restaurant findByName(String string);

	Iterable<Restaurant> findWithinDistance(int i, int j, int k);
}
