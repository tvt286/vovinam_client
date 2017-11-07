package vn.tranty.vovinam_client.models.chamthi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TRUC-SIDA on 10/19/2017.
 */

public class StudentModel{
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("stt")
    @Expose
    public int stt;
    @SerializedName("birthday")
    @Expose
    public int birthday;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("weight")
    @Expose
    public int weight;
    @SerializedName("total")
    @Expose
    public float total;
    @SerializedName("ketqua")
    @Expose
    public String ketqua;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("club")
    @Expose
    public ClubModel club;
    @SerializedName("co_ban")
    @Expose
    public CoBanModel coBan;
    @SerializedName("doi_khang")
    @Expose
    public DoiKhangModel doiKhang;
//    @SerializedName("examination")
//    @Expose
//    public Object examination;
    @SerializedName("level")
    @Expose
    public LevelModel level;
    @SerializedName("quyen")
    @Expose
    public QuyenModel quyen;
    @SerializedName("song_luyen")
    @Expose
    public SongLuyenModel songLuyen;
    @SerializedName("the_luc")
    @Expose
    public TheLucModel theLuc;
    @SerializedName("vo_dao")
    @Expose
    public VoDaoModel voDao;

}
