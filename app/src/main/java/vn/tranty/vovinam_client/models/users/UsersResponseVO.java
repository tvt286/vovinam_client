package vn.tranty.vovinam_client.models.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import vn.tranty.vovinam_client.models.ResponseVO;

/**
 * Created by TRUC-SIDA on 11/9/2017.
 */

public class UsersResponseVO extends ResponseVO{
    @SerializedName("data")
    @Expose
    public ArrayList<UserModel> data;
}
