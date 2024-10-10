package com.nicebao.answering_record.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicebao.answering_record.factory.AnswerCountStrategyFactory;
import com.nicebao.answering_record.model.ResponseRecord;
import com.nicebao.answering_record.strategy.AnswerCountStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @name: StudentService
 * @author: IhaveBB
 * @date: 2024-10-09 16:35
 **/
@Service
@Slf4j
public class StudentService {
	@Autowired
	private AnswerCountStrategyFactory answerCountStrategyFactory;

	@Autowired
	private CommonService commonService;

	public String getAllStudentsTotalRecordsCSV(String names, String data, String type) {
		AnswerCountStrategy strategy = answerCountStrategyFactory.getStrategy(type);
		return strategy.countAnswers(names,data);
	}

	public List<ResponseRecord> getTotalRecordsJson(String data){
		return commonService.getResponseRecords(data);
	}
}
