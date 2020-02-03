package com.example.themoviedbpopularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.themoviedbpopularmovies.model.MovieDbModel;
import com.example.themoviedbpopularmovies.model.Result;
import com.example.themoviedbpopularmovies.network.Instances;
import com.example.themoviedbpopularmovies.recycle_view.MovieDbAdapter;
import com.example.themoviedbpopularmovies.recycle_view.OnRecycleItemClicked;

import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db);

        setTitle(getString(R.string.poupular_movies));

        Call<MovieDbModel> call = Instances.getClient().getPopularMovies(Constants.API_KEY);

        call.enqueue(new Callback<MovieDbModel>() {
            @Override
            public void onResponse(Call<MovieDbModel> call, Response<MovieDbModel> response) {
                if(response.isSuccessful()) {
                    RecyclerView recyclerView = findViewById(R.id.rv_movie_db);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    MovieDbAdapter movieDbAdapter = new MovieDbAdapter(response.body(), new OnRecycleItemClicked() {
                        @Override
                        public void onRecycleItemClicked(Result movieDbModel) {
                            Intent intent = new Intent(MovieDbActivity.this, MovieDbDetailActivity.class);
                            intent.putExtra(Constants.INTENTS_KEY, movieDbModel.getId());
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(movieDbAdapter);
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.not_sucsess_error),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieDbModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error)+
                                t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
