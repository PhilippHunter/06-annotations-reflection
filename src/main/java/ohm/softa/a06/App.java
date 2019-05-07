package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.model.Joke;
import ohm.softa.a06.model.JokeAdapter;
import retrofit2.Retrofit;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	public static void main(String[] args) {
		// TODO fetch a random joke and print it to STDOUT
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Joke.class, new JokeAdapter());

		final Gson gson = gsonBuilder.create();

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://api.github.com/")
			.build();

		ICNDBApi service = retrofit.create(ICNDBApi.class);
	}

}
