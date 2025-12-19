package com.nu.clubs.clubs_bakend.dto;

public class ClubResponse {
	private Long id;
	private String name;
	private String description;
	private String president;
	private String email;
	private String category;

	public ClubResponse() {
	}

	public ClubResponse(Long id, String name, String description, String president, String email, String category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.president = president;
		this.email = email;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
