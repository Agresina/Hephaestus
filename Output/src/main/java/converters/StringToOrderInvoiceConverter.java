package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.OrderInvoiceRepository;
import domain.OrderInvoice;

@Component
@Transactional
public class StringToOrderInvoiceConverter implements Converter<String, OrderInvoice> {

	@Autowired
	OrderInvoiceRepository orderInvoiceRepository;

	@Override
	public OrderInvoice convert(String text) {
		OrderInvoice result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = orderInvoiceRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
