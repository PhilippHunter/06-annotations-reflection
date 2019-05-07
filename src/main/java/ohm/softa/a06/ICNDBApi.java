package ohm.softa.a06;

import ohm.softa.a06.model.Joke;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public interface ICNDBApi {
	@GET
	Call<Joke> getRandomJoke();

	@GET
	Call<Joke> getRandomJoke(String[] categoriesToInclude);

	@GET
	Call<List<Joke>> getRandomJokes(int count);

	@GET
	Call<Joke> getJokeById(int id);
}
