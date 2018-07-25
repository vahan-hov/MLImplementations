package com.example.vahan.movie5;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_movie_list_row, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.title.setText(movieList.get(position).getTitle());
        holder.ratingBar.setRating(movieList.get(position).getRating());
        holder.image.setImageResource(movieList.get(position).getImage());
        holder.description.setText(movieList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public RatingBar ratingBar;
        public TextView description;
        public ImageView image;
        public ImageButton Like;

        public MovieViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            ratingBar = view.findViewById(R.id.Rating);
            description = view.findViewById(R.id.Description);
            image = view.findViewById(R.id.My_Image);

            /*final ImageButton btnTest = view.findViewById(R.id.Like);
            btnTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnTest.setSelected(!Like.isPressed());

                    if (btnTest.isPressed()) {
                        Like.setImageResource(R.drawable.liked);
                    }
                    else {
                        btnTest.setImageResource(R.drawable.unliked);
                    }
                }
            }); */
        }
    }
}
