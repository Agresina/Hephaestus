package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Clerk;

@Component
@Transactional
public class ClerkToStringConverter implements Converter<Clerk, String> {

	@Override
	public String convert(Clerk clerk) {
		Assert.notNull(clerk);
		String result;
		result = String.valueOf(clerk.getId());
		return result;
	}

}