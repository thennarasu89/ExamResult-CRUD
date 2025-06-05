package com.project.Exam_result.Entity;
import jakarta.persistence.*;


@Entity
public class Result {
	@Id
	private long Regno;
	
	@OneToOne
	@JoinColumn(name = "Regno", referencedColumnName = "Regno")
	@MapsId
	private Student student;
	@Version
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


	private int mark1;
	private int mark2;
	private int mark3;
	private int mark4;
	private int mark5;
	private int mark6;

	private int total;
	private String result;

	
	 public long getRegno() {
	        return Regno;
	    }

	    public void setRegno(Long Regno) {
	        this.Regno = Regno;
	    }

	    public Student getStudent() {
	        return student;
	    }

	    public void setStudent(Student student) {
	        this.student = student;
	    }

	    public int getMark1() {
	        return mark1;
	    }

	    public void setMark1(int mark1) {
	        this.mark1 = mark1;
	    }

	    public int getMark2() {
	        return mark2;
	    }

	    public void setMark2(int mark2) {
	        this.mark2 = mark2;
	    }

	    public int getMark3() {
	        return mark3;
	    }

	    public void setMark3(int mark3) {
	        this.mark3 = mark3;
	    }

	    public int getMark4() {
	        return mark4;
	    }

	    public void setMark4(int mark4) {
	        this.mark4 = mark4;
	    }

	    public int getMark5() {
	        return mark5;
	    }

	    public void setMark5(int mark5) {
	        this.mark5 = mark5;
	    }

	    public int getMark6() {
	        return mark6;
	    }

	    public void setMark6(int mark6) {
	        this.mark6 = mark6;
	    }

	    public int getTotal() {
	        return total;
	    }

	    public void setTotal(int total) {
	        this.total = total;
	    }

	    public String getResult() {
	        return result;
	    }

	    public void setResult(String result) {
	        this.result = result;
	    }
	    
	 
	    public Result() {
	    }

	    
	    public Result(Student student, int mark1, int mark2, int mark3, int mark4, int mark5, int mark6, int total, String result) {
	        this.student = student;
	        this.Regno = student.getRegno();
	        this.mark1 = mark1;
	        this.mark2 = mark2;
	        this.mark3 = mark3;
	        this.mark4 = mark4;
	        this.mark5 = mark5;
	        this.mark6 = mark6;
	        this.total = total;
	        this.result = result;
	    }


}
