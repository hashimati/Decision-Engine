package io.hashimati.repositories;

import io.hashimati.domains.Variable;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import java.util.*;


@MongoRepository
public interface VariableRepository extends CrudRepository<Variable, String> {

    public Optional<Variable> findByName(String name);
    public Iterable<Variable> findAllByContext(String context);

}

