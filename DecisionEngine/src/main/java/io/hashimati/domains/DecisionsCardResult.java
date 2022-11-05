package io.hashimati.domains;

import java.lang.reflect.Parameter;
import java.util.HashMap;

public class DecisionsCardResult {



    public DecisionsCardResult(HashMap<String, ?> parameters, HashMap<String,?> result, String message){

        this.parameters = parameters;
        this.result = result;
        this.message = message;

    }
    private HashMap<String,?> result = new HashMap<>();
    private HashMap<String, ?> parameters = new HashMap<>();

    private String message ="";
    public HashMap<String, ?> getResult() {
        return result;
    }

    public void setResult(HashMap<String, ?> result) {
        this.result = result;
    }

    public HashMap<String, ?> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, ?> parameters) {
        this.parameters = parameters;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
