package com.nicebao.answering_record.model;

import java.util.List;

/**
 * @name: ResponseRecord
 * @author: IhaveBB
 * @date: 2024-10-08 22:54
 **/
public class ResponseRecord {
	private String timestamp;
	private int participantsCount;
	private List<AnswerRecord> answerRecordList;

	public ResponseRecord(List<AnswerRecord> answerRecordList, int participantsCount, String timestamp) {
		this.answerRecordList = answerRecordList;
		this.participantsCount = participantsCount;
		this.timestamp = timestamp;
	}

	public List<AnswerRecord> getAnswerRecordList() {
		return answerRecordList;
	}

	public void setAnswerRecordList(List<AnswerRecord> answerRecordList) {
		this.answerRecordList = answerRecordList;
	}

	public int getParticipantsCount() {
		return participantsCount;
	}

	public void setParticipantsCount(int participantsCount) {
		this.participantsCount = participantsCount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
