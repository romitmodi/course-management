package learning.spring.boot.soap.webservices.course.management.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//enable Spring web services
@EnableWs
//create spring configuration
@Configuration
public class WebServiceConfig {

	// MessageDispatcherServlet is needed for handling request and identifying
	// end-point corresponding to a request.
	// application context and URL mapping --> /ws/* in our case.

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(final ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		// below change enables the WSDL creation when end user hit a URL
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	// our expectation is to generate courses.wsdl using /ws/courses.wsdl
	// port-type:- course-port
	// namespace:- http://www.learningatrmc.com/course
	// course-details.xsd

	@Bean
	public XsdSchema courseXsdSchema() {
		return new SimpleXsdSchema(new ClassPathResource("schema-definition/course-details.xsd"));
	}

	@Bean(name = "courses")
	public DefaultWsdl11Definition wsdl11Definition(XsdSchema courseSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("course-port");
		definition.setTargetNamespace("http://www.learningatrmc.com/course");
		definition.setSchema(courseSchema);
		definition.setTransportUri("/ws");
		return definition;
	}
}
