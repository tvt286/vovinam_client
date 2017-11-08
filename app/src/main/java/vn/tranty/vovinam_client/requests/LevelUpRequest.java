package vn.tranty.vovinam_client.requests;

import retrofit2.Call;
import retrofit2.Callback;
import vn.tranty.vovinam_client.interfaces.Requests;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.models.ResponseVO;
import vn.tranty.vovinam_client.models.chamthi.LevelUpRespone;

/**
 * Created by TRUC-SIDA on 11/2/2017.
 */

public class LevelUpRequest {
    
    public static void getLevelUps(int examinationId, int companyId, int levelId, final Response resp) {
        resp.onStart();
        Requests client = HandlerRequest.createService(Requests.class);
        Call<LevelUpRespone> call = client.getLevelUps(examinationId, companyId, levelId);
        call.enqueue(new Callback<LevelUpRespone>() {
            @Override
            public void onResponse(Call<LevelUpRespone> call, retrofit2.Response<LevelUpRespone> response) {
                resp.onSuccess(response.body().erorr_code, response.body().message, response.body().data);
            }
            @Override
            public void onFailure(Call<LevelUpRespone> call, Throwable t) {
                resp.onFailure();
            }
        });
    }

    public static void updatePoint(int studentId, int userId, float point,int pointType, final Response resp) {
        resp.onStart();
        Requests client = HandlerRequest.createService(Requests.class);
        Call<ResponseVO> call = client.updatePoint(studentId, userId, point, pointType);
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
}
