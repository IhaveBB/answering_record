package com.nicebao.answering_record.strategy;

import com.nicebao.answering_record.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @name: StrictFormatStrategy
 * @author: IhaveBB
 * @date: 2024-10-10 10:01
 **/
@Component
public class StrictFormatStrategy implements AnswerCountStrategy{
	@Autowired
	private CommonService commonService;

	@Override
	public String countAnswers(String names, String data) {
		List<String> studentNames = Arrays.asList(names.split("[\\s\\r?\\n]+"));
		Map<String, Integer> answerCounts = commonService.countTotalAnswersFromRecords(commonService.getResponseRecords(data));
		return commonService.buildCSVOutput(studentNames, answerCounts);
	}
}
