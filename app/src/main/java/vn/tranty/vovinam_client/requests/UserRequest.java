package vn.tranty.vovinam_client.requests;

import retrofit2.Call;
import retrofit2.Callback;
import vn.tranty.vovinam_client.abtracts.AbstractResponse;
import vn.tranty.vovinam_client.interfaces.Requests;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.models.ResponseVO;
import vn.tranty.vovinam_client.models.users.UserResponseVO;
import vn.tranty.vovinam_client.models.users.UsersResponseVO;

/**
 * Created by TRUC-SIDA on 10/24/2017.
 */

public class UserRequest {
    public static void login(String username, String password, final Response resp) {
        resp.onStart();
        Requests client = HandlerRequest.createService(Requests.class);
        Call<UserResponseVO> call = client.login(username, password);
        call.enqueue(new Callback<UserResponseVO>() {
            @Override
            public void onResponse(Call<UserResponseVO> call, retrofit2.Response<UserResponseVO> response) {
                resp.onSuccess(response.body().erorr_code, response.body().message, response.body().data);
            }
            @Override
            public void onFailure(Call<UserResponseVO> call, Throwable t) {
                resp.onFailure();
            }
        });
    }

    public static void changePassword(String username, String password, String passwordNew, final Response resp) {
        resp.onStart();
        Requests client = HandlerRequest.createService(Requests.class);
        Call<ResponseVO> call = client.changePassword(username, password, passwordNew);
        call.enqueue(new Callback<ResponseVO>() {
            @Override
            public void onResponse(Call<ResponseVO> call, retrofit2.Response<ResponseVO> response) {
                resp.onSuccess(response.body().erorr_code, response.body().message, response.body());
            }
            @Override
            public void onFailure(Call<ResponseVO> call, Throwable t) {
                resp.onFailure();
            }
        });
    }

    public static void getUsers(int company_id, final AbstractResponse resp) {
        resp.onStart();
        Requests client = HandlerRequest.createService(Requests.class);
        Call<UsersResponseVO> call = client.getUsers(company_id);
        call.enqueue(new Callback<UsersResponseVO>() {
            @Override
            public void onResponse(Call<UsersResponseVO> call, retrofit2.Response<UsersResponseVO> response) {
                resp.onSuccess(response.body().erorr_code, response.body().message, response.body().data);
            }
            @Override
            public void onFailure(Call<UsersResponseVO> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
