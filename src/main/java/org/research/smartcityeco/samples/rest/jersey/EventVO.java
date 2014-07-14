package org.research.smartcityeco.samples.rest.jersey;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Event")
public class EventVO {

	@XmlElement
	Long eventID;

	@XmlElement
	String eventKey;
	
	Date startDate;
	
	@XmlElement(name = "startDateForm")
	String startDateFormatted;

	@XmlElement
	Integer amount;

	@XmlTransient
	public Long getEventID() {
		return eventID;
	}

	public void setEventID(Long eventID) {
		this.eventID = eventID;
	}

	@XmlTransient
	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@XmlTransient
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
		this.startDateFormatted = sdf.format(startDate);
	}

	@XmlTransient
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@XmlTransient
	public String getStartDateFormatted() {
		return startDateFormatted;
	}


//	@XmlTransient
//	public String getAirTimeServiceFormatted() {
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
//		return sdf.format(airTimeService);
//	}

}
