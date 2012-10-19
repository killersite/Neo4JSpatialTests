package com.ex.graph;

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
import com.ex.neorepository.VenueNeoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration //("Neo4jGraphRecommendationTest-context.xml")
public class SpatialTestVenu extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired Neo4jTemplate template;
	@Autowired VenueNeoRepository venueNeoRepository;
    @Autowired PlatformTransactionManager transactionManager;

    Venue venue;

	@Before
	public void setUp() throws Exception {
        new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus status) {
        		venue = new Venue();
        		venue.setName("test");
        		venue.setLocation(16.5, 56.5);
        		venueNeoRepository.save(venue);
            }
        });
	}

	@BeforeTransaction
//	@AfterTransaction
	public void cleanDb() {
	    Neo4jHelper.cleanDb(template);
	}

	@Test
	public void test() {
		Venue foundvenu = venueNeoRepository.findByName("test");
		System.out.println("venu: " + foundvenu.getName());

    	Iterable<Venue> venus = venueNeoRepository.findWithinDistance("VenueLocation", 16,56,70);
        assertThat(asCollection(venus), hasItems(venue));

        double lat = 16.0;
		double lon = 56.0;
		double distanceKm = 10;
//		Iterable<Venue> results = venueNeoRepository.findWithinBoundingBox("VenueLocation", 55, 15, 57, 17);
		Iterable<Venue> results = venueNeoRepository.findWithinDistance("VenueLocation", lat, lon, distanceKm);
		System.out.println("results: " + asCollection(results));
		
		
//		assertThat(asCollection(results), hasItems(venu));


//		Restaurant restaurant = restaurantService.findRestaurant(Long.valueOf(1));
//
//		List<Restaurant> restaurants = restaurantService.findRestaurantNearby(lat, lon, distance);
//		assertThat(asCollection(restaurants), hasItems(restaurant));
		
//		fail("Not yet implemented");
	}

}
