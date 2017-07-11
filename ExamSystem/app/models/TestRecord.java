package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_testrecord")
public class TestRecord extends Model{
	
	@Required
	@Column(name="beginTime")
	public Date beginTime;
	
	@Required
	@Column(name="endTime")
	public Date endTime;
	
	@Required
	@Column(name="status")
	public int status;
	
	@Column(name="remark")
	public String remark;
	
	public Paper paper;
	
	public TestRecord() {
	}
	
	public TestRecord(Date beginTime, Date endTime, int status, String remark, Paper paper) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.remark = remark;
		this.paper = paper;
	}
	
	public TestRecord(Date beginTime, Date endTime, int status, Paper paper) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.paper = paper;
	}
}
