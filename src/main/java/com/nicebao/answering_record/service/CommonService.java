package com.nicebao.answering_record.service;

import com.nicebao.answering_record.model.AnswerRecord;
import com.nicebao.answering_record.model.ResponseRecord;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: CommonService
 * @author: IhaveBB
 * @date: 2024-10-10 19:53
 **/
@Service
public class CommonService {
	/**
	 * @description: 根据格式化后的数据，解析并返回学生名-抢答总数的键值对
	 * @param: [java.util.List<com.nicebao.answering_record.model.ResponseRecord>]
	 * @return: java.util.Map<java.lang.String,java.lang.Integer>
	 * @author: IhaveBB
	 * @date: 2024/10/9
	 **/
	public Map<String,Integer> countTotalAnswersFromRecords(List<ResponseRecord> responseRecords){
		Map<String, Integer> totalAnswerCountMap = new HashMap<>();
		for (ResponseRecord record : responseRecords) {
			for (AnswerRecord answer : record.getAnswerRecordList()) {
				String studentName = answer.getStudentName();
				totalAnswerCountMap.put(studentName, totalAnswerCountMap.getOrDefault(studentName, 0) + 1);
			}
		}
		return totalAnswerCountMap;
	}

	/**
	 * @description: 根据学生名统计次数
	 * @param: [java.util.List<java.lang.String>]
	 * @return: java.util.Map<java.lang.String,java.lang.Integer>
	 * @author: IhaveBB
	 * @date: 2024/10/9
	 **/
	public Map<String, Integer> countStudentNameOccurrences(List<String> studentNames) {
		Map<String, Integer> occurrenceMap = new HashMap<>();
		for (String name : studentNames) {
			occurrenceMap.put(name, occurrenceMap.getOrDefault(name, 0) + 1);
		}
		return occurrenceMap;
	}

/**
* @description: 构建CSV输出
* @param: [java.util.List<java.lang.String>, java.util.Map<java.lang.String,java.lang.Integer>]
* @return: java.lang.String
* @author: IhaveBB
* @date: 2024/10/09
**/
	public String buildCSVOutput(List<String> studentNames, Map<String, Integer> answerCounts) {
		StringBuilder csvBuilder = new StringBuilder();
		csvBuilder.append("学生名,抢答次数\n");
		for (String studentName : studentNames) {
			Integer count = answerCounts.getOrDefault(studentName.trim(), 0);
			csvBuilder.append(studentName.trim()).append(",").append(count).append("\n");
		}
		return csvBuilder.toString();
	}

	/**
	 * @description: 获取预处理的抢答数据
	 * @param: [java.lang.String]
	 * @return: java.util.List<com.nicebao.answering_record.model.ResponseRecord>
	 * @author: IhaveBB
	 * @date: 2024/10/9
	 **/
	public List<ResponseRecord> getResponseRecords(String data){
		List<ResponseRecord> responseRecords = new ArrayList<>();
		Pattern pattern = Pattern.compile("(\\d{4}年\\d{2}月\\d{2}日 \\d{2}：\\d{2}) (\\d+)人");
		Matcher matcher = pattern.matcher(data);

		int searchIndex = 0;

		//matcher匹配的是抢答的开始时间戳和人数
		while(matcher.find(searchIndex)){
			String timestamp = matcher.group(1);
			int participantsCount = Integer.parseInt(matcher.group(2));
			List<AnswerRecord> answerRecordList = new ArrayList<>();

			// 记录当前时间段的结束位置
			int startIndex = matcher.end();
			// 查找下一个时间段的开始位置，避免影响外层循环
			int endIndex;
			if (matcher.find(startIndex)) {
				// 下一个时间段的开始位置
				endIndex = matcher.start();
			} else {
				// 没有下一个时间段，取数据末尾
				endIndex = data.length();
			}

			// 截取当前时间段内的数据
			String recordSection = data.substring(startIndex, endIndex);

			Pattern participantPattern = Pattern.compile("(\\d+)\\s*([\\u4e00-\\u9fa5]+)\\s*(\\d+)\\s*([\\d\\- :]+)");
			Matcher participantMatcher = participantPattern.matcher(recordSection);

			// 从抢答标志的结束位置开始查找
			int participantIndex = 0;
			while (participantMatcher.find(participantIndex)) {
				String studentId = participantMatcher.group(3);
				String name = participantMatcher.group(2);
				String responseTime = participantMatcher.group(4);
				answerRecordList.add(new AnswerRecord(studentId, name, responseTime));
				// 更新位置
				participantIndex = participantMatcher.end();
			};
			// 更新 searchIndex 为当前时间段的结束位置，继续下一个匹配
			searchIndex = endIndex;

			// 如果抢答人数＞上方人数，则只保留规定人数的数据
			if (answerRecordList.size() > participantsCount) {
				// 根据抢答时间进行排序
				answerRecordList.sort(Comparator.comparing(AnswerRecord::getAnswerTime));
				answerRecordList = answerRecordList.subList(0, participantsCount);
			}

			responseRecords.add(new ResponseRecord(timestamp, participantsCount,answerRecordList));
		}
		return responseRecords;
	}
}
