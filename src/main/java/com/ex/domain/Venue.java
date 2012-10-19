package com.ex.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Venue {

    public static final String NAME_INDEX = "name-index";

    @GraphId
	private Long graphId;

    @Indexed(indexName = NAME_INDEX)
    String name;
	
	@Indexed(indexType = IndexType.POINT, indexName = "VenueLocation")
	String wkt;

	public void setLocation(double d, double e) {
		this.wkt = String.format("POINT ( %.2f %.2f )", d, e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue v = (Venue) o;
        if (graphId == null) return super.equals(o);
        return graphId.equals(v.graphId);
    }

}