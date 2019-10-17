package com.feiyangedu.sample;

public class Student {

	private long id;
	private long classId;
	private String name;
	private String gender;

	public Student() {
	}

	Student(long id, long classId, String name, String gender) {
		this.id = id;
		this.classId = classId;
		this.name = name;
		this.gender = gender;
	}

	public Student(long classId, String name, String gender) {
		this.classId = classId;
		this.name = name;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "<Student id=" + id + ", class_id=" + classId + ", name=" + name + ", gender=" + gender + ">";
	}
}
