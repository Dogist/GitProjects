/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.lamda.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author masix
 */
public class AppService {

    private static AppService appService = null;

    private List<Student> studentList = null;

    private AppService() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Rob", "New Jersey", LocalDate.of(1993, 3, 18), 7));
        studentList.add(new Student("Alf", "Aspen", LocalDate.of(1970, 3, 18), 1));
        studentList.add(new Student("Bert", "Berlin", LocalDate.of(1964, 3, 18), 2));
        studentList.add(new Student("Anton", "Vienna", LocalDate.of(1969, 3, 18), 3));
        studentList.add(new Student("Andi", "Augusta", LocalDate.of(1964, 3, 18), 4));
        studentList.add(new Student("Gerd", "Vienna", LocalDate.of(2000, 3, 18), 5));
        studentList.add(new Student("Sue", "Aspen", LocalDate.of(1996, 3, 18), 6));
    }

    public static AppService getAppService() {
        if (appService == null) {
            appService = new AppService();
        }
        return appService;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<Display> processList(Comparator<Student> comparator, List<Predicate<Student>> filters, List<Consumer<Display<Student>>> actions) {
        Stream<Student> studentStream = studentList.stream();
        for (Predicate<Student> filter : filters) {
            studentStream = studentStream.filter(filter);
        }
        if (comparator != null) {
            studentStream = studentStream.sorted(comparator);
        }
        Stream<Display<Student>> displayStream = studentStream.map((t) -> {
            return new Display<>(t.getRegistrationNumber() + " ", t);
        });
        for (Consumer<Display<Student>> action : actions) {
            displayStream = displayStream.peek(action);
        }
        return displayStream.collect(Collectors.toList());
    }
}
