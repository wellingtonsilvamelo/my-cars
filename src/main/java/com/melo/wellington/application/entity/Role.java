package com.melo.wellington.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tbg_role", uniqueConstraints={@UniqueConstraint(columnNames = {"role_description"})})
public class Role {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private Long id;
	
	@Column(name="role_description", length=50, nullable=false)
	private String description;
	
	public Role(Long id) {
		this.id = id;
	}
	
	public Role() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
