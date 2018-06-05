package Assignment4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EnrollmentResults {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to Rubiat's course verifier");
		
		createSyllabus();
		//System.out.println(Syllabus.head.next.next.getCourse().getCoReqID());
		
		
		createArrayList();
		
		//search();
		
		/*Course c1 = new Course(Syllabus.head.course, Syllabus.head.course.getCourseID());
		Course c2 = new Course(Syllabus.head.next.course, Syllabus.head.next.course.getCourseID());
		System.out.println();*/
		
		//System.out.println(c1.clone());
		
		/*if (c1.isDirectlyRelated(c2))
			System.out.println(c1.getCourseID() + " is directly related to " + c2.getCourseID());
		else
			System.out.println(c1.getCourseID() + " is not directly related to " + c2.getCourseID());
		*/
		
		/*
		CourseList copy = new CourseList(Syllabus);
		System.out.println(copy);
		
		copy.addToStart(c2);
		System.out.println();
		System.out.println(copy);
		
		copy.insertAtIndex(c1, 0);
		System.out.println();
		System.out.println(copy);
		
		copy.deleteFromIndex(8);
		System.out.println();
		System.out.println(copy);
		
		copy.deleteFromStart();
		System.out.println();
		System.out.println(copy);
		
		copy.replaceAtIndex(c1, 4);
		System.out.println();
		System.out.println(copy);
		*/
		
		System.out.println("End of program");



	}

	static CourseList Syllabus = new CourseList();

	public static void createSyllabus() {
		Scanner input = null;

		try {
			input = new Scanner(new FileInputStream("C:\\Users\\Rubiat Zaman\\Desktop\\Comp249_W18_Assg4_Files\\Syllabus.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Could not open input file");
		}

		String courseID = null, courseName = null, preReqID = null, coReqID = null;
		double credit;

		String s = "";
		String [] line1; // each 'object' in the text doc has 3 lines of info
		String [] line2;
		String [] line3;

		while (input.hasNextLine()) {
			courseID = null; courseName = null; preReqID = null; coReqID = null;
			
			s = input.nextLine();  // read first line of info of an object
			line1 = s.split("	");

			courseID = line1[0];  // first word is always the course id

			courseName = line1[1];  // 2nd word is always the course name
			credit = Double.parseDouble(line1[2]);  // 3rd 'word' is the number of credits

			s = input.nextLine(); // read second line of info
			line2 = s.split("	");

			if (line2.length>1) {  // meaning if there's actually a word after the 'P' for prerequisite
				preReqID = line2[1]; // in that case it would be the second word
			}

			s = input.nextLine();  // 3rd line of info
			line3 = s.split("	");

			if (line3.length>1)  { // to see if it contains something after the 'C'
				coReqID = line3[1];
			}

			if (Syllabus.contains(courseID) == false) { // verifying if the list already contains that course
				Course courseToAdd = new Course(courseID, courseName, preReqID, coReqID, credit);
				Syllabus.addToStart(courseToAdd);
			}

			if (input.hasNextLine())
				input.nextLine();
			
			
		}
	}

	public static void createArrayList() {

		Scanner input = new Scanner(System.in);

		System.out.println("Which request file do you wish to open?");

		String s = input.next();

		String path = "C:\\Users\\Rubiat Zaman\\Desktop\\Comp249_W18_Assg4_Files\\" + s;

		ArrayList <String> Finished = new ArrayList<String>(), Requested = new ArrayList<String>();

		Scanner reader = null;

		try {
			reader = new Scanner(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			System.out.println("File could not be opened for reading");
		}

		reader.next(); // bypass the "Finished" word

		String finishedCourse = reader.next();
		String requestedCourse = "";

		while(finishedCourse.equals("Requested") == false) {
			Finished.add(finishedCourse);
			finishedCourse = reader.next();
		}

		while (reader.hasNext()) {
			requestedCourse = reader.next();
			Requested.add(requestedCourse);
		}


		if (Requested.isEmpty())  // check if student doesn't want to take any courses
			System.out.println("No enrollment courses found");

		for (String requested : Requested) {

			String prerequisite = Syllabus.find(requested).course.getPreReqID();
			String corequisite = Syllabus.find(requested).course.getCoReqID();
			
			if (prerequisite == null)
				System.out.println("Student can enroll in " + requested + " as it doesn't have any prerequsites");
			else if (prerequisite.equals(corequisite) && Requested.contains(corequisite)) // a special, weird case
				System.out.println("Student can enroll in " + requested + " as he/she is enrolling for corequisite " + corequisite);
			else if (Finished.contains(prerequisite)) {
				
				if (corequisite == null)
					System.out.println("Student can enroll in " + requested + " as he/she has completed the prerequisite " + prerequisite);
				else if (Finished.contains(corequisite))
					System.out.println("Student can enroll in " + requested + " as he/she has completed the prerequisite " + prerequisite + " and " + corequisite);
				else if (Requested.contains(corequisite))
					System.out.println("Student can enroll in " + requested + " as he/she has completed the prerequisite " + prerequisite + " and is enrolling for " + corequisite);
				else
					System.out.println("Student cannot enroll in " + requested + " as he/she does not have sufficient requirements");
			}
			else
				System.out.println("Student can't enroll in " + requested + " course as he/she doesn't have sufficient background needed");

		}
		
		input.close();

	}

	public static void search() {
		
		System.out.println("Please enter a course ID or -1 to quit");
		
		Scanner input = new Scanner(System.in);
		
		String ID = input.next();
		
		while(ID.equals("-1") == false) {
			Syllabus.find(ID);
			System.out.println("Please enter another course ID or -1 to quit");
			ID = input.next();
		}
		
		input.close();
	}
}
