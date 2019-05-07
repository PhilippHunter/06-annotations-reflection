package ohm.softa.a06.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.ICNDBApi;
import ohm.softa.a06.model.Joke;
import ohm.softa.a06.model.JokeAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
class ICNDBTests {

	private static final Logger logger = LogManager.getLogger(ICNDBTests.class);
	private static final int REQUEST_COUNT = 20;

	private ICNDBApi norrisApi;

	@BeforeEach
	void setup() {
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(Joke.class, new JokeAdapter())
			.create();

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://api.icndb.com")
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();

		norrisApi = retrofit.create(ICNDBApi.class);
	}

	@Test
	void testCollision() throws IOException {
		Set<Integer> jokeNumbers = new HashSet<>();
		int requests = 0;
		boolean collision = false;

		while (requests++ < REQUEST_COUNT) {
			// TODO Prepare call object
			Call<Joke> jokeCall = norrisApi.getRandomJoke();

			// TODO Perform a synchronous request
			Response<Joke> jokeResponse = jokeCall.execute();

			// TODO Extract object
			if(!jokeResponse.isSuccessful()) continue;

			Joke j = jokeResponse.body();

			if(jokeNumbers.contains(j.getNumber())) {
				logger.info(String.format("Collision at joke %s", j.getNumber()));
				collision = true;
				break;
			}

			jokeNumbers.add(j.getNumber());
			logger.info(j.toString());
		}

		assertTrue(collision, String.format("Completed %d requests without collision; consider increasing REQUEST_COUNT", requests));
	}

	@Test
	void howLongTilCollision() throws IOException {
		int counter = 0;
		boolean collision = false;
		List<Integer> jokeIndexes = new LinkedList<>();

		while(!collision) {
			Joke j = norrisApi.getRandomJoke().execute().body();

			if(jokeIndexes.contains(j.getNumber())) {
				collision = true;
				logger.info(j.toString());
				break;
			}

			jokeIndexes.add(j.getNumber());
			counter++;
		}

		logger.info(String.format("Completed %d requests until the first collision appeared", counter));
	}
}
