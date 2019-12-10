package com.database.websockets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bulletin")
public class Bulletin {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "message")
	private String message;

	public Bulletin() {
	}

	public Bulletin(String username, String message) {
		this.username = username;
		this.message = message;
	}

	protected Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	protected String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	protected String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

}
