package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Identificator;

@Component
@Transactional
public class IdentificatorToStringConverter implements Converter<Identificator, String> {

	@Override
	public String convert(Identificator identificator) {
		Assert.notNull(identificator);
		String result;
		result = String.valueOf(identificator.getId());
		return result;
	}

}