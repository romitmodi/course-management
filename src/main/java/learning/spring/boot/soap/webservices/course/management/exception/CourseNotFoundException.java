package learning.spring.boot.soap.webservices.course.management.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,customFaultCode = "{http://www.learningatrmc.com/course}CUSTOM_FAULT")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String message) {
		super(message);
	}

}
