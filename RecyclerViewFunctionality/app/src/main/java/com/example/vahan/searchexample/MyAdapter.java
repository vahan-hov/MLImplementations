package com.example.vahan.searchexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserModel> implements Filterable {
    public static final String MY_KEY = "1";
    public static final String MY_KEY2 = "2";
    public static final String MY_KEY3 = "3";
    public static final String MY_KEY4 = "4";
    public static final String MY_KEY5 = "5";
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
        private ImageView imageViewEmail;
        private ImageView imageViewPhone;
        private TextView userEmail;
        private ImageView userDelete;
        private ImageView userAdd;

        public UserModel(View view) {
            super(view);
            userImage = view.findViewById(R.id.userImage);
            name = view.findViewById(R.id.userName);
            imageViewEmail = view.findViewById(R.id.userEmailImg);
            userEmail = view.findViewById(R.id.userEmailAddress);
            imageViewPhone = view.findViewById(R.id.userCallImg);
            userDelete = view.findViewById(R.id.userDeleteImg);
            userAdd = view.findViewById(R.id.userAddImg);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = filteredUsers.get(getAdapterPosition()).getmImageUrl();
                    String title = filteredUsers.get(getAdapterPosition()).getmName();
                    String email = filteredUsers.get(getAdapterPosition()).getEmail();
                    String number = filteredUsers.get(getAdapterPosition()).getPhoneNumber();
                    String description = filteredUsers.get(getAdapterPosition()).getDescription();
                    Intent intent = new Intent(context, ScrollingActivity.class);
                    intent.putExtra(MY_KEY, url);
                    intent.putExtra(MY_KEY2, title);
                    intent.putExtra(MY_KEY3, email);
                    intent.putExtra(MY_KEY4, number);
                    intent.putExtra(MY_KEY5, description);
                    context.startActivity(intent);
                }
            });

            userDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    filteredUsers.remove(position);
                    notifyItemRemoved(position);
                    Toast toast = Toast.makeText(context, R.string.deleted, Toast.LENGTH_LONG);
                    toast.show();
                }
            });

            userAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    users.add(new User("https://lh3.googleusercontent.com/-iD3H7SA50ns/WzNGkaFd7OI/AAAAAAAAAIE/_IotfecITz4jifZuT-2SnxBq6GvCa2SSQCEwYBhgL/w139-h140-p/f3477ef4-c2a3-4580-a2df-205e4da8de73", "Vahan", "vahan.hovhannisyan.1997@gmail.com", "055751551", "Baelish\">Lord Petyr Baelish, popularly called Littlefinger, was the Master of Coin on the Small Council under King Robert Baratheon and King Joffrey Baratheon. He was a skilled manipulator and used his ownership of brothels in King\\'s Landing to both accrue intelligence on political rivals and acquire vast wealth. Baelish\\'s spy network is eclipsed only by that of his arch-rival Varys."));
                    notifyItemInserted(position);
                    Toast toast = Toast.makeText(context, R.string.added, Toast.LENGTH_LONG);
                    toast.show();
                }
            });


            imageViewPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phoneNumber = filteredUsers.get(getAdapterPosition()).getPhoneNumber();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    context.startActivity(intent);
                }
            });

            imageViewEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = String.valueOf(userEmail.getText());
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject"); // Whatever subject
                    emailIntent.putExtra(Intent.EXTRA_TEXT, email);
                    context.startActivity(Intent.createChooser(emailIntent, "Send email"));
                }
            });
        }
    }
}
