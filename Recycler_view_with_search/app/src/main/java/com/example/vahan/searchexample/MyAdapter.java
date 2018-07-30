package com.example.vahan.searchexample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserModel> implements Filterable {
    public static final String MY_KEY = "com.example.vahan.elya";
    private List<User> users;
    private Context context;
    private List<User> filteredUsers;

    public MyAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        this.filteredUsers = users;

    }

    @NonNull
    @Override
    public UserModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_model, viewGroup, false);
        return new UserModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserModel userModel, int position) {
        User user = filteredUsers.get(position);
        Picasso.get().load(user.getmImageUrl()).into(userModel.userImage);
        userModel.name.setText(user.getmName());

    }

    @Override
    public int getItemCount() {
        return filteredUsers.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredUsers = users;
                } else {
                    List<User> fiteredUsers2 = new ArrayList<>();
                    for (User row : users) {
                        if (row.getmName().toLowerCase().contains(charString.toLowerCase())) {
                            fiteredUsers2.add(row);
                        }
                    }

                    filteredUsers = fiteredUsers2;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUsers;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredUsers = (List<User>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public class UserModel extends RecyclerView.ViewHolder {
        private CircleImageView userImage;
        private TextView name;

        public UserModel(View view) {
            super(view);
            userImage = view.findViewById(R.id.userImage);
            name = view.findViewById(R.id.userName);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String url = filteredUsers.get(getAdapterPosition()).getmImageUrl();
                    Intent intent = new Intent(context, ScrollingActivity.class);
                    intent.putExtra(MY_KEY, url);
                    context.startActivity(intent);
                }
            });
        }
    }
}
