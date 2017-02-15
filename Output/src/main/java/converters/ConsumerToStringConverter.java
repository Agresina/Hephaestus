package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Consumer;

@Component
@Transactional
public class ConsumerToStringConverter implements Converter<Consumer, String> {

	@Override
	public String convert(Consumer consumer) {
		Assert.notNull(consumer);
		String result;
		result = String.valueOf(consumer.getId());
		return result;
	}

}