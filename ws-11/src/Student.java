import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

	private int stdID;
	private String firstName;
	private String lastName;
	// private String[] courses;
	private String course;
//	private ArrayList<String> courses;
	// <TYPE> instead of none, is to add compile time checking incase I put a
	// different type in instead of String. (generics)

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int stdID, String firstName, String lastName, String course) throws Exception {

		// courses = new ArrayList<String>(course.size()); // capacity allocated will be
		// 10 null elements, if print size
		// out it won't
		// show default null values.
		// setStdID(stdID);
		if (stdID > 0)
			this.stdID = stdID;
		else
			throw new IllegalArgumentException("Student ID entered is not a valid number");

		// setFirstName(firstName);
		if (firstName != null || !firstName.isEmpty())  
			this.firstName = firstName;
		else
			throw new IllegalArgumentException("Student first name is empty");

		// this.firstName = firstName;
		// setLastName(lastName);

		if (course != null || !course.isEmpty())
			this.course = course;
		else
			throw new IllegalArgumentException("Student course is empty");

		if (lastName != null || !lastName.isEmpty())
			this.lastName = lastName;
		else
			throw new IllegalArgumentException("Student last name is empty");

		// this.lastName = lastName;
		// setCourses(course);
	}

	// STUDENT SETTERS
	public void setStdID(int stdID) throws Exception {

		if (stdID > 0) {
			this.stdID = stdID;
		} else {
			throw new IllegalArgumentException("Student ID is not valid");
		}
	}

	public void setFirstName(String firstName) throws Exception {

		if (firstName != null) {
			this.firstName = firstName;
		} else {
			throw new IllegalArgumentException("Student first name is empty");
		}
	}

	public void setLastName(String lastName) throws Exception {

		if (lastName != null) {
			this.lastName = lastName;
		} else {
			throw new IllegalArgumentException("Student last name is empty");
		}
	}

	public void setCourseName(String courseName) throws Exception {

		if (courseName != null) {
			this.course = courseName;
		} else {
			throw new IllegalArgumentException("Student course is empty");
		}
	}

	// STUDENT GETTERS
	public int getStdID() {
		return this.stdID;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getCourseName() {
		return this.course;
	}

//	if (course != null || !course.isEmpty()) {
//
//		this.courses = course;
//
//	} else {
//		throw new IllegalArgumentException("Student course is empty"); 
//	} 

//	public void setCourses(ArrayList<String> course) throws Exception {
//
//		if (course != null) {
//			// for (int i = 0; i < course.size(); i++) {
//			// this.courses.add(course.get(i));
//			this.courses = course;
//			// }
//		} else {
//			throw new Exception("Student courses is empty");
//		}
//
//	}

//	public ArrayList<String> getAllCourses() {
//		return this.courses;
//	}

//	public String getOneCourse(int courseIndex) {
//		return this.courses.get(courseIndex);
//	}

	// courses.add();
	// courses = new ArrayList<String>(5); 5 is the intial value not fixed.
	// course.size() gives size of array.
	// courses.remove(index);
	// courses.clear(); removes all elements from array
	// courses.set(index, value);
	// courses.trimToSize() removes any extra space of the array and condenses it to
	// only the # of elements inside array. IS NOT NECCESSARY done auto from
	// interpreter.

}
