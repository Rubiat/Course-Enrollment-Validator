package Assignment4;

import java.util.NoSuchElementException;

public class CourseList {

	class CourseNode  {

		Course course;
		CourseNode next;

		public CourseNode() {
			course = null;
			next = null;
		}

		public CourseNode(Course c, CourseNode next) {
			this.course = c;
			this.next = next;
		}

		/* Potential privacy leak here because of using c.course instead of c.course.clone()*/
		public CourseNode(CourseNode c) {
			course = c.course;
			next = c.next;
		}

		public CourseNode clone() {
			return new CourseNode(this);
		}

		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}

		public CourseNode getNext() {
			return next;
		}

		public void setNext(CourseNode next) {
			this.next = next;
		}

	}

	CourseNode head;
	private int size = 0;

	public CourseList() {
		head = null;
	}

	public CourseList(CourseList L) {

		size = L.size;

		if (L.head == null)
			head = null;
		else {
			head = null;
			CourseNode t1, t2;
			t2 = null;

			t1 = L.head;

			while (t1 != null) {

				if (head == null) {
					t2 = new CourseNode(t1);
					head = t2;
				}
				else {
					t2.next = new CourseNode(t1);
					t2 = t2.next;
				}
				t1 = t1.next;
			}
			t2 = null;
		}
	}

	/* Privacy leak since I didn't use C.clone() as parameter instead of C */
	public void addToStart(Course C) {
		head = new CourseNode(C, head);
		size++;
	}

	/* Privacy leak because of using C instead of C.clone for copy constructor */
	public void insertAtIndex(Course C, int index) throws NoSuchElementException {

		if (index < 0 || index > size-1) 
			throw new NoSuchElementException();
		else {
			size++;
			int i = 0;
			CourseNode t = head;

			if (index == 0) {
				head = new CourseNode(C, head);
				return;
			}

			while (i != index-1) {
				t = t.next;
				i++;
			}

			t.next = new CourseNode(C, t.next);

		}

	}

	public void deleteFromIndex(int index) throws NoSuchElementException {

		if (index < 0 || index > size-1) 
			throw new NoSuchElementException();
		else {
			size--;
			int i = 0;
			CourseNode t = head;

			if (index == 0) {
				head = t.next;
				return;
			}

			while (i != index-1) {
				t = t.next;
				i++;
			}

			t.next = t.next.next;
		}

	}

	public void deleteFromStart() {
		if (head == null)
			System.out.println("List is empty. Cannot delete from start");
		else {
			head = head.next;
			size--;
		}
	}

	/* privacy leak because of using C instead of C.clone() */
	public void replaceAtIndex(Course C, int index) {

		if (index < 0 || index > size-1)
			return;

		CourseNode t = head;
		int i = 0;

		while (i != index) {
			t = t.next;
			i++;
		}

		t.course = C;

	}

	/* Privacy leak here because it returns t and not t.clone() */
	public CourseNode find(String ID) {

		String id = ID;
		int iterations = 1;
		CourseNode t = head;

		// this method does not verify if the list is empty, a.k.a head == null
		
		for (int i = 0; i <= size-1; i++) {
			if (t.course.getCourseID().equals(id)) {
				//System.out.println("It took " + iterations + " attempt(s) to find the course");
				return t;
			}
			else {
				iterations++;
				t = t.next;
			}
		}

		// Does the following code only if nothing was found and returned from the list already
		System.out.println("The course was not found in the list");
		System.out.println(iterations + " iteration(s) were made");
		return null;

	}
	
	public boolean contains(String ID) {
		
		String id = ID;
		CourseNode t = head;
		
		for (int i = 0; i <= size-1; i++) {
			if (t.course.getCourseID().equals(id))
				return true;
			else
				t = t.next;
		}
		
		// return false if it went through the whole list without finding it
		return false;
		
	}
	
	public boolean equals(CourseList A) {
		
		if (this.head == null || A.head == null)
			return false;
		
		boolean isEqual = true;
		
		CourseNode t = this.head;
		CourseNode s = A.head;
		
		while (t != null || s != null) {
			if (t.equals(s) == false) {
				isEqual = false;
				break;
			}
		}
		
		return isEqual;
		
	}

	public String toString() {
		CourseNode t = head;
		for (int i = 0; i < size; i++) {
			System.out.print(t.course.getCourseID() + " ---> ");
			t = t.next;
		}
		System.out.println("\nsize = " + size);
		return "";
	}
}
