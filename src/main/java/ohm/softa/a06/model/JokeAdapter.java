package ohm.softa.a06.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class JokeAdapter extends TypeAdapter<Joke> {

	@Override
	public void write(JsonWriter out, Joke value) throws IOException {

	}

	@Override
	public Joke read(JsonReader in) throws IOException {
		Joke joke = null;
		final Gson gson = new Gson();

		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
				case "type":
					if (!in.nextString().equals("success"))
						throw new IOException("No Joke found");
					break;
				case "value":
					joke = gson.fromJson(in, Joke.class);
					break;
			}
		}
		in.endObject();

		return joke;
	}
}
