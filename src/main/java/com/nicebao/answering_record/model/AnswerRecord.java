package com.nicebao.answering_record.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @name: AnswerRecord
 * @author: IhaveBB
 * @date: 2024-10-08 21:12
 **/
@AllArgsConstructor
@Data
public class AnswerRecord {
	private String studentId;
	private String studentName;
	private String answerTime;

}
