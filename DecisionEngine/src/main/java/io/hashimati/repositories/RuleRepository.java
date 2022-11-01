package io.hashimati.repositories;

import io.hashimati.domains.Rule;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import java.util.*;
import org.bson.types.ObjectId;





@MongoRepository
public interface RuleRepository extends CrudRepository<Rule, String> {

}

