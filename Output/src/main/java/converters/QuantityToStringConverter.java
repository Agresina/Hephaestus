package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Quantity;

@Component
@Transactional
public class QuantityToStringConverter implements Converter<Quantity, String> {

	@Override
	public String convert(Quantity quantity) {
		Assert.notNull(quantity);
		String result;
		result = String.valueOf(quantity.getId());
		return result;
	}

}