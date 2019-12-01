import java.io.*;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.sql.*;

public class InsertAndQueryStudents {

	// ----------------------------------------------------------------------------------------------------
	private final static JFrame entryContainer = new JFrame("Student Entry Form");

	private final static JPanel panelWrapper = new JPanel();

	private final static JTextField studentIDField = new JTextField(12);
	private final static JTextField firstNameField = new JTextField(12);
	private final static JTextField lastNameField = new JTextField(12);
	private final static JTextField courseNameField = new JTextField(12);

//	private final static JTextArea courseTextAreaField = new JTextArea(15, 15);

	private final static JPanel buttonPanel = new JPanel();

	private final static JFrame outputContainer = new JFrame("Student Information");

	private final static JTextArea outputContent = new JTextArea(5, 10);

	private static JScrollPane outputContentScrollWrapper = new JScrollPane();

	private static ArrayList<Student> stu = new ArrayList<>();

	// ---------------------------------------------------------------------------------------------------

	public static void createEntryFields() {

		// student id panel
		JPanel studentIDPanel = new JPanel();
		studentIDPanel.setLayout(new FlowLayout());

		JLabel studentIDLabel = new JLabel("Student ID:               ");

		studentIDPanel.add(studentIDLabel);
		studentIDPanel.add(studentIDField);
		panelWrapper.add(studentIDPanel);

		// first name panel
		JPanel firstNamePanel = new JPanel();
		firstNamePanel.setLayout(new FlowLayout());

		JLabel firstNameLabel = new JLabel("First Name:              ");

		firstNamePanel.add(firstNameLabel);
		firstNamePanel.add(firstNameField);
		panelWrapper.add(firstNamePanel);

		// last name panel
		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setLayout(new FlowLayout());

		JLabel lastNameLabel = new JLabel("Last Name:               ");

		lastNamePanel.add(lastNameLabel);
		lastNamePanel.add(lastNameField);
		panelWrapper.add(lastNamePanel);

		// courses panel
		JPanel coursePanel = new JPanel();
		coursePanel.setLayout(new FlowLayout());

		JLabel courseLabel = new JLabel("Course:                      ");

		coursePanel.add(courseLabel);
		coursePanel.add(courseNameField);
		panelWrapper.add(coursePanel);

	}

	public static void createSaveButton() { // SAVE BUTTON

		JButton saveButton = new JButton("Save");
		buttonPanel.add(saveButton);// added submit button to button panel

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				Connection con = null;
				Statement stmt = null;

				try {

					con = DriverManager.getConnection(Values.URL, Values.USER_NAME, Values.PASS_WORD);
					stmt = con.createStatement();

					// ArrayList<String> sCourses = new ArrayList<>();
					// String[] courses = null;

					int id = Integer.parseInt(studentIDField.getText());
					String fName = firstNameField.getText();
					String lName = lastNameField.getText();
					String courseName = courseNameField.getText();

					// object needed for student input validation.
					Student s = new Student(id, fName, lName, courseName);

					// stmt.executeUpdate("insert into Students " + "values('John-Mark', 00101,
					// 3.99, 2006, 3)");

					stmt.executeUpdate("insert into Students " + "values(" + s.getStdID() + ", '" + s.getFirstName()
							+ " " + s.getLastName() + "', '" + s.getCourseName() + "')");

					// stu.add(new Student(id, fName, lName, courseName));

					JOptionPane.showMessageDialog(buttonPanel, "Student information has been saved");

					// object serialization
//					FileOutputStream file = new FileOutputStream("stu.out");
//					ObjectOutputStream oos = new ObjectOutputStream(file);
//					oos.writeObject(stu);
//					oos.flush();
					// closes the file streams resources
//					if (file != null) {
//						file.close();
//					}

				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				} catch (NumberFormatException nfe) {
					// System.err.println(nfe.getMessage() + " User did not enter correct integer
					// value, prompt them to re-enter");
					// have to reference studentIDField as the parent component for this
					// messageDialog being showin.
					JOptionPane.showMessageDialog(studentIDField, "Student ID entered is not a valid number");

				} catch (IllegalArgumentException iae) {
					// System.err.println(nfe.getMessage() + " User did not enter correct integer
					// value, prompt them to re-enter");
					// have to reference studentIDField as the parent component for this
					// messageDialog being showin.
					JOptionPane.showMessageDialog(studentIDField, iae.getMessage());

				} catch (Exception ex) {
					System.err.println(ex.getMessage());
				} finally {
					try {
						// 4. Close the connection and statement
						if (stmt != null)
							stmt.close();

						if (con != null)
							con.close();
					} catch (Exception ex) {
						System.err.println("SQLException: " + ex.getMessage());
					}
				}
			}
		});

	} // END OF CREATE SAVE BUTTON

	public static void createDisplayButton() {

		JButton displayButton = new JButton("Display");

		buttonPanel.add(displayButton);

		displayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				Connection con = null;
				Statement stmt = null;
				try {

					con = DriverManager.getConnection(Values.URL, Values.USER_NAME, Values.PASS_WORD);
					stmt = con.createStatement();

					// FileInputStream fis = new FileInputStream("stu.out");
					// ObjectInputStream ois = new ObjectInputStream(fis);

					int studentCount = 1;

					// stu = (ArrayList<Student>) ois.readObject();

					String str = "";

					// -------
					ResultSet rs = stmt.executeQuery("select ID, STUDENT, COURSE from Students");

					while (rs.next()) {
						int id = rs.getInt("ID");
						String s = rs.getString("STUDENT");
						String c = rs.getString("COURSE");

						str += "[Student " + (studentCount++) + "]";
						str += "\nStudent ID: " + Integer.toString(id) + "\n";
						str += "Student Name: " + s + "\n";
						str += "Course: " + c + "\n\n";

						// float f = rs.getFloat("GPA");

//						System.out.println(s + " \t  " + f);
					}
					// ----------

//					for (Student s : stu) {
//
//						str += "[Student " + (studentCount++) + "]";                          
//						str += "\nStudent ID: " + Integer.toString(s.getStdID()) + "\n";
//						str += "Student Name: " + s.getFirstName() + " " + s.getLastName() + "\n";
//						str += "Course: " + s.getCourseName() + "\n\n"; 
//
//
//					}

					outputContent.setText(str);

					outputContent.setCaretPosition(0); // makes sure the scrollbar starts at the top.

					outputContentScrollWrapper.setViewportView(outputContent);

					outputContainer.add(outputContentScrollWrapper);

					outputContainer.setVisible(true);
					outputContainer.setSize(400, 300);

					outputContent.setEditable(false);
					// releasing file stream resources.

//					if (fis != null) {
//						fis.close();
//					}
//
//					if (ois != null) {
//						ois.close();
//					}

				} catch (SQLException ex) {                  
					System.err.println("SQLException: " + ex.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						// 4. Close the connection and statement
						if (stmt != null)
							stmt.close();

						if (con != null)
							con.close();
					} catch (Exception ex) {
						System.err.println("SQLException: " + ex.getMessage());
					}
				}

			}

		});

	}

	public static void main(String args[]) {

		entryContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		entryContainer.setSize(500, 400);

		panelWrapper.setLayout(new BoxLayout(panelWrapper, BoxLayout.Y_AXIS));

		createEntryFields();

		buttonPanel.setLayout(new FlowLayout());

		createSaveButton();

		createDisplayButton();

		panelWrapper.add(buttonPanel);
		entryContainer.add(panelWrapper);
		entryContainer.setLocationRelativeTo(null);
		entryContainer.setVisible(true);

//		try {
//			// 2. Get connection and statements objects
//		
//
//			// 3. Execute JDBC commands
//			
////			stmt.executeUpdate("insert into Students " + "values('Chung-Wei', 00049, 3.65, 2005, 2)");
////			stmt.executeUpdate("insert into Students " + "values('Karazim', 00150, 2.89, 2006, 3)");
////			stmt.executeUpdate("insert into Students " + "values('Ramirez', 00101, 2.09, 2006, 1)");
////			stmt.executeUpdate("insert into Students " + "values('Francis', 00125, 2.99, 2006, 3)");
//

//		} catch (SQLException ex) {
//			System.err.println("SQLException: " + ex.getMessage());
//		} finally {
//			try {
//				// 4. Close the connection and statement
//				if (stmt != null)	stmt.close();
//
//				if (con != null) con.close();
//			} catch (Exception ex) {
//				System.err.println("SQLException: " + ex.getMessage());
//			}
//		}

	}

}