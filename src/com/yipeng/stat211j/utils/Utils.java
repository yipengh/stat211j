package com.yipeng.stat211j.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyip on 14/09/2015.
 */
public class Utils {

    private Utils() {

    }

    // log an object in console
    public static void console(Object object) {
        if (object == null) {
            System.out.println("null");
        } else {
            System.out.println(object);
        }
    }

    // log arrays in console
    public static <T> void console(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void console(long[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void console(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void console(char[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void console(float[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void console(double[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void console(boolean[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void console(byte[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void console(short[] array) {
        System.out.println(Arrays.toString(array));
    }

    // log list in console
    public static void console(List<?> list) {
        StringBuilder mStringBuilder = new StringBuilder();
        mStringBuilder.append("List [\n");
        for (int i = 0; i < list.size(); i++) {
            mStringBuilder.append("\t" + i + " => " + list.get(i) + "\n");
        }
        mStringBuilder.append("]");
        System.out.println(mStringBuilder.toString());
    }

    // log map<String, Object> in console
    public static void console(Map<String, List<?>> map) {
        for (Map.Entry<String, List<?>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => ");
            console(entry.getValue());
        }
    }
}
