package vn.tranty.vovinam_client.mics;

/**
 * Created by TRUC-SIDA on 10/18/2017.
 */

public class Contanst {
    public static class TAB_MENU {
        public static final String TAB_THELUC = "Thể Lực";
        public static final String TAB_QUYEN = "Quyền";
        public static final String TAB_DOIKHANG = "Đối Kháng";
        public static final String TAB_VODAO = "Võ Đạo";
        public static final String TAB_COBAN = "Cơ Bản";
        public static final String TAB_SONGLUYEN = "Song Luyện";
        public static final String TAB_KETQUA = "Kết Quả";
        public static final String TAB_DOIKHANG_NAM = "Đối Kháng Nam";
        public static final String TAB_DOIKHANG_NU = "Đối Kháng Nữ";

    }

    public static class FRAGMENT {
        public static final int LAMDAI = 1;
        public static final int LAMDAI_I = 2;
        public static final int LAMDAI_II = 3;
        public static final int LAMDAI_III = 4;
        public static final int DOI_KHANG = 5;
    }

    public static class POINT_TYPE {
        public static final int CO_BAN = 1;
        public static final int VO_DAO = 2;
        public static final int THE_LUC = 3;
        public static final int QUYEN = 4;
        public static final int DOI_KHANG = 5;
        public static final int SONG_LUYEN = 6;
        public static final int KETQUA = 7;

    }

    public static class GENDER {
        public static final int DK_NAM = 1;
        public static final int DK_NU = 2;
    }
    public static class API {
        public static final String URL = "http://www.vovinam-khxhnv.somee.com/Api/";
    }

    public static class ROLE {
        public static final int LAMDAI_COBAN = 7001;
        public static final int LAMDAI_QUYEN = 7002;
        public static final int LAMDAI_VODAO = 7003;
        public static final int LAMDAI_THELUC = 7004;

        public static final int LAMDAI_I_COBAN = 8001;
        public static final int LAMDAI_I_QUYEN = 8002;
        public static final int LAMDAI_I_VODAO = 8003;
        public static final int LAMDAI_I_DOIKHANG = 8004;

        public static final int LAMDAI_II_COBAN = 9001;
        public static final int LAMDAI_II_QUYEN = 9002;
        public static final int LAMDAI_II_VODAO = 9003;

        public static final int LAMDAI_III_COBAN = 10001;
        public static final int LAMDAI_III_QUYEN = 10002;
        public static final int LAMDAI_III_VODAO = 10003;
        public static final int LAMDAI_III_SONGLUYEN = 10004;

        public static final int DK_NAM = 10001;
        public static final int DK_NU = 10002;
    }
}
