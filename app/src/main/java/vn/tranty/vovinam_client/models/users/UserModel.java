package vn.tranty.vovinam_client.models.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TRUC-SIDA on 10/24/2017.
 */

public class UserModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("email")
    @Expose
    public Object email;
    @SerializedName("phone")
    @Expose
    public Object phone;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("is_admin_root")
    @Expose
    public Boolean isAdminRoot;
    @SerializedName("is_admin_company")
    @Expose
    public Boolean isAdminCompany;
    @SerializedName("user_permission")
    @Expose
    public List<UserPermission> userPermission = null;
    @SerializedName("group")
    @Expose
    public List<Object> group = null;


}

class UserPermission {

    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("permission_id")
    @Expose
    public Integer permissionId;
}