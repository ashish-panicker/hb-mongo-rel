package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_posts")
public class Post {

	@Id
	@GeneratedValue(generator = "id_gen")
	@GenericGenerator(name = "id_gen", strategy = "uuid2")
	private String id;

	private String message;

	private Date postedDate;

	@OneToOne
	@JoinColumn(name = "user")
	private User postedBy;

	public Post() {
	}

	public Post(String message, Date postedDate, User postedBy) {
		this.message = message;
		this.postedDate = postedDate;
		this.postedBy = postedBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", message=" + message + ", postedDate=" + postedDate + ", postedBy=" + postedBy
				+ "]";
	}

}
