package vn.tranty.vovinam_client.requests;

import retrofit2.Call;
import retrofit2.Callback;
import vn.tranty.vovinam_client.interfaces.Requests;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.models.chamthi.LevelUpRespone;
import vn.tranty.vovinam_client.models.examinations.ExaminationModel;
import vn.tranty.vovinam_client.models.examinations.ExaminationResponse;

/**
 * Created by TRUC-SIDA on 11/2/2017.
 */

public class ExaminationRequest
{
    public static void getExaminations( int companyId, final Response resp) {
        resp.onStart();
        Requests client = HandlerRequest.createService(Requests.class);
        Call<ExaminationResponse> call = client.getExaminations(companyId);
        call.enqueue(new Callback<ExaminationResponse>() {
            @Override
            public void onResponse(Call<ExaminationResponse> call, retrofit2.Response<ExaminationResponse> response) {
                resp.onSuccess(response.body().erorr_code, response.body().message, response.body().data);
            }
            @Override
            public void onFailure(Call<ExaminationResponse> call, Throwable t) {
                resp.onFailure();
            }
        });
    }

}
