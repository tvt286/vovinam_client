package vn.tranty.vovinam_client.interfaces;


/**
 * Created by TranTy on 5/13/2016.
 */
public interface Response {
    public void onStart();
    public void onSuccess(int error_code, String message, Object obj);
    public void onFailure();
}
