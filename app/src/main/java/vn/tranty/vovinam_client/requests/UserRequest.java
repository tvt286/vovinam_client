package vn.tranty.vovinam_client.requests;

import retrofit2.Call;
import retrofit2.Callback;
import vn.tranty.vovinam_client.interfaces.Requests;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.models.users.UserResponseVO;

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
}
