package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.OrderInvoice;

@Component
@Transactional
public class OrderInvoiceToStringConverter implements Converter<OrderInvoice, String> {

	@Override
	public String convert(OrderInvoice orderInvoice) {
		Assert.notNull(orderInvoice);
		String result;
		result = String.valueOf(orderInvoice.getId());
		return result;
	}

}