package vn.tranty.vovinam_client.interfaces;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Query;
import vn.tranty.vovinam_client.models.chamthi.LevelUpRespone;
import vn.tranty.vovinam_client.models.chamthi.StudentModel;
import vn.tranty.vovinam_client.models.examinations.ExaminationModel;
import vn.tranty.vovinam_client.models.examinations.ExaminationResponse;
import vn.tranty.vovinam_client.models.users.UserResponse;

public interface Requests {

    @POST("User/Login")
    Call<UserResponse> login(@Query("user_name") String userName, @Query("password") String password);

    @GET("LevelUp/GetLevelUps")
    Call<LevelUpRespone> getLevelUps(@Query("examination_id") int examination_id, @Query("company_id") int companyId, @Query("level_id") int levelId);

    @GET("LevelUp/GetExamination")
    Call<ExaminationResponse> getExaminations(@Query("company_id") int companyId);
}
