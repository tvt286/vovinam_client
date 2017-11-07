package vn.tranty.vovinam_client.requests;

import retrofit2.Call;
import retrofit2.Callback;
import vn.tranty.vovinam_client.interfaces.Requests;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.models.examinations.ExaminationResponseVO;

/**
 * Created by TRUC-SIDA on 11/2/2017.
 */

public class ExaminationRequest
{
    public static void getExaminations( int companyId, final Response resp) {
        resp.onStart();
        Requests client = HandlerRequest.createService(Requests.class);
        Call<ExaminationResponseVO> call = client.getExaminations(companyId);
        call.enqueue(new Callback<ExaminationResponseVO>() {
            @Override
            public void onResponse(Call<ExaminationResponseVO> call, retrofit2.Response<ExaminationResponseVO> response) {
                resp.onSuccess(response.body().erorr_code, response.body().message, response.body().data);
            }
            @Override
            public void onFailure(Call<ExaminationResponseVO> call, Throwable t) {
                resp.onFailure();
            }
        });
    }

}
