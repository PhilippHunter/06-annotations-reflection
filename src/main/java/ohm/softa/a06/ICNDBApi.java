package ohm.softa.a06;

import ohm.softa.a06.model.Joke;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public interface ICNDBApi {
	@GET("joke/random")
	Call<Joke> getRandomJoke();

	@GET("joke/random")
	Call<Joke> getRandomJoke(@Query("limitTo") String[] categoriesToInclude);

	@GET("joke/randomCount/{count}")
	Call<List<Joke>> getRandomJokes(@Path("count") int count);

	@GET("joke/{id}")
	Call<Joke> getJokeById(@Path("id") int number);
}
