package com.yipeng.stat211j.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by huangyip on 14/09/2015.
 */
public class Checker {

    public static final String PATH_211 = "res/211.csv";

    private List<String> school211List;
    private int nb211;

    public Checker() {
        school211List = new ArrayList<String>(); // Compatible with java 1.6 and lower
        nb211 = 0;
    }

    public void init() {
        retrieveSchool211List();
    }

    public int count211(List<String> schoolNames) {
        int count = 0;

        int nbSchoolNames = schoolNames.size();
        for (int i = 0; i < nbSchoolNames; i++) {
            if (is211(schoolNames.get(i))) {
                count++;
            }
        }

        return count;
    }

    public boolean is211(String schoolName) {
        for (int i = 0; i < nb211; i++) {
            if (schoolName.contains(school211List.get(i))) {
                return true;
            }
        }
        return false;
    }

    private void retrieveSchool211List() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_211), "UTF-8"));
            String line = br.readLine();
            while (line != null) {
                school211List.addAll(Arrays.asList(line.trim().split(",")));
                line = br.readLine();
            }
            nb211 = school211List.size();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getSchool211List() {
        return school211List;
    }

    public void setSchool211List(List<String> school211List) {
        this.school211List = school211List;
        this.nb211 = this.school211List.size();
    }
}
