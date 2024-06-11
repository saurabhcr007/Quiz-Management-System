package ltts.com.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
public class Codes {
	@Id
	private int codeid;
	private String email;
	private String title;
	private Date createdate;
	
	private String status;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "code")
	private Set<Questions> questions=new HashSet<>();
	public int getCodeid() {
		return codeid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCodeid(int codeid) {
		this.codeid = codeid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Questions> questions) {
		this.questions = questions;
	}
	
	public Codes(int codeid, String email, String title, Date createdate, String status) {
		super();
		this.codeid = codeid;
		this.email = email;
		this.title = title;
		this.createdate = createdate;
		this.status = status;
	}
	public Codes() {
		super();
	}
	@Override
	public String toString() {
		return "Codes [codeid=" + codeid + ", email=" + email + ", title=" + title + ", questions=" + questions + "]";
	}
	
}
