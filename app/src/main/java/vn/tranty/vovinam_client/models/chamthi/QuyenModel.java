package vn.tranty.vovinam_client.models.chamthi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TRUC-SIDA on 11/2/2017.
 */

public class QuyenModel {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("point")
    @Expose
    public float point;
    @SerializedName("user_name")
    @Expose
    public String userName;
}
