package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ExchangeRateTableRepository;
import domain.ExchangeRateTable;

@Component
@Transactional
public class StringToExchangeRateTableConverter implements Converter<String, ExchangeRateTable> {

	@Autowired
	ExchangeRateTableRepository exchangeRateTableRepository;

	@Override
	public ExchangeRateTable convert(String text) {
		ExchangeRateTable result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = exchangeRateTableRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}