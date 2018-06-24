package t3h.com.musicbolero.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import t3h.com.musicbolero.connecinternet.DownloadJSON;
import t3h.com.musicbolero.constant.iConstant;
import t3h.com.musicbolero.objectclass.BaiHat;
import t3h.com.musicbolero.objectclass.ChuDe;

/**
 * Created by songsong on 6/20/2018.
 */

public class GetListChuDe {

    public List<ChuDe> layListChuDeTheoMa()
    {
        List<ChuDe> chuDes=new ArrayList<>();

        HashMap<String,String> hsHam=new HashMap<>();
        hsHam.put("ham","layDanhSachChuDeTheoMa");

        List<HashMap<String,String>> attrs=new ArrayList<>();
        attrs.add(hsHam);

        DownloadJSON downloadJSON=new DownloadJSON(iConstant.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String data=downloadJSON.get();

            JSONObject jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("DANHSACHCHUDE");

            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject object=jsonArray.getJSONObject(i);

                int  maDanhSach=object.getInt("madanhsach");
                String tenChuDe=object.getString("tenchude");
                String hinhChuDeLon=object.getString("hinhchudelon");
                String hinhChuDeNho=object.getString("hinhchudenho");
                ChuDe chuDe=new ChuDe();
                chuDe.setMaDanhSach(maDanhSach);
                chuDe.setTenChuDe(tenChuDe);
                chuDe.setHinhChuDeNho(hinhChuDeNho);
                chuDe.setHinhChuDeLon(hinhChuDeLon);
                chuDes.add(chuDe);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

     return chuDes;
    }


}
