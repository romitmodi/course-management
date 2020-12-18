package learning.spring.boot.soap.webservices.course.management.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import learning.spring.boot.soap.webservices.course.management.internal.model.Course;
import learning.spring.boot.soap.webservices.course.management.model.CourseDetail;
import learning.spring.boot.soap.webservices.course.management.model.DeleteCourseRequest;
import learning.spring.boot.soap.webservices.course.management.model.DeleteCourseResponse;
import learning.spring.boot.soap.webservices.course.management.model.GetAllCourseDetailsRequest;
import learning.spring.boot.soap.webservices.course.management.model.GetAllCourseDetailsResponse;
import learning.spring.boot.soap.webservices.course.management.model.GetCourseDetailsRequest;
import learning.spring.boot.soap.webservices.course.management.model.GetCourseDetailsResponse;
import learning.spring.boot.soap.webservices.course.management.model.Status;
import learning.spring.boot.soap.webservices.course.management.service.CourseDetailsService;

/**
 * @author romit
 *
 */
@Endpoint
public class CourseDetailsResource {

	@Autowired
	CourseDetailsService courseDetailsService;

	/**
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = "http://www.learningatrmc.com/course", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = courseDetailsService.findCourseById(request.getId());
		return mapCourseDetails(course);
	}

	/**
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = "http://www.learningatrmc.com/course", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailRequest(
	        @RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = courseDetailsService.findAllCourses();
		return mapAllCourse(courses);
	}

	/**
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = "http://www.learningatrmc.com/course", localPart = "DeleteCourseRequest")
	@ResponsePayload
	public DeleteCourseResponse deleteCourse(@RequestPayload DeleteCourseRequest request) {
		int result = courseDetailsService.deleteCourseById(request.getId());
		DeleteCourseResponse response = new DeleteCourseResponse();
		response.setStatus(result == 1d ? Status.SUCCESS : Status.FAILURE);
		return response;
	}

	/**
	 * @param course
	 * @return
	 */
	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetail courseDetail = mapCourse(course);
		response.setCourseDetail(courseDetail);
		return response;
	}

	/**
	 * @param courses
	 * @return
	 */
	private GetAllCourseDetailsResponse mapAllCourse(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		courses.forEach(course -> {
			response.getCourseDetail().add(mapCourse(course));
		});
		return response;
	}

	/**
	 * @param course
	 * @return
	 */
	private CourseDetail mapCourse(Course course) {
		CourseDetail courseDetail = new CourseDetail();
		courseDetail.setId(course.getId());
		courseDetail.setName(course.getName());
		courseDetail.setDescription(course.getDescription());
		return courseDetail;
	}
}
