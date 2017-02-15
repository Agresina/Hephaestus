package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.${class.name};

@Component
@Transactional
public class ${class.name}ToStringConverter implements Converter<${class.name}, String> {

	@Override
	public String convert(${class.name} ${class.name?uncap_first}) {
		Assert.notNull(${class.name?uncap_first});
		String result;
		result = String.valueOf(${class.name?uncap_first}.getId());
		return result;
	}

}