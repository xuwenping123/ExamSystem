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
	public Date beginTime;
	
	@Required
	public Date endTime;
	
	@Required
	public int status;
	
	public String remark;
	
	public long paper_id;
	
	public TestRecord() {
	}
	
	public TestRecord(Date beginTime, Date endTime, int status, String remark, long paper_id) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.remark = remark;
		this.paper_id = paper_id;
	}
	
	public TestRecord(Date beginTime, Date endTime, int status, long paper_id) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.paper_id = paper_id;
	}
}
