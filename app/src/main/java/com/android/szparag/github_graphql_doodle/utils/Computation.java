package com.android.szparag.github_graphql_doodle.utils;

/**
 * Created by ciemek on 05/11/2016.
 */

public class Computation {

    //todo: dagger instead of statics!

//    http://stackoverflow.com/a/237204/6942800
    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
