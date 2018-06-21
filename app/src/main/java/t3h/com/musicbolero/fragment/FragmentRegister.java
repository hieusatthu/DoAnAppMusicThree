package t3h.com.musicbolero.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import t3h.com.musicbolero.LoginActivity;
import t3h.com.musicbolero.MainActivity;
import t3h.com.musicbolero.R;
import t3h.com.musicbolero.connecinternet.DownloadJSON;
import t3h.com.musicbolero.constant.iConstant;

/**
 * Created by songsong on 6/19/2018.
 */

public class FragmentRegister extends Fragment implements View.OnClickListener {
    private Button btnRegister, btnCanced;
    private TextView txtRegister;
    private EditText edtEmailRegister,edtMatKhauRegister,edtNhapLaiMatKhauRegister;
    private String email,matKhau,nhapLaiMatKhau;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        anhXa(view);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Fonty.ttf");
        txtRegister.setTypeface(typeface);

        return view;
    }

    private void anhXa(View view) {
        btnRegister = view.findViewById(R.id.btn_register_register);
        btnCanced = view.findViewById(R.id.btn_canced_register);
        txtRegister = view.findViewById(R.id.txt_register);

        edtEmailRegister=view.findViewById(R.id.edt_email_register);
        edtMatKhauRegister=view.findViewById(R.id.edt_password_register);
        edtNhapLaiMatKhauRegister=view.findViewById(R.id.edt_input_password_register);
        //
        btnRegister.setOnClickListener(this);
        btnCanced.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_canced_register:
                ((LoginActivity)getActivity()).addFragmentLogin();
                break;
            case R.id.btn_register_register:
                email=edtEmailRegister.getText().toString().trim();
                matKhau=edtMatKhauRegister.getText().toString().trim();
                nhapLaiMatKhau=edtNhapLaiMatKhauRegister.getText().toString();

                if(email.equals("")||matKhau.equals("")||nhapLaiMatKhau.equals("")||
                        !edtEmailRegister.getError().toString().equals("") ||
                        !edtMatKhauRegister.getError().toString().equals("")||
                        !edtNhapLaiMatKhauRegister.getError().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!matKhau.equals(nhapLaiMatKhau))
                {
                    Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<HashMap<String,String>> hashMaps=new ArrayList<>();

                HashMap<String,String> hsHam=new HashMap<>();
                hsHam.put("ham","kiemTraDangKy");

                HashMap<String,String> hsEmail=new HashMap<>();
                hsEmail.put("email",email);

                HashMap<String,String> hsMatKhau=new HashMap<>();
                hsMatKhau.put("matkhau",matKhau);

                hashMaps.add(hsHam);
                hashMaps.add(hsEmail);
                hashMaps.add(hsMatKhau);
                DownloadJSON downloadJSON=new DownloadJSON(iConstant.SERVER_NAME,hashMaps);
                downloadJSON.execute();

                try {
                    String data=downloadJSON.get();
                    Log.d("data",data);
                    if(data.equals("1"))
                    {
                        Toast.makeText(getActivity(), "dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                    if(data.equals("0"))
                    {
                        Toast.makeText(getActivity(), "dang ky that bai", Toast.LENGTH_SHORT).show();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
        }

    }



}
