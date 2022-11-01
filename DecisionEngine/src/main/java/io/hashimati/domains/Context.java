package io.hashimati.domains;

import io.micrometer.core.lang.NonNull;
import io.micronaut.data.annotation.*;
import lombok.*;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import static io.micronaut.data.model.naming.NamingStrategies.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@MappedEntity(value = "contexts", namingStrategy = Raw.class)
@Schema(name="Context", description="Context Description")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Context{
    @Id 
   // @GeneratedValue(GeneratedValue.Type.AUTO)
    private String id;

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description ;

    @NonNull
    private Status status;
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "context",cascade = Relation.Cascade.ALL)
    private Set<Variable> variables;

    @DateCreated private Date dateCreated;
    @DateUpdated private Date dateUpdated;


}

