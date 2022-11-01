package io.hashimati.domains;

import io.micronaut.data.annotation.*;
import lombok.*;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import static io.micronaut.data.model.naming.NamingStrategies.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@MappedEntity(value = "decisions", namingStrategy = Raw.class)
@Schema(name="Decision", description="Decision Description")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Decision{
    @Id
   // @GeneratedValue(GeneratedValue.Type.AUTO)
    private String id;
    private String name;
	private String context;
    private String description;
    private String result;
    private Status status;
//    private ArrayList<String> rules;

    private String rule;
    private String decision;
   // private ArrayList<?> rules = new ArrayList<>();
    @DateCreated private Date dateCreated;
    @DateUpdated private Date dateUpdated;
}

