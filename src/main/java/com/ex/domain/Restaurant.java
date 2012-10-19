package com.ex.domain;

import javax.validation.constraints.Pattern;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
@NodeEntity(partial = true)
public class Restaurant {

	@Indexed
	private String name;
    
    @Pattern(regexp = "^POINT \\( \\-?\\d+\\.\\d+?\\s*\\-?\\d+\\.\\d+ \\)$",
            message="Only 'POINT ( <latitude> <longitude> )' are supported.")
    @Indexed(indexType=IndexType.POINT, indexName="latLon")
    private String latLon; // POINT ( 51.746078 10.678711 ) 

}
