package vn.tranty.vovinam_client.models.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TRUC-SIDA on 10/31/2017.
 */
public class UserPermission {
    public UserPermission(int userId, int permissionId) {
        this.userId = userId;
        this.permissionId = permissionId;
    }
    public UserPermission() {

    }
    @SerializedName("user_id")
    @Expose
    public int userId;
    @SerializedName("permission_id")
    @Expose
    public int permissionId;

    public static boolean contains(List<UserPermission> list, UserPermission per) {
        for (UserPermission object : list) {
            if (object.userId == per.userId && object.permissionId == per.permissionId) {
                return true;
            }
        }
        return false;
    }
}
