package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.model.Joke;
import ohm.softa.a06.model.JokeAdapter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

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
			.baseUrl("http://api.icndb.com")
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		ICNDBApi service = retrofit.create(ICNDBApi.class);

		try {
			Joke j = service.getRandomJoke().execute().body();
			System.out.println(j);
		}
		catch(IOException e) {
			System.out.println("FAIL: " + e.getMessage());
		}
	}

}
