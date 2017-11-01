package vn.tranty.vovinam_client.preferences;

import android.content.Context;

import vn.tranty.vovinam_client.models.users.UserModel;

public class UserShared extends SharedPre {
    private static UserShared instance = null;
    private UserModel userVO;
    private String password;
    private Boolean rememberMe;
    private UserShared(Context context) {
        super(context, "userVO");
        userVO = new UserModel();
        restoringPreferences();
    }

    public static UserShared ins(Context context) {
        if (instance == null) {
            instance = new UserShared(context);
        }
        return instance;
    }

    public void restoringPreferences() {
        editor = pre.edit();
        userVO.id = getId();
        userVO.userName = getUserName();
        userVO.fullName = getFullName();
        userVO.image = getImage();
        userVO.isAdminCompany = getIsAdminCompanyId();
        rememberMe = getRememberMe();

        password = getPassword();
    }


    public UserModel getUserModel() {
        return userVO;
    }

    public void setUserModel(UserModel userVO, String password, boolean rememberMe) {
        editor = pre.edit();
        editor.putInt("id", userVO.id);
        editor.putString("user_name", userVO.userName);
        editor.putString("full_name", userVO.fullName);
        editor.putString("image", userVO.image);
        editor.putBoolean("is_admin_company", userVO.isAdminCompany);
        editor.putString("password", password);
        editor.putBoolean("remember_me", rememberMe);

        editor.commit();
        this.userVO = userVO;
        this.password = password;
        this.rememberMe = rememberMe;
    }


    public int getId() {
        return pre.getInt("id", 0);
    }

    public String getUserName() {
        return pre.getString("user_name", "");
    }

    public String getFullName() {
        return pre.getString("full_name", "");
    }

    public String getImage() {
        return pre.getString("image", "");
    }

    public Boolean getIsAdminCompanyId() {
        return pre.getBoolean("is_admin_company", false);
    }

    public Boolean getRememberMe() {
        return pre.getBoolean("remember_me", false);
    }


    public String getPassword() {
        return pre.getString("password", "");
    }


}
