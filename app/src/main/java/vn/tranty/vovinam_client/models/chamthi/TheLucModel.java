package vn.tranty.vovinam_client.models.chamthi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TRUC-SIDA on 11/2/2017.
 */

public class TheLucModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("point")
    @Expose
    public Integer point;
    @SerializedName("user_name")
    @Expose
    public String userName;
}
