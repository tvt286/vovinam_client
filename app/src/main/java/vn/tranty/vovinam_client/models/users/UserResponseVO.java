package vn.tranty.vovinam_client.models.users;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.tranty.vovinam_client.models.ResponseVO;

/**
 * Created by TRUC-SIDA on 10/24/2017.
 */

public class UserResponseVO extends ResponseVO {
    @SerializedName("data")
    @Expose
    public UserModel data;
}
