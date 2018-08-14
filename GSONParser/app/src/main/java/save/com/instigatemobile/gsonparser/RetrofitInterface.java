package save.com.instigatemobile.gsonparser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import save.com.instigatemobile.gsonparser.api.ApiResponse;

public interface RetrofitInterface {
    @GET("/api/")
    Call<ApiResponse> fetchUsers(@Query("results") int results);
}

