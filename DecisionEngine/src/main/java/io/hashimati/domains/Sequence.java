package io.hashimati.domains;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

import static io.micronaut.data.model.naming.NamingStrategies.Raw;


@MappedEntity(value = "sequences", namingStrategy = Raw.class)
@Schema(name="Sequence", description="Sequence Description")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Sequence{
    @Id 
  //s  @GeneratedValue(GeneratedValue.Type.AUTO)
    private String id;

    private String name;
	private int counter;
    @DateCreated private Date dateCreated;
    @DateUpdated private Date dateUpdated;


}

