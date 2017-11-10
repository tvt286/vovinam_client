package vn.tranty.vovinam_client.models.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.tranty.vovinam_client.models.ResponseVO;

/**
 * Created by TRUC-SIDA on 11/10/2017.
 */

public class ResultResponse  extends ResponseVO{

    @SerializedName("data")
    @Expose
    public ArrayList<ResultModel> data;
}
