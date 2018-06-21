package t3h.com.musicbolero.customview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import t3h.com.musicbolero.R;

/**
 * Created by songsong on 6/19/2018.
 */

public class CustomEdittextEmail extends android.support.v7.widget.AppCompatEditText {

    private boolean visiableIconClearn = false;
    private Drawable clearnVisiable, clearnVisiableOff;//clearnVisiable để lấy ra hình icon
    private  Drawable drawable;
    private Pattern pattern;
    private Matcher matcher;

    public CustomEdittextEmail(Context context) {
        super(context);
        khoiTao();
    }

    public CustomEdittextEmail(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoiTao();
    }

    public CustomEdittextEmail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoiTao();
    }

    private void khoiTao() {
        this.pattern=Pattern.compile(ContactsContract.CommonDataKinds.Email.ADDRESS);
        // lấy ra icon clearn
        clearnVisiable = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        // lấy ra ảnh trong suốt
        clearnVisiableOff = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_dark_transparent).mutate();
        caiDat();
    }

    private void caiDat() {
        // set kiểu nhập
        setInputType(InputType.TYPE_CLASS_TEXT);



        drawable = visiableIconClearn == true ? clearnVisiable : clearnVisiableOff;
        Drawable drawable1=ContextCompat.getDrawable(getContext(),R.drawable.user);

        // trả về 1 mảng các hình ảnh theo thứ tự bên trên,bên trái,bên phải,bên dưới
        setCompoundDrawablesWithIntrinsicBounds(drawable1, null, drawable, null);

    }

    // bắt sự kiện thay đổi text

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (text.length() >= 1) {
            visiableIconClearn = true;
            caiDat();
        } else {
            visiableIconClearn = false;
            caiDat();
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
    // bắt sự kiện khi click vào iconClearn


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN && event.getX()>= (getRight()-drawable.getBounds().width()))
        {
            // set lại text
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        String email=getText().toString().trim();
        if(!focused)
        {
            matcher=pattern.matcher(email);
            if(!matcher.matches())
            {
                setError("Địa chỉ email không hợp lệ");
            }else
            {
                setError("");
            }

        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
}
