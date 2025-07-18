package com.university.course.application;


import java.util.Collections;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author papa-pc
 */
@Component(
		property = {
			JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/courses1",
			JaxrsWhiteboardConstants.JAX_RS_NAME + "=Courses.Rest"
		},
		service = Application.class
	)
public class RestApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Produces("text/plain")
	public String working() {
		return "Courses!";
	}
}