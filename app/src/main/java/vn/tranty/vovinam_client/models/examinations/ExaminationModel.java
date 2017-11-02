package vn.tranty.vovinam_client.models.examinations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TRUC-SIDA on 11/2/2017.
 */

public class ExaminationModel
{
        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
}
