package com.theironyard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Person> people = new ArrayList<>();


    public static void parsePeople() throws FileNotFoundException {
        File f = new File("people.csv");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split(",");
            Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
            people.add(person);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        parsePeople();


    }
}
