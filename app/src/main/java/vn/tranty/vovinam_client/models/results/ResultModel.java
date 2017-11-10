package vn.tranty.vovinam_client.models.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.tranty.vovinam_client.models.ResponseVO;
import vn.tranty.vovinam_client.models.chamthi.StudentModel;

/**
 * Created by TRUC-SIDA on 11/10/2017.
 */

public class ResultModel {
    @SerializedName("gender")
    @Expose
    public String gender;

    @SerializedName("students")
    @Expose
    public ArrayList<StudentModel> students;

}
