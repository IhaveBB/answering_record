package com.nicebao.answering_record.model;

/**
 * @name: AnswerRecord
 * @author: IhaveBB
 * @date: 2024-10-08 21:12
 **/
public class AnswerRecord {
	private String studentId;
	private String studentName;
	private String answerTime;

	public AnswerRecord(String studentName, String studentId, String answerTime) {
		this.answerTime = answerTime;
		this.studentId = studentId;
		this.studentName = studentName;
	}

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


}
