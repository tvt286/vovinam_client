package vn.tranty.vovinam_client;

import android.app.Application;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import vn.tranty.vovinam_client.models.users.UserPermission;

/**
 * Created by TRUC-SIDA on 10/31/2017.
 */

public class VovinamApplication extends Application {
   private static Dictionary<Integer,ArrayList<UserPermission>> permissions;

    @Override
    public void onCreate() {
        super.onCreate();
        permissions = new Hashtable<>();
     }


  public void setArrPermission(int id, ArrayList<UserPermission> arr){
      permissions.put(id, arr);
    }

    public ArrayList<UserPermission> getArrayPermission(int userId){
        return permissions.get(userId);
    }
}
