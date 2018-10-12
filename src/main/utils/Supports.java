package main.utils;

import java.util.Arrays;

public class Supports {
    public static boolean compareStringArray(String array1[], String array2[]){
        Object[] obj1 = {array1};
        Object[] obj2 = {array2};
        if (Arrays.deepEquals(obj1,obj2))
            return true;
        else
            return false;
    }
}
