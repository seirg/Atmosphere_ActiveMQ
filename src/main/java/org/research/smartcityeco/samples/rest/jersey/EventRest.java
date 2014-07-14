package org.research.smartcityeco.samples.rest.jersey;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/event")
public class EventRest {

	@GET
	@Produces({ "application/xml", "application/json" })
	public EventVO getEvent() throws Exception {
		EventVO eventVO = new EventVO();
		eventVO.setAmount(3);
		eventVO.setEventID(23L);
		eventVO.setEventKey("SFCHDSK");
		eventVO.setStartDate(new Date());
		return eventVO;
	}

	@POST
	@Consumes("application/json")
	public Response receiveVirtualReceipt(EventVO eventVO)
			throws Exception {
		return Response.ok(eventVO.getEventKey()).build();
	}
	

}
