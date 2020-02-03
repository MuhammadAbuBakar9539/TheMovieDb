package com.example.themoviedbpopularmovies.recycle_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedbpopularmovies.Constants;
import com.example.themoviedbpopularmovies.R;
import com.example.themoviedbpopularmovies.model.MovieDbModel;
import com.example.themoviedbpopularmovies.model.Result;
import com.squareup.picasso.Picasso;

public class MovieDbAdapter extends RecyclerView.Adapter<MovieDbAdapter.myViewHolder> {

    private MovieDbModel movieDbModel;
    private OnRecycleItemClicked onRecycleItemClicked;

    public MovieDbAdapter(MovieDbModel movieDbModel, OnRecycleItemClicked onRecycleItemClicked) {
        this.movieDbModel = movieDbModel;
        this.onRecycleItemClicked = onRecycleItemClicked;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.movie_db_layout, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.title.setText(movieDbModel.getResults().get(position).getTitle());
        holder.vote_average.setRating((float) (movieDbModel.getResults().get(position).getVoteAverage()/2));
        Picasso.get().load(Constants.IMG_BASE_URL + movieDbModel.getResults().
                get(position).getPosterPath()).into(holder.poster);

        holder.bind(movieDbModel.getResults().get(position));

    }

    @Override
    public int getItemCount() {
        return movieDbModel.getResults().size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView title;
        private RatingBar vote_average;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.img_poster);
            title = itemView.findViewById(R.id.tv_title);
            vote_average = itemView.findViewById(R.id.rb_vote_average);

        }
        public void bind(Result movieDbModel){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecycleItemClicked.onRecycleItemClicked(movieDbModel);
                }
            });
        }
    }
}
