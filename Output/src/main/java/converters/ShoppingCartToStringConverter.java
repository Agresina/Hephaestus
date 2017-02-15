package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ShoppingCart;

@Component
@Transactional
public class ShoppingCartToStringConverter implements Converter<ShoppingCart, String> {

	@Override
	public String convert(ShoppingCart shoppingCart) {
		Assert.notNull(shoppingCart);
		String result;
		result = String.valueOf(shoppingCart.getId());
		return result;
	}

}