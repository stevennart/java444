package workshop07;

import java.text.DecimalFormat;

public class Student {

	DecimalFormat df = new DecimalFormat("#.00");

	private String firstName;
	private String lastName;
	private double grade;
	private String department;

	public Student(String firstName, String lastName, double grade, String department) {

		if (firstName.isEmpty() || firstName == null || lastName.isEmpty() || lastName == null || department.isEmpty() || department == null) { 
			throw new RuntimeException("strings are empty");
		} else if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException("No negative numbers or higher than 100");
		} else {
			this.firstName = firstName;
			this.lastName = lastName;
			this.grade = grade;
			this.department = department;
		}

	}

	// SETTER METHODS

	public void setFirstName(String firstName) {
		if (firstName == null || firstName.isEmpty()) {
			throw new RuntimeException("first name is empty");
		} else {
			this.firstName = firstName;
		}
	}

	public void setLastName(String lastName) {
		if (lastName == null || lastName.isEmpty()) {
			throw new RuntimeException("last name is empty");
		} else {
			this.lastName = lastName;
		}
	}

	public void setGrade(double grade) {
		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException("No negative numbers or higher than 100");
		} else {
			this.grade = grade;
		}
	}

	public void setDepartment(String department) {
		if (department == null || department.isEmpty()) {
			throw new RuntimeException("department name is empty");
		} else {
			this.department = department;
		}
	}

	// GETTER METHODS

	public String getDepartment() {
		return department;
	}

	public double getGrade() {
		return grade;
	}

	public String getName() {
		return getFirstName() + " " + getLastName();
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	// OVERRIDING EQUALS AND TOSTRING

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof Student)) {
			return false;
		}
		
		Student other = (Student) obj;
		
		if (!lastName.equalsIgnoreCase(other.lastName)) {
			return false;
		}
		
		if (!firstName.equalsIgnoreCase(other.firstName)) {
			return false;
		}
		
		if (grade != other.grade) {
			return false;
		}
		
		if (!department.equalsIgnoreCase(other.department)) {
			return false;
		}
	
		return true;
	}

	@Override
	public String toString() {
		
		return String.format("%-9s", firstName) +
		String.format("%-12s", lastName) +
		String.format("%-7s", (df.format(getGrade()))) +
		String.format("%-10s", department);

	}

}
