package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Tax;

@Component
@Transactional
public class TaxToStringConverter implements Converter<Tax, String> {

	@Override
	public String convert(Tax tax) {
		Assert.notNull(tax);
		String result;
		result = String.valueOf(tax.getId());
		return result;
	}

}