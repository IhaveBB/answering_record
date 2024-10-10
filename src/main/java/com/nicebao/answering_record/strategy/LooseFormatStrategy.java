package com.nicebao.answering_record.strategy;

import com.nicebao.answering_record.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @name: LooseFormatStrategy
 * @author: IhaveBB
 * @date: 2024-10-10 10:05
 **/
@Component
public class LooseFormatStrategy implements AnswerCountStrategy{
	@Autowired
	private CommonService commonService;

	@Override
	public String countAnswers(String names, String data) {
		List<String> studentNames = Arrays.asList(names.split("[\\s\\r?\\n]+"));
		List<String> answerStudentNames = Arrays.asList(data.split("[\\s\\r?\\n]+"));
		Map<String, Integer> answerCounts = commonService.countStudentNameOccurrences(answerStudentNames);
		return commonService.buildCSVOutput(studentNames, answerCounts);
	}
}
