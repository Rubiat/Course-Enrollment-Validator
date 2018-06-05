package Assignment4;

import java.util.Scanner;

public class Course implements DirectlyRelatable {

	private String courseID, courseName;
	private String preReqID;
	private String coReqID;
	private double credit;


	public Course(String courseID, String courseName, String preReqID, String coReqID, double credit) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.preReqID = preReqID;
		this.coReqID = coReqID;
		this.credit = credit;
	}

	/* Copy constructor that takes a new value for the courseID */
	public Course(Course object, String value) {
		this.courseName = object.courseName;
		this.preReqID = object.preReqID;
		this.coReqID = object.coReqID;
		this.credit = object.credit;
		courseID = value;
	}

	/*  Clone method prompts user to enter the value to change the courseID */
	public Course clone() {
		Scanner input = new Scanner(System.in);
		String courseID;
		System.out.println("Please enter the courseID: ");
		courseID = input.next();
		input.close();
		return new Course(this, courseID);
	}

	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getPreReqID() {
		return preReqID;
	}
	public void setPreReqID(String preReqID) {
		this.preReqID = preReqID;
	}
	public String getCoReqID() {
		return coReqID;
	}
	public void setCoReqID(String coReqID) {
		this.coReqID = coReqID;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}


	public String toString() {
		return "Course [courseID=" + courseID + ", courseName=" + courseName + ", preReqID=" + preReqID + ", coReqID="
				+ coReqID + ", credit=" + credit + "]";
	}

	public boolean equals (Object another) {
		if (another == null || this.getClass() != another.getClass()) 
			return false;

		Course anotherCourse = (Course) another;

		return (this.courseName.equals(anotherCourse.courseName) && this.preReqID.equals(anotherCourse.preReqID) && 
				this.coReqID.equals(anotherCourse.coReqID) && this.credit == anotherCourse.credit);
	}

	public boolean isDirectlyRelated(Course C) {
		return (this.preReqID.equals(C.courseID) || this.coReqID.equals(C.courseID));
	}


}
