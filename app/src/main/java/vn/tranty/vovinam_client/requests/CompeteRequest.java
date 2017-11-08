package vn.tranty.vovinam_client.requests;

import retrofit2.Call;
import retrofit2.Callback;
import vn.tranty.vovinam_client.interfaces.Requests;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.models.chamthi.CompeteRespone;
import vn.tranty.vovinam_client.models.chamthi.LevelUpRespone;

/**
 * Created by TRUC-SIDA on 11/8/2017.
 */

public class CompeteRequest {

    public static void getCompetes(int examinationId, int companyId, int gender, final Response resp) {
        resp.onStart();
        Requests client = HandlerRequest.createService(Requests.class);
        Call<CompeteRespone> call = client.getCompetes(examinationId, companyId, gender);
        call.enqueue(new Callback<CompeteRespone>() {
            @Override
            public void onResponse(Call<CompeteRespone> call, retrofit2.Response<CompeteRespone> response) {
                resp.onSuccess(response.body().erorr_code, response.body().message, response.body().data);
            }
            @Override
            public void onFailure(Call<CompeteRespone> call, Throwable t) {
                resp.onFailure();
            }
        });
    }
}
