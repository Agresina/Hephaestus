package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Message Folder;

@Component
@Transactional
public class Message FolderToStringConverter implements Converter<Message Folder, String> {

	@Override
	public String convert(Message Folder message Folder) {
		Assert.notNull(message Folder);
		String result;
		result = String.valueOf(message Folder.getId());
		return result;
	}

}