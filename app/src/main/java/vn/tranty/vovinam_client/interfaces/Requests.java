package vn.tranty.vovinam_client.interfaces;

import retrofit2.Call;

import retrofit2.http.POST;

import retrofit2.http.Query;
import vn.tranty.vovinam_client.models.users.UserResponse;

public interface Requests {

    @POST("User/Login")
    Call<UserResponse> login(@Query("user_name") String userName, @Query("password") String password);

}
