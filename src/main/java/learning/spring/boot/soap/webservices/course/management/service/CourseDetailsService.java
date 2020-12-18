package learning.spring.boot.soap.webservices.course.management.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import learning.spring.boot.soap.webservices.course.management.internal.model.Course;

@Service
public class CourseDetailsService {

	private static List<Course> courses = new ArrayList<Course>();

	static {
		courses.add(new Course(1, "Spring In Action", "Good Book about Spring framework"));
		courses.add(new Course(2, "Spring-Boot In Action", "Good Book about Spring Boot"));
		courses.add(new Course(3, "Junit In Action", "Good Book about Junit framework"));
	}

	// Get all courses
	public List<Course> findAllCourses() {
		return courses;
	}

	// get course details using id
	public Course findCourseById(int id) {
		for (Course course : courses) {
			if (id == course.getId()) {
				return course;
			}
		}
		return null;
	}

	// delete course details using id
	public int deleteCourseById(int id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (id == course.getId()) {
				iterator.remove();
				return 1;
			}
		}
		return 0;
	}

	// update or add course
}
