package vn.tranty.vovinam_client.interfaces;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Query;
import vn.tranty.vovinam_client.models.*;
import vn.tranty.vovinam_client.models.chamthi.CompeteRespone;
import vn.tranty.vovinam_client.models.chamthi.LevelUpRespone;
import vn.tranty.vovinam_client.models.examinations.ExaminationResponseVO;
import vn.tranty.vovinam_client.models.users.UserResponseVO;

public interface Requests {

    @POST("User/Login")
    Call<UserResponseVO> login(@Query("user_name") String userName, @Query("password") String password);

    @GET("LevelUp/GetLevelUps")
    Call<LevelUpRespone> getLevelUps(@Query("examination_id") int examination_id, @Query("company_id") int companyId, @Query("level_id") int levelId);

    @GET("LevelUp/GetExamination")
    Call<ExaminationResponseVO> getExaminations(@Query("company_id") int companyId);

    @POST("LevelUp/UpdatePoint")
    Call<ResponseVO> updatePoint(@Query("levelup_id") int studentId, @Query("user_id") int user_id, @Query("point") float point, @Query("point_type") int pointType );

    @GET("Compete/GetCompetes")
    Call<CompeteRespone> getCompetes(@Query("examination_id") int examination_id, @Query("company_id") int companyId, @Query("gender") int gender);


}
