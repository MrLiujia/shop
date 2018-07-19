package shop.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Shopuser {
	private Long id;
	@Pattern(regexp="^[A-Za-z][A-Za-z1-9_-]+$",message="字母开头 + 数字/字母/下划线s")
	private String username;
	@Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$",message="6-21字母和数字组成")
	private String password;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
