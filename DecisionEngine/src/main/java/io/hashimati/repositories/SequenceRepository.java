package io.hashimati.repositories;

import io.hashimati.domains.Sequence;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import java.util.*;
import org.bson.types.ObjectId;





@MongoRepository
public interface SequenceRepository extends CrudRepository<Sequence, String> {

    public Optional<Sequence> findByName(String name);


    public Long updateByName(String query, String name, int counter);

}

