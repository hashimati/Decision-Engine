package io.hashimati.domains;


import io.micronaut.data.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

import static io.micronaut.data.model.naming.NamingStrategies.Raw;


@MappedEntity(value = "rules", namingStrategy = Raw.class)
@Schema(name="Rule", description="Rule Description")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Rule{
    @Id 
    @GeneratedValue(GeneratedValue.Type.AUTO) 
    private String id;
    private String expression;
	private String variable;
	private String operand;
	private String value;
	private String name;
    private String context;

    public <T extends String, Integer, Float, Double, Long, Short> String toExpression(T variable)
    {
        return variable.toString() + " "+ this.operand + " " + value;
    }
    @DateCreated private Date dateCreated;
    @DateUpdated private Date dateUpdated;


}

