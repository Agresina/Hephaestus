package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.OrderLine;

@Component
@Transactional
public class OrderLineToStringConverter implements Converter<OrderLine, String> {

	@Override
	public String convert(OrderLine orderLine) {
		Assert.notNull(orderLine);
		String result;
		result = String.valueOf(orderLine.getId());
		return result;
	}

}