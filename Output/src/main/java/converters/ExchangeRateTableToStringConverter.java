package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ExchangeRateTable;

@Component
@Transactional
public class ExchangeRateTableToStringConverter implements Converter<ExchangeRateTable, String> {

	@Override
	public String convert(ExchangeRateTable exchangeRateTable) {
		Assert.notNull(exchangeRateTable);
		String result;
		result = String.valueOf(exchangeRateTable.getId());
		return result;
	}

}