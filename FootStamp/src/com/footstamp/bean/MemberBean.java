package com.footstamp.bean;

public class MemberBean {
	private String id;
	private String pwd;
	private String name;
	private String call;
	private String profileImg;

	public MemberBean() {
		super();
	}

	public MemberBean(String id, String pwd, String name, String call,
			String profileImg) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.call = call;
		this.profileImg = profileImg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String[] getAll(){
		return new String[]{id,pwd,name,call,profileImg};
	}
	@Override
	public String toString() {
		return "MemberBeen [id=" + id + ", pwd=" + pwd + ", name=" + name
				+ ", call=" + call + ", profileImg=" + profileImg + "]";
	}

}
