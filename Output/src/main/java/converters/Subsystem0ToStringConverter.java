package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Subsystem0;

@Component
@Transactional
public class Subsystem0ToStringConverter implements Converter<Subsystem0, String> {

	@Override
	public String convert(Subsystem0 subsystem0) {
		Assert.notNull(subsystem0);
		String result;
		result = String.valueOf(subsystem0.getId());
		return result;
	}

}