package t3h.com.musicbolero.customview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import t3h.com.musicbolero.R;

/**
 * Created by songsong on 6/19/2018.
 */

public class CustomPasswordEdittext extends android.support.v7.widget.AppCompatEditText {
    private Drawable anMatKhau, hienMatKhau;
    // mặc định là hiện mật khẩu
    private boolean hienPassword = false;
    private Drawable drawable;
    private int dem = 1;
    private String MATCHER_PATTERN=".{6,20}";
    private Matcher matcher;
    private Pattern pattern;


    public CustomPasswordEdittext(Context context) {
        super(context);
        khoiTao();
    }

    public CustomPasswordEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoiTao();
    }

    public CustomPasswordEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoiTao();
    }

    private void khoiTao() {
        anMatKhau = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        hienMatKhau = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        caiDat();
    }

    private void caiDat() {
        drawable = hienPassword == true ? hienMatKhau : anMatKhau;
        Drawable icPassword = ContextCompat.getDrawable(getContext(), R.drawable.password);
        setCompoundDrawablesWithIntrinsicBounds(icPassword, null, drawable, null);
        setInputType(InputType.TYPE_CLASS_TEXT | (hienPassword == true ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                InputType.TYPE_TEXT_VARIATION_PASSWORD)
        );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())) {

            if (dem % 2 != 0) {
                hienPassword = true;
                caiDat();
                dem++;

            } else {
                hienPassword = false;
                caiDat();
                dem++;
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {

        String chuoi=getText().toString();
        if(!focused)
        {
           this.pattern=Pattern.compile(MATCHER_PATTERN);
           matcher=this.pattern.matcher(chuoi);
            if(!matcher.matches())
            {
                setError("Mật khẩu phải lớn hơn 6 ký tự");
            }else
            {
                setError("");
            }
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
}
