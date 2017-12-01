package com.soapui.automation.framework.services.data;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

@XmlRootElement(name = "ReferenceData")
public class ReferenceData {

	private String id;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date date;
	@NotNull
	private String timezone;
	@NotNull
	@Size(max = 20, min = 5)
	private String location;

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);
		formatter.format("ID:%s\nLocation:%s\nDate:%s\nTime zone:%s\n", getId(), getLocation(), getDate(), getTimezone());

		return sb.toString();
	}
}
