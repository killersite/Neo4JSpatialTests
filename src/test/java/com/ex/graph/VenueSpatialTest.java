package com.ex.graph;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ex.domain.Venue;
import com.ex.neorepository.VenueNeoRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.IsCollectionContaining.hasItems;
import static org.neo4j.helpers.collection.IteratorUtil.asCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class VenueSpatialTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired Neo4jTemplate neo4jTemplate;
	@Autowired VenueNeoRepository venueNeoRepository;
    @Autowired PlatformTransactionManager transactionManager;
    
    Venue venue;

    @BeforeTransaction
    public void cleanDb() {
        Neo4jHelper.cleanDb(neo4jTemplate);
    }
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

    @Test
    public void testFindPeopleWithinDistance() {
		Venue foundvenu = venueNeoRepository.findByName("test");
		assertEquals("test", foundvenu.getName());

    	Iterable<Venue> venus = venueNeoRepository.findWithinDistance("VenueLocation", 16,56,70);
        assertThat(asCollection(venus), hasItems(venue));
    }
}
