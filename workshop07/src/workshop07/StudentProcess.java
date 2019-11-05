package workshop07;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Optional;

public class StudentProcess {

	
	public static void main(String[] args) {
	

		Student[] students = { 
				new Student("Jack", "Smith", 50.0, "IT"),
				new Student("Aaron", "Johnson", 76.0, "IT"),
				new Student("Maaria", "White", 35.8, "Business"),
				new Student("John", "White", 47.0, "Media"),
				new Student("Laney", "White", 62.0, "IT"),
				new Student("Jack", "Jones", 32.9, "Business"),
				new Student("Wesley", "Jones", 42.89, "Media")
				};
		
		
		List<Student> studentList = Arrays.asList(students);
		 
		// TASK 1
		System.out.println("Task 1:\n");
		System.out.println("Complete Student list:");
		studentList.forEach(System.out::println);
		
		// TASK 2
		System.out.println("\nTask 2:\n");
		System.out.println("Students who got 50.0-100.0 sorted by grade:");
		studentList.stream().filter(student -> student.getGrade() > 49 && student.getGrade() < 101)//filter takes a Predicate (condition)
		.sorted(Comparator.comparing(Student::getGrade)).forEach(System.out::println);//sorted takes a comparator
		
		// TASK 3
		System.out.println("\nTask 3:\n");
		System.out.println("First Student who got 50.0-100.0:");
		System.out.println(studentList.stream().filter(student -> student.getGrade() > 49 && student.getGrade() < 101)//filter takes Predicate
				.findFirst().get());//findFirst returns Optional class
		
		// TASK 4
		System.out.println("\nTask 4:\n");
		System.out.println("Students in ascending order by last name then first:");
		studentList.stream().sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName))//sorted take Comparator
		.forEach(System.out::println);
		
		System.out.println("\nStudents in descending order by last name then first:");
		studentList.stream()
		.sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName).reversed())//takes comparator
		.forEach(System.out::println);
		
		// TASK 5
		System.out.println("\nTask 5:\n");
		System.out.println("Unique Student last names:");
		studentList.stream().map(student -> student.getLastName()).distinct().sorted().forEach(System.out::println);//take lambda function
		System.out.println();
		
	}

}
