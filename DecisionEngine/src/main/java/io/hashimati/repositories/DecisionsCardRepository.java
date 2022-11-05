package io.hashimati.repositories;

import io.hashimati.domains.DecisionsCard;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import java.util.*;
import org.bson.types.ObjectId;





@MongoRepository
public interface DecisionsCardRepository extends CrudRepository<DecisionsCard, String> {

    public Optional<DecisionsCard> findByName(String name);

    public Optional<DecisionsCard> findByIdAndContext(String id, String context);

    public Iterable<DecisionsCard> findAllByContext(String context);

}

