package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;

public class Translator {

	private String langFrom;
	private String langTo;
	
	public Translator(String langFrom, String langTo) {
		this.langFrom = langFrom;
		this.langTo = langTo;
	}

	public String translate(final String word) throws Exception {

		String url = "https://translate.googleapis.com/translate_a/single?" + "client=gtx&" + "sl=" + this.langFrom + "&tl="
				+ this.langTo + "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return parseResult(response.toString());
	}
	
	public Collection<String> translate(Collection<String> words) throws Exception  {
		ArrayList<String> result = new ArrayList<String>();
		for(String word:words) {
			result.add(this.translate(word));
		}
		return result;
	}

	private String parseResult(String inputJson) throws Exception {
		/*
		 * inputJson for word 'hello' translated to language Hindi from English-
		 * [[["नमस्ते","hello",,,1]],,"en"] We have to get 'नमस्ते ' from this
		 * json.
		 */

		JSONArray jsonArray = new JSONArray(inputJson);
		JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
		JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);

		return jsonArray3.get(0).toString();
	}
}