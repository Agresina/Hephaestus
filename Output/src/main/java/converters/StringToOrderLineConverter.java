package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.OrderLineRepository;
import domain.OrderLine;

@Component
@Transactional
public class StringToOrderLineConverter implements Converter<String, OrderLine> {

	@Autowired
	OrderLineRepository orderLineRepository;

	@Override
	public OrderLine convert(String text) {
		OrderLine result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = orderLineRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
