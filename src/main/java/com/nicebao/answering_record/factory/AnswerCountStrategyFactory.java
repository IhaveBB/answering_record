package com.nicebao.answering_record.factory;

import com.nicebao.answering_record.strategy.AnswerCountStrategy;
import com.nicebao.answering_record.strategy.LooseFormatStrategy;
import com.nicebao.answering_record.strategy.StrictFormatStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @name: AnswerCountStrategyFactory
 * @author: IhaveBB
 * @date: 2024-10-10 10:07
 **/
@Component
public class AnswerCountStrategyFactory {
	@Autowired
	private LooseFormatStrategy looseFormatStrategy;
	@Autowired
	private StrictFormatStrategy strictFormatStrategy;

	public AnswerCountStrategy getStrategy(String strategyType) {
		if ("strict".equalsIgnoreCase(strategyType)) {
			return strictFormatStrategy;
		}{
			return looseFormatStrategy;
		}
	}
}
