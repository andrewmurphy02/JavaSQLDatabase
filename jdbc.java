/*
 *  DO:  more $HOME/.my.cnf to see your MySQL username and  password
 *  CHANGE:  MYUSERNAME  and   MYMYSQLPASSWORD  in the main function of
 *  this program to your username and mysql password 
 *  MAKE SURE that you download mysql-connector-java-5.1.40-bin.jar from this assignment description on the class website
 *  COMPILE AND RUN: 
 *  javac *.java
 *  java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_example
 *  */

import java.sql.*;
import java.util.Scanner;

public class jdbc_example {

	// The instance variables for the class
	private Connection connection;
	private Statement statement;

	// The constructor for the class
	public jdbc_example() {
		connection = null;
		statement = null;
	}

	// The main program", that tests the methods
	public static void main(String[] args) throws SQLException {
		String Username = "ajm057"; // Change to your own username
		String mysqlPassword = "de2ki3Ah"; // Change to your own mysql Password

		// Create a jdbc_example instance called test
		jdbc_example test = new jdbc_example();

		// Connect and set up the database for use
		test.initDatabase(Username, mysqlPassword);

		Scanner s1 = new Scanner(System.in);
		int menuChoice = 0;
		while (menuChoice != 6) {
			System.out.println("-----User Menu-----");
			System.out.println("1. Find Professors");
			System.out.println("2. Find Sections");
			System.out.println("3. Add Section");
			System.out.println("4. Update Section");
			System.out.println("5. Report Enrollments");
			System.out.println("6. Quit");

			menuChoice = s1.nextInt();

			switch (menuChoice) {
				case 1:
					test.findProfessors();
					break;
				case 2:
					test.findSections();
					break;
				case 3:
					test.addSection();
					break;
				case 4:
					test.updateSection();
					break;
				case 5:
					test.reportEnrollments();
					break;
				case 6:
					System.out.println("Exiting!");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
			}
		}
		// Disconnect
		test.disConnect();
	}

	// Part 1
	public void findProfessors() {
		query("SELECT DEPT_NAME FROM DEPT");
		System.out.println("\nChoose a department.");
		System.out.println("CSCE");
		System.out.println("ELEG");
		System.out.println("MEEG");
		Scanner sc2 = new Scanner(System.in);
		String dept = sc2.nextLine();
		if (dept.equalsIgnoreCase("CSCE")) {
			query("SELECT * FROM PROFESSOR WHERE DEPT_CODE = 'CSCE'");
		} else if (dept.equalsIgnoreCase("ELEG")) {
			query("SELECT * FROM PROFESSOR WHERE DEPT_CODE = 'ELEG'");
		} else if (dept.equalsIgnoreCase("MEEG")) {
			query("SELECT * FROM PROFESSOR WHERE DEPT_CODE = 'MEEG'");
		} else {
			System.out.println("\nThat department does not exist");
		}
	}

	// Part 2
	public void findSections() {
		// All classes or open classes
		System.out.println("Would you like to see all classes or just open classes?");
		System.out.println("1 = all classes");
		System.out.println("2 = only open classes");
		Scanner sc3 = new Scanner(System.in);
		int classChoice = sc3.nextInt();

		// If all classes
		if (classChoice == 1) {
			System.out.println("Would you like to search by department or level?");
			System.out.println("1 = department");
			System.out.println("2 = level");
			Scanner sc4 = new Scanner(System.in);
			int choice = sc4.nextInt();
			// If department
			if (choice == 1) {
				System.out.println("Which Department?");
				System.out.println("CSCE");
				System.out.println("ELEG");
				System.out.println("MEEG");
				Scanner sc6 = new Scanner(System.in);
				String choice1 = sc6.nextLine();
				if (choice1.equalsIgnoreCase("CSCE")) {
					query(
							"SELECT * FROM SECTION WHERE DEPT_CODE = 'CSCE'");
				} else if (choice1.equalsIgnoreCase("ELEG")) {
					query("SELECT * FROM SECTION WHERE DEPT_CODE = 'ELEG'");
				} else if (choice1.equalsIgnoreCase("MEEG")) {
					query("SELECT * FROM SECTION WHERE DEPT_CODE = 'MEEG'");
				} else {
					System.out.println("That department does not exist");
				}

			}
			// If level
			if (choice == 2) {
				System.out.println("What level do you want?");
				System.out.println("1000, 2000, 3000, 4000, or 5000");
				Scanner sc5 = new Scanner(System.in);
				String pick = sc5.nextLine();
				if (pick.equalsIgnoreCase("1000")) {
					query("SELECT * FROM SECTION WHERE COURSE_NUM >= 1000 AND COURSE_NUM < 2000");
				} else if (pick.equalsIgnoreCase("2000")) {
					query("SELECT * FROM SECTION WHERE COURSE_NUM >= 2000 AND COURSE_NUM < 3000");
				} else if (pick.equalsIgnoreCase("3000")) {
					query("SELECT * FROM SECTION WHERE COURSE_NUM >= 3000 AND COURSE_NUM < 4000");
				} else if (pick.equalsIgnoreCase("4000")) {
					query("SELECT * FROM SECTION WHERE COURSE_NUM >= 4000 AND COURSE_NUM < 5000");
				} else if (pick.equalsIgnoreCase("5000")) {
					query("SELECT * FROM SECTION WHERE COURSE_NUM >= 5000 AND COURSE_NUM < 6000");
				} else {
					System.out.println("That course level does not exist");
				}
			} else {
				System.out.println("Incorrect input");
			}
		}
		// If open classes
		if (classChoice == 2) {
			System.out.println("Would you like to search by department or level?");
			System.out.println("1 = department");
			System.out.println("2 = level");
			Scanner sc7 = new Scanner(System.in);
			int choice3 = sc7.nextInt();
			// If department
			if (choice3 == 1) {
				System.out.println("Which Department?");
				System.out.println("CSCE");
				System.out.println("ELEG");
				System.out.println("MEEG");
				Scanner sc8 = new Scanner(System.in);
				String choice4 = sc8.nextLine();
				if (choice4.equalsIgnoreCase("CSCE")) {
					query(
							"SELECT * FROM SECTION WHERE DEPT_CODE = 'CSCE' AND (CURRENT_ENROLLMENT < MAX_ENROLLMENT)");
				} else if (choice4.equalsIgnoreCase("ELEG")) {
					query(
							"SELECT * FROM SECTION WHERE DEPT_CODE = 'ELEG' AND (CURRENT_ENROLLMENT < MAX_ENROLLMENT)");
				} else if (choice4.equalsIgnoreCase("MEEG")) {
					query(
							"SELECT * FROM SECTION WHERE DEPT_CODE = 'MEEG' AND (CURRENT_ENROLLMENT < MAX_ENROLLMENT)");
				} else {
					System.out.println("That department does not exist");
				}
			}
			// If level
			if (choice3 == 2) {
				System.out.println("What level do you want?");
				System.out.println("1000, 2000, 3000, 4000, or 5000");
				Scanner sc9 = new Scanner(System.in);
				String choice5 = sc9.nextLine();
				if (choice5.equalsIgnoreCase("1000")) {
					query(
							"SELECT * FROM SECTION WHERE (COURSE_NUM >= 1000 AND COURSE_NUM < 2000) AND (CURRENT_ENROLLMENT < MAX_ENROLLMENT)");
				} else if (choice5.equalsIgnoreCase("2000")) {
					query(
							"SELECT * FROM SECTION WHERE (COURSE_NUM >= 2000 AND COURSE_NUM < 3000) AND (CURRENT_ENROLLMENT < MAX_ENROLLMENT)");
				} else if (choice5.equalsIgnoreCase("3000")) {
					query(
							"SELECT * FROM SECTION WHERE (COURSE_NUM >= 3000 AND COURSE_NUM < 4000) AND (CURRENT_ENROLLMENT < MAX_ENROLLMENT)");
				} else if (choice5.equalsIgnoreCase("4000")) {
					query(
							"SELECT * FROM SECTION WHERE (COURSE_NUM >= 4000 AND COURSE_NUM < 5000) AND (CURRENT_ENROLLMENT < MAX_ENROLLMENT)");
				} else if (choice5.equalsIgnoreCase("5000")) {
					query(
							"SELECT * FROM SECTION WHERE (COURSE_NUM >= 5000 AND COURSE_NUM < 6000) AND (CURRENT_ENROLLMENT < MAX_ENROLLMENT)");
				} else {
					System.out.println("That level does not exist");
				}
			} else {
				System.out.println("Incorrect input");
			}
		} else {
			System.out.println("Incorrect input");
		}
	}

	// Part 3
	public void addSection() {
		// Display list of courses
		System.out.println("List of Courses:");
		query("SELECT * FROM COURSE");

		// Get user input
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter department code: ");
		String departmentCode = scanner.nextLine();
		System.out.println("Enter course number: ");
		String courseNumber = scanner.nextLine();
		System.out.println("Enter professor id: ");
		String professorId = scanner.nextLine();
		System.out.println("Enter room number: ");
		String roomNumber = scanner.nextLine();
		System.out.println("Enter building: ");
		String building = scanner.nextLine();
		System.out.println("Enter days: ");
		String days = scanner.nextLine();
		System.out.println("Enter start time: ");
		String startTime = scanner.nextLine();
		System.out.println("Enter end time: ");
		String endTime = scanner.nextLine();
		System.out.println("Enter start date: ");
		String startDay = scanner.nextLine();
		System.out.println("Enter end date: ");
		String endDay = scanner.nextLine();
		System.out.println("Enter max enrollment: ");
		String maxEnrollment = scanner.nextLine();
		System.out.println("Enter current enrollment: ");
		String currentEnrollment = scanner.nextLine();
		// Set sectionId
		String sectionId = "10001";
		// Check that all NOT NULL values have input
		if (sectionId.isEmpty() || departmentCode.isEmpty() || courseNumber.isEmpty() || professorId.isEmpty()
				|| roomNumber.isEmpty() || building.isEmpty() || startDay.isEmpty() || endDay.isEmpty()
				|| currentEnrollment.isEmpty()) {
			System.out.println("One of the NOT NULL values is empty");
		} else {
			System.out.println("All NOT NULL values are filled!");
		}

		// The SQL
		String q = ("INSERT INTO SECTION (SID, DEPT_CODE, COURSE_NUM, PROF_ID, ROOM_NUM, BUILDING, DAYS, START_TIME, END_TIME, START_DAY, END_DAY, MAX_ENROLLMENT, CURRENT_ENROLLMENT) VALUES ("
				+ sectionId + ", '" + departmentCode + "', '" + courseNumber + "', " + professorId + ", "
				+ roomNumber + ", '" + building + "', '" + days + "', '" + startTime + "', '" + endTime
				+ "', '" + startDay + "', '" + endDay + "', " + maxEnrollment + ", " + currentEnrollment
				+ ")");
		try {
			// Execute the query
			statement.executeUpdate(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Part 4
	public void updateSection() {
		// Get dept and course number input
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input dept");
		String department = scanner.nextLine();
		System.out.println("Input course number");
		String courseNumber = scanner.nextLine();

		// Call a query
		query("SELECT * FROM SECTION WHERE DEPT_CODE = '" + department + "' AND COURSE_NUM = '" + courseNumber + "'");

		// Get sectionId, attribute, and value input
		System.out.println("Enter the section id of the course section you want to update");
		String sectionId = scanner.nextLine();
		System.out.println("What attribute do you want to update?");
		String attribute = scanner.nextLine();
		System.out.println("What is the value you would like to insert?");
		String value = scanner.nextLine();

		// Make sure primary key is not updated
		if (!attribute.equalsIgnoreCase("SID")) {
			// The SQL
			String q = "UPDATE SECTION SET " + attribute + " = '" + value + "' WHERE SID = '" + sectionId + "'";
			try {
				// Execute the query
				statement.executeUpdate(q);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Part 5
	public void reportEnrollments() {
		// Choose department
		System.out.println("Which department?");
		System.out.println("CSCE");
		System.out.println("ELEG");
		System.out.println("MEEG");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if (input.equalsIgnoreCase("CSCE")) {
			query("SELECT SUM(CURRENT_ENROLLMENT) FROM SECTION WHERE DEPT_CODE = 'CSCE'");
		} else if (input.equalsIgnoreCase("ELEG")) {
			query("SELECT SUM(CURRENT_ENROLLMENT) FROM SECTION WHERE DEPT_CODE = 'ELEG'");
		} else if (input.equalsIgnoreCase("MEEG")) {
			query("SELECT SUM(CURRENT_ENROLLMENT) FROM SECTION WHERE DEPT_CODE = 'MEEG'");
		} else {
			System.out.println("Bad input");
		}
	}

	// Connect to the database
	public void connect(String Username, String mysqlPassword) throws SQLException {
		try {
			String url = "jdbc:mysql://localhost/" + Username + "?useSSL=false";
			System.out.println(url);
			connection = DriverManager.getConnection(url, Username, mysqlPassword);
		} catch (Exception e) {
			throw e;
		}
	}

	// Disconnect from the database
	public void disConnect() throws SQLException {
		connection.close();
		statement.close();
	}

	// Execute an SQL query passed in as a String parameter
	// and print the resulting relation
	public void query(String q) {
		try {
			ResultSet resultSet = statement.executeQuery(q);
			System.out.println("---------------------------------");
			System.out.println("Query: \n" + q + "\n\nResult: ");
			print(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Print the results of a query with attribute names on the first line
	// Followed by the tuples, one per line
	public void print(ResultSet resultSet) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		int numColumns = metaData.getColumnCount();

		printHeader(metaData, numColumns);
		printRecords(resultSet, numColumns);
	}

	// Print the attribute names
	public void printHeader(ResultSetMetaData metaData, int numColumns) throws SQLException {
		for (int i = 1; i <= numColumns; i++) {
			if (i > 1)
				System.out.print(",  ");
			System.out.print(metaData.getColumnName(i));
		}
		System.out.println();
	}

	// Print the attribute values for all tuples in the result
	public void printRecords(ResultSet resultSet, int numColumns) throws SQLException {
		String columnValue;
		while (resultSet.next()) {
			for (int i = 1; i <= numColumns; i++) {
				if (i > 1)
					System.out.print(",  ");
				columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
	}

	// Insert into any table, any values from data passed in as String parameters
	public void insert(String table, String values) {
		String q = "INSERT into " + table + " values (" + values + ")";
		try {
			statement.executeUpdate(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// init and testing
	// Assumes that the tables are already created
	public void initDatabase(String Username, String Password) throws SQLException {

		connect(Username, Password);
		// create a statement to hold mysql queries
		statement = connection.createStatement();
	}
}
