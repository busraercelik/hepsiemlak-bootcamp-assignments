package com.emlakburada.entity;

public class User {

	public int id;
	public String kullaniciTipi;
	public String name;
	public String email;
	public String photo;
	public String bio;

	public User() {

	}

	public User(String kullaniciTipi, String name, String email) {
		super();
		this.kullaniciTipi = kullaniciTipi;
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", kullaniciTipi=" + kullaniciTipi + ", name=" + name + ", email=" + email
				+ ", photo=" + photo + ", bio=" + bio + "]";
	}

}
