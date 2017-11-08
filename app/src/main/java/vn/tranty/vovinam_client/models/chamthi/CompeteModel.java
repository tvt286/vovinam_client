package vn.tranty.vovinam_client.models.chamthi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TRUC-SIDA on 11/8/2017.
 */

public class CompeteModel {
    @SerializedName("levelup_1")
    @Expose
    public StudentModel levelup1;
    @SerializedName("levelup_2")
    @Expose
    public StudentModel levelup2;
}
