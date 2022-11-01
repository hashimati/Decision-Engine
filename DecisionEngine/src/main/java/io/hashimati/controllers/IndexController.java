package io.hashimati.controllers;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

@Controller("/")
public class IndexController {
    @View("indexV")
    @Get(value = "/")
    public HttpResponse<?> home()
    {

        return HttpResponse.ok();
    }

}
