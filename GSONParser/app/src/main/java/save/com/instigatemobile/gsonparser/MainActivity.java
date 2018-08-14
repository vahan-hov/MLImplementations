package save.com.instigatemobile.gsonparser;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import save.com.instigatemobile.gsonparser.adapters.MultiViewTypeAdapter;
import save.com.instigatemobile.gsonparser.api.ApiResponse;
import save.com.instigatemobile.gsonparser.api.Result;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
        client.fetchUsers(10).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                assert response.body() != null;
                List<Result> mlist = Objects.requireNonNull(response.body()).getResults();
                final MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(mlist, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Log.e(getString(R.string.main), t.toString());
            }
        });
    }

}
