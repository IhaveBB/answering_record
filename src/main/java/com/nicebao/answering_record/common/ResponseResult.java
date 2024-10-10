package com.nicebao.answering_record.common;


import com.nicebao.answering_record.enums.BaseErrorInfoInterface;
import com.nicebao.answering_record.enums.ResultCodeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 统一结果返回
 *
 * @name: ResponseResult
 * @author: IhaveBB
 * @date: 2024-08-13 18:10
 **/
@Data
@Builder
public class ResponseResult<T> {
	private Integer code;
	private long timestamp;
	private String msg;
	private T data;

	public static <T> ResponseResult<T> success(T data) {
		return ResponseResult.<T>builder()
				.code(ResultCodeEnum.Success.getCode())
				.msg(ResultCodeEnum.Success.getMessage())
				.timestamp(System.currentTimeMillis())
				.data(data)
				.build();
	}
	public static <T> ResponseResult<T> fail(BaseErrorInfoInterface baseErrorInfoInterface) {
		return ResponseResult.<T>builder()
				.code(baseErrorInfoInterface.getCode())
				.msg(baseErrorInfoInterface.getMessage())
				.timestamp(System.currentTimeMillis())
				.data(null)
				.build();
	}



}
