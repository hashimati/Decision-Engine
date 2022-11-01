package io.hashimati.repositories;

import io.hashimati.domains.Decision;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import java.util.*;
import org.bson.types.ObjectId;





@MongoRepository
public interface DecisionRepository extends CrudRepository<Decision, String> {

    public Optional<Decision> findByName(String name);

    public Iterable<Decision> findAllByContext(String context);


    public Long updateByName(String query, String name);

}

