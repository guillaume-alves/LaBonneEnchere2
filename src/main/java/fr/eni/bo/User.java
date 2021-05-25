package fr.eni.bo;

public class User {

	private Integer userId;
	private String userNickname;
	private String userName;
	private String userFirstname;
	private String userEmail;
	private String userPhone;
	private String userStreet;
	private String userPostalCode;
	private String userCity;
	private String userPassword;
	private Integer userCredit;
	private Boolean admin;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer id) {
		this.userId = id;
	}

	public void setUserEmail(String email) {
		this.userEmail = email;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserPassword(String motDePasse) {
		this.userPassword = motDePasse;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserName(String nom) {
		this.userName = nom;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserStreet() {
		return userStreet;
	}

	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}

	public String getUserPostalCode() {
		return userPostalCode;
	}

	public void setUserPostalCode(String userPostalCode) {
		this.userPostalCode = userPostalCode;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public Integer getUserCredit() {
		return userCredit;
	}

	public void setUserCredit(Integer userCredit) {
		this.userCredit = userCredit;
	}

	public boolean getUserAdmin() {
		return admin;
	}

	public void setUserAdmin(Boolean admin) {
		this.admin = admin;
	}

}
