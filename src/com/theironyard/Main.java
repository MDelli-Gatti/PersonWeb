package com.theironyard;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static ArrayList<Person> people = new ArrayList<>();


    public static void parsePeople() throws FileNotFoundException {
        File f = new File("people.csv");
        Scanner fileScanner = new Scanner(f);
        fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split(",");
            Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
            people.add(person);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        parsePeople();

        Spark.init();
        Spark.get(
                "/",
                (request, response) -> {
                    String offsetStr = request.queryParams("offset");
                    int offset = 0;
                    boolean hasPrevious = false;
                    boolean hasNext = true;
                    if (offsetStr != null) {
                        offset = Integer.valueOf(offsetStr);
                        if (offset <= 0) {
                            hasPrevious = false;
                        }
                        else {
                            hasPrevious = true;
                        }

                        if (offset+20 >= 1000){
                            hasNext = false;
                        }
                        else {
                            hasNext = true;
                        }
                    }
                    ArrayList p = new ArrayList<>(people.subList(offset, offset + 20));
                    HashMap m = new HashMap();
                    m.put("people", p);
                    m.put("offsetNext", offset + 20);
                    m.put("offsetPrevious", offset - 20);
                    m.put("hasPrevious", hasPrevious);
                    m.put("hasNext", hasNext);
                    return new ModelAndView(m, "home.html");
                },
                new MustacheTemplateEngine()
        );
        Spark.get(
                "/person",
                (request, response) -> {
                    HashMap m = new HashMap<>();
                    int idNum = Integer.valueOf(request.queryParams("id"));
                    Person per = people.get(idNum - 1);
                    m.put("id", per.id);
                    m.put("per", per);
                    return new ModelAndView(m, "person.html");
                },
                new MustacheTemplateEngine()
        );
    }
}
