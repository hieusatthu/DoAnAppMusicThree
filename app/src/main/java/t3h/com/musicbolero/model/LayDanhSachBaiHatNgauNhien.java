package t3h.com.musicbolero.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import t3h.com.musicbolero.connecinternet.DownloadJSON;
import t3h.com.musicbolero.constant.iConstant;
import t3h.com.musicbolero.objectclass.BaiHat;

/**
 * Created by songsong on 6/22/2018.
 */

public class LayDanhSachBaiHatNgauNhien {

    public List<BaiHat> layDanhSachBaiHatNgauNhien() {
        List<BaiHat> baiHats = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        // random madanhsach
        Random random = new Random();
        int maDanhSach = random.nextInt(4)+1;
        Log.d("madanhsach",maDanhSach+"");
        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "layDanhSachBaiHatNgauNhien");

        HashMap<String, String> hsMaDanhSach = new HashMap<>();
        hsMaDanhSach.put("madanhsach", String.valueOf(maDanhSach));

        attrs.add(hsHam);
        attrs.add(hsMaDanhSach);

        DownloadJSON downloadJSON = new DownloadJSON(iConstant.SERVER_NAME, attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("DANHSACHBAIHAT");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                BaiHat baiHat = new BaiHat();
                String tenBaiHat = object.getString("tenbaihat");
                String tenCaSy = object.getString("tencasy");
                int madanhSach = object.getInt("madanhsach");
                String hinhBaiHat = object.getString("hinhbaihat");
                String linkBaiHat = object.getString("linkbaihat");
                baiHat.setMaDanhSach(madanhSach);
                baiHat.setTenBaiHat(tenBaiHat);
                baiHat.setTenCaSy(tenCaSy);
                baiHat.setHinhBaiHat(hinhBaiHat);
                baiHat.setLinkBaiHat(linkBaiHat);
                baiHats.add(baiHat);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return baiHats;
    }

//    public List<BaiHat> taiThemBaiHat(int soBaiHatHienTai)
//    {
//        for(int i=0; i<layDanhSachBaiHatNgauNhien().size(); i++)
//        {
//
//        }
//    }

}
