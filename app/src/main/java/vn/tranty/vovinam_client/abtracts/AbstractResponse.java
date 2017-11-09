package vn.tranty.vovinam_client.abtracts;

import android.view.View;

import vn.tranty.vovinam_client.interfaces.Response;

/**
 * Created by TRUC-SIDA on 11/9/2017.
 */

public abstract class AbstractResponse implements Response {
    public void onStart(){
    }

    public void onSuccess(int error_code, String message, Object obj){
    }

    public void onFailure(){
    }
}
