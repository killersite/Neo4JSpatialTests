package com.ex.neorepository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.SpatialRepository;
import com.ex.domain.Venue;

public interface VenueNeoRepository extends GraphRepository<Venue>, SpatialRepository<Venue> {

	Venue findByName(String name);

}
