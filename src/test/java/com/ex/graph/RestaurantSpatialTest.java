package com.ex.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.internal.matchers.IsCollectionContaining.hasItems;
import static org.neo4j.helpers.collection.IteratorUtil.asCollection;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ex.domain.Restaurant;
import com.ex.domain.Venue;
import com.ex.neorepository.RestaurantNeoRepository;
import com.ex.neorepository.VenueNeoRepository;
import com.ex.service.RestaurantService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RestaurantSpatialTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired Neo4jTemplate template;
	@Autowired RestaurantService restaurantService;

    Restaurant restaurant;

	@Before
	public void setUp() throws Exception {
    	restaurant = new Restaurant();
    	restaurant.setName("test");
    	restaurant.setWkt("POINT ( 16.5 56.5 )");
    	restaurantService.saveRestaurant(restaurant);
	}

	@Test
	public void testFindWithinDistance() {
    	Iterable<Restaurant> venus = restaurantService.findWithinDistance(16,56,70);
        assertThat(asCollection(venus), hasItems(restaurant));
	}

	@Test
	public void testFindByName() {
		Restaurant foundvenu = restaurantService.findByName("test");
		assertEquals("test", foundvenu.getName());
	}

}
