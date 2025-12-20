package com.nu.clubs.clubs_bakend.dto;

public class GalleryResponse {
	private Long id;
	private String title;
	private String description;
	private String imageUrl;
	private Long clubId;

	public GalleryResponse() {
	}

	public GalleryResponse(Long id, String title, String description, String imageUrl, Long clubId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.clubId = clubId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}
}
