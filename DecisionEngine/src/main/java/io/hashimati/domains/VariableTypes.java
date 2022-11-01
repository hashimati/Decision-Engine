package io.hashimati.domains;

import java.util.Arrays;
import java.util.List;

public class VariableTypes {


    public static List<String> numeric = Arrays.asList("Int", "Double");
    public static String BOOLEAN = "Boolean";
    public static String STRING = "String";
    public static List<String> DATE_TIME = Arrays.asList("Date", "Time");

    public static boolean isBoolean(String datatype)
    {
        return datatype.contentEquals(BOOLEAN);

    }
    public static boolean isString(String dataType)
    {
        return dataType.equalsIgnoreCase(STRING);

    }
    public static boolean isNumeric(String dataType)
    {
        return numeric.stream().anyMatch(x-> x.equalsIgnoreCase(dataType));
    }

    public static boolean isTime(String dataType)
    {
        return DATE_TIME.stream().anyMatch(x-> x.equalsIgnoreCase(dataType));

    }

}
