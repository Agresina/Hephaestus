package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Warehouse;

@Component
@Transactional
public class WarehouseToStringConverter implements Converter<Warehouse, String> {

	@Override
	public String convert(Warehouse warehouse) {
		Assert.notNull(warehouse);
		String result;
		result = String.valueOf(warehouse.getId());
		return result;
	}

}