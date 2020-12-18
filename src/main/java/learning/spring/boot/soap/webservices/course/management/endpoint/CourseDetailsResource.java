package learning.spring.boot.soap.webservices.course.management.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import learning.spring.boot.soap.webservices.course.management.model.CourseDetail;
import learning.spring.boot.soap.webservices.course.management.model.GetCourseDetailsRequest;
import learning.spring.boot.soap.webservices.course.management.model.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsResource {

	@PayloadRoot(namespace = "http://www.learningatrmc.com/course", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailRequest(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetail courseDetail = new CourseDetail();
		courseDetail.setId(request.getId());
		courseDetail.setName("Microservices Courses");
		courseDetail.setDescription("this is a Must have course");
		response.setCourseDetail(courseDetail);
		return response;
	}
}
