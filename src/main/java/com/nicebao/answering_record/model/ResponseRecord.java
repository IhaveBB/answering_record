package com.nicebao.answering_record.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @name: ResponseRecord
 * @author: IhaveBB
 * @date: 2024-10-08 22:54
 **/
@AllArgsConstructor
@Data
public class ResponseRecord {
	private String timestamp;
	private int participantsCount;
	private List<AnswerRecord> answerRecordList;

}
