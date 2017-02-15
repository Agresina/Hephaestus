package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.${class.name}Repository;
import domain.${class.name};

@Component
@Transactional
public class StringTo${class.name}Converter implements Converter<String, ${class.name}> {

	@Autowired
	${class.name}Repository ${class.name?uncap_first}Repository;

	@Override
	public ${class.name} convert(String text) {
		${class.name} result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = ${class.name?uncap_first}Repository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
