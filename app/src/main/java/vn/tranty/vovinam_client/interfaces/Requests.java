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
import vn.tranty.vovinam_client.models.users.UsersResponseVO;

public interface Requests {

    @POST("User/Login")
    Call<UserResponseVO> login(@Query("user_name") String userName, @Query("password") String password);

    @GET("LevelUp/GetLevelUps")

    Call<LevelUpRespone> getLevelUps(@Query("examination_id") int examination_id, @Query("company_id") int companyId, @Query("level_id") int levelId);
    @GET("User/GetUsers")
    Call<UsersResponseVO> getUsers(@Query("company_id") int companyId);

    @GET("LevelUp/GetExamination")
    Call<ExaminationResponseVO> getExaminations(@Query("company_id") int companyId);

    @POST("LevelUp/UpdatePoint")
    Call<ResponseVO> updatePoint(@Query("levelup_id") int studentId, @Query("user_id") int user_id, @Query("point") float point, @Query("point_type") int pointType);

    @GET("Compete/GetCompetes")
    Call<CompeteRespone> getCompetes(@Query("examination_id") int examination_id, @Query("company_id") int companyId, @Query("gender") int gender);

    @POST("Compete/UpdatePoint")
    Call<ResponseVO> updatePointCompete(@Query("levelup_id_1") int student1Id, @Query("levelup_id_2") int student2Id, @Query("user_id") int user_id, @Query("point_1") float point1, @Query("point_2") float point2);

    @POST("User/ChangePassword")
    Call<ResponseVO> changePassword(@Query("user_name") String userName, @Query("password_old") String passwordOld, @Query("password_new") String passwordNew);

    @POST("User/LockAccount")
    Call<ResponseVO> lockAccount(@Query("user_id") int userId);

}
