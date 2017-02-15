package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.Subsystem0Repository;
import domain.Subsystem0;

@Component
@Transactional
public class StringToSubsystem0Converter implements Converter<String, Subsystem0> {

	@Autowired
	Subsystem0Repository subsystem0Repository;

	@Override
	public Subsystem0 convert(String text) {
		Subsystem0 result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = subsystem0Repository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
