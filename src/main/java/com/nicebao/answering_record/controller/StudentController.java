package com.nicebao.answering_record.controller;

import com.nicebao.answering_record.common.ResponseResult;
import com.nicebao.answering_record.dto.RequestData;
import com.nicebao.answering_record.enums.ResultCodeEnum;
import com.nicebao.answering_record.model.ResponseRecord;
import com.nicebao.answering_record.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @name: StudentController
 * @author: IhaveBB
 * @date: 2024-10-09 16:35
 **/
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class StudentController {
	@Autowired
	private StudentService studentService;
	@GetMapping("/getAllStudentsTotalRecordsCSV")
	public ResponseEntity<String> getAllStudensTotalRecordsCSV(@RequestParam String names,@RequestParam String data,@RequestParam String strategyType) {

		String csvData = studentService.getAllStudentsTotalRecordsCSV(names,data,strategyType);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.csv");
		headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");
		return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
	}
	@RequestMapping("/getTotalRecordsJson")
	public ResponseResult<List<ResponseRecord>> getTotalRecordsJson(@RequestParam String data){
		return ResponseResult.success(studentService.getTotalRecordsJson(data));
	}
}
