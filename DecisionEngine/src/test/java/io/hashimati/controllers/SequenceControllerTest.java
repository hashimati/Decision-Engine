package io.hashimati.controllers;

import io.hashimati.domains.Sequence;
import io.hashimati.utils.Randomizer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;





import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class SequenceControllerTest {

    @Inject
    @Client("/api/v1/sequence")
    HttpClient client;

    HashMap<String, Object> randomObject;
    

    @Test
    void save() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Randomizer randomizer= new Randomizer<Sequence>(Sequence.class);
        randomObject = randomizer.getRandomInstance();
        
        HttpRequest<HashMap<String, Object>> request = HttpRequest.POST("/save", randomObject);
        this.randomObject = client.toBlocking().retrieve(request, HashMap.class);
        assertNotNull(randomObject);
    }

    @Test
    void findById() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        save();
         
        MutableHttpRequest<Object> request = HttpRequest.GET("/get?id="+this.randomObject.get("id"));
        HashMap<String, Object> randomObject = client.toBlocking().retrieve(request, HashMap.class);
        assertNotNull(randomObject);
    }

    @Test
    void deleteById() throws InstantiationException, IllegalAccessException, NoSuchFieldException{
        save();
         
        MutableHttpRequest<Object> request = HttpRequest.DELETE("/delete/"+this.randomObject.get("id"));
        Boolean body= client.toBlocking().retrieve(request, Boolean.class);
        assertTrue(body.booleanValue());
    }

    @Test
    void findAll() throws InstantiationException, IllegalAccessException, NoSuchFieldException{
        save();
         
        MutableHttpRequest<Object> request = HttpRequest.GET("/findAll");
        Iterable<Sequence> list = client.toBlocking().retrieve(request, Iterable.class);
        assertNotNull(list);

    }

    @Test
    void update() throws InstantiationException, IllegalAccessException, NoSuchFieldException{
        save();
         
        HttpRequest<HashMap<String, Object>> request = HttpRequest.PUT("/update", this.randomObject);
        HashMap<String, Object> randomObject = client.toBlocking().retrieve(request, HashMap.class);
        assertNotNull(randomObject);
    }
}


