package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Stock;

@Component
@Transactional
public class StockToStringConverter implements Converter<Stock, String> {

	@Override
	public String convert(Stock stock) {
		Assert.notNull(stock);
		String result;
		result = String.valueOf(stock.getId());
		return result;
	}

}