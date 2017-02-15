package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.Message FolderRepository;
import domain.Message Folder;

@Component
@Transactional
public class StringToMessage FolderConverter implements Converter<String, Message Folder> {

	@Autowired
	Message FolderRepository message FolderRepository;

	@Override
	public Message Folder convert(String text) {
		Message Folder result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = message FolderRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
