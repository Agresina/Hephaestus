package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Supermarket;

@Component
@Transactional
public class SupermarketToStringConverter implements Converter<Supermarket, String> {

	@Override
	public String convert(Supermarket supermarket) {
		Assert.notNull(supermarket);
		String result;
		result = String.valueOf(supermarket.getId());
		return result;
	}

}