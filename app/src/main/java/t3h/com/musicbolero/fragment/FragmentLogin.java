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

import t3h.com.musicbolero.IAddFragment;
import t3h.com.musicbolero.LoginActivity;
import t3h.com.musicbolero.MainActivity;
import t3h.com.musicbolero.R;
import t3h.com.musicbolero.connecinternet.DownloadJSON;
import t3h.com.musicbolero.constant.iConstant;

/**
 * Created by songsong on 6/18/2018.
 */

public class FragmentLogin extends Fragment implements View.OnClickListener {
    private TextView txtBolero;
    private Button btnRegister,btnLogin;
    private IAddFragment iAddFragment;
    private EditText edtEmailLogin,edtPasswordLogin;
    private String emailLogin,passWordLogin;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iAddFragment=(IAddFragment)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        anhXa(view);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Fonty.ttf");
        txtBolero.setTypeface(typeface);


        return view;
    }

    private void anhXa(View view) {
        txtBolero = view.findViewById(R.id.txt_bolero);
        btnRegister=view.findViewById(R.id.btn_register_login);
        btnLogin=view.findViewById(R.id.btn_login_login);

        edtEmailLogin=view.findViewById(R.id.edt_email_login);
        edtPasswordLogin=view.findViewById(R.id.edt_passwork_login);
        //
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.btn_register_login:
                //((LoginActivity)getActivity()).addFragmentRegister();
                iAddFragment.addFragment(FragmentRegister.class.getName());
                break;
            case R.id.btn_login_login:
                Toast.makeText(getContext(), "toas", Toast.LENGTH_SHORT).show();
                emailLogin=edtEmailLogin.getText().toString().trim();
                passWordLogin=edtPasswordLogin.getText().toString().trim();

                List<HashMap<String,String>> hashMaps=new ArrayList<>();

                HashMap<String,String> hsHam=new HashMap<>();
                hsHam.put("ham","kiemTraDangNhap");

                HashMap<String,String> hsEmail=new HashMap<>();
                hsEmail.put("email",emailLogin);

                HashMap<String,String> hsMatKhau=new HashMap<>();
                hsMatKhau.put("matkhau",passWordLogin);

                hashMaps.add(hsHam);
                hashMaps.add(hsEmail);
                hashMaps.add(hsMatKhau);

                DownloadJSON downloadJSON=new DownloadJSON(iConstant.SERVER_NAME,hashMaps);
                downloadJSON.execute();

                try {
                    String login=downloadJSON.get();
                    Log.d("login",login+"ss");

                    if(login.equals("1"))
                    {
                        Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        return;
                    }
                    if(login.equals("0"))
                    {
                        Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        return;

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
