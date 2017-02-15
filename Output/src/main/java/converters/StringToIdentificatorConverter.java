package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.IdentificatorRepository;
import domain.Identificator;

@Component
@Transactional
public class StringToIdentificatorConverter implements Converter<String, Identificator> {

	@Autowired
	IdentificatorRepository identificatorRepository;

	@Override
	public Identificator convert(String text) {
		Identificator result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = identificatorRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
