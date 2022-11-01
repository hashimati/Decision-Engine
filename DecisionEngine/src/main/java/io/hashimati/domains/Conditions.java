package io.hashimati.domains;

public class Conditions {

    public static class Numeric{
        public static String GREATER_THAN = ">",
                            LESS_THAN = "<",
                            EQUAL ="==",
                            GREATER_THAN_OR_EQUAL = ">=",
                            LESS_THAN_OR_EQUAL= "<=";

    }
    public static class STRING{
        public static String PLAIN_EQUAL ="==",
                            EQUALS = ".equals(X)",
                            EQUALS_IGNORE_CASE = ".equalsIgnoreCase(X)";
    }
}
