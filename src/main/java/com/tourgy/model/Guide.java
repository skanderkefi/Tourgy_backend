package com.tourgy.model;


import javax.persistence.Column;   
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "guide")
public class Guide {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mdp;
	private String link;
	private String status;
	private String dispo;
	private String motiv;
	
	

	public Guide() {
		
	}
	public Guide(int id, String firstName, String lastName, String email, String mdp, String link, String dispo, String motiv) {
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mdp=mdp;
		this.link=link;
		this.dispo=dispo;
		this.motiv=motiv;
	}
	public Guide(String firstName, String lastName, String email, String mdp, String link, String dispo, String motiv) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mdp=mdp;
		this.link=link;
		this.dispo=dispo;
		this.motiv=motiv;
	}
	
public Guide(int id) {
	this.id=id;
		
	}
	
	
	public String getDispo() {
		return dispo;
	}

	public void setDispo(String dispo) {
		this.dispo = dispo;
	}

	public String getMotiv() {
		return motiv;
	}

	public void setMotiv(String motiv) {
		this.motiv = motiv;
	}
	


@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.AUTO)
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

@Column(name = "first_name", nullable = false)
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

@Column(name = "password", nullable = false)
public String getMdp() {
	return mdp;
}
public void setMdp(String mdp) {
	this.mdp = mdp;
}

@Column(name = "last_name", nullable = false)
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}

@Column(name = "gmail_address", nullable = false)
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@Column(name = "link", nullable = true)
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}

@Column(name = "status")
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}





}
