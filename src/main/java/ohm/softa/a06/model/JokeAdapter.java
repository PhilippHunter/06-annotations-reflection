package ohm.softa.a06.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class JokeAdapter<T> extends TypeAdapter<T> {

	@Override
	public void write(JsonWriter out, T value) throws IOException {

	}

	@Override
	public T read(JsonReader in) throws IOException {
		Joke joke = new Joke();
		final Gson gson = new Gson();

		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
				case "type":
					if (!in.nextString().equals("success"))
						throw new IllegalArgumentException("No Joke found");
					else
						break;
				case "value":
					joke = gson.fromJson(in.nextString(), Joke.class);
					break;
			}
		}
		in.endObject();

		return joke;
	}
}
