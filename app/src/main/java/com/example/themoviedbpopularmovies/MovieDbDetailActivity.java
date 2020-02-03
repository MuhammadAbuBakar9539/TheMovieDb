package com.example.themoviedbpopularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedbpopularmovies.model.MovieDbDetailModel;
import com.example.themoviedbpopularmovies.model.Result;
import com.example.themoviedbpopularmovies.network.Instances;
import com.example.themoviedbpopularmovies.recycle_view.OnRecycleItemClicked;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDbDetailActivity extends AppCompatActivity {
    private String id;
    private TextView tv_title, tv_overview;
    private ImageView img_poster;
    private RatingBar rb_vote_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db_detail);
        tv_title = findViewById(R.id.tv_title_detail);
        tv_overview = findViewById(R.id.tv_overview_detail);
        img_poster = findViewById(R.id.img_poster_detail);
        rb_vote_average = findViewById(R.id.rb_vote_average_detail);
        id = String.valueOf(getIntent().getIntExtra(Constants.INTENTS_KEY, 0));

        setTitle(getString(R.string.movie_detail));

        Call<MovieDbDetailModel> call = Instances.getClient().getMovieDetails(id ,Constants.API_KEY);
        call.enqueue(new Callback<MovieDbDetailModel>() {
            @Override
            public void onResponse(Call<MovieDbDetailModel> call, Response<MovieDbDetailModel> response) {
                if(response.isSuccessful()) {
                    MovieDbDetailModel movieDbDetailModel = response.body();
                    tv_title.setText(movieDbDetailModel.getTitle());
                    tv_overview.setText(movieDbDetailModel.getOverview());
                    Picasso.get().load(Constants.DETAIL_IMG_BASE_URL + movieDbDetailModel.getPosterPath())
                            .into(img_poster);
                    rb_vote_average.setRating((float) (movieDbDetailModel.getVoteAverage() / 2));
                }
            }

            @Override
            public void onFailure(Call<MovieDbDetailModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error)+
                        t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
