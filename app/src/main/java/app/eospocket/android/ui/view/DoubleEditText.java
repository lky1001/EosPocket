package app.eospocket.android.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class DoubleEditText extends EditText {

    private static final int ERROR_TEXT_COLOR = Color.RED;
    private static final int NORMAL_TEXT_COLOR = Color.BLACK;

    public static final double INVAILD_VALUE = -1;

    public DoubleEditText(Context context) {
        this(context, null);
    }

    public DoubleEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi();
    }

    private void initUi() {
        setTextColor(NORMAL_TEXT_COLOR);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentTextColor() != NORMAL_TEXT_COLOR) {
                    setTextColor(NORMAL_TEXT_COLOR);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    public double getDoubleValue() {
        try {
            String string = getText().toString();
            double doubleValue = Double.parseDouble(string);
            return doubleValue;
        } catch (NumberFormatException e) {
            setTextColor(Color.RED);
        }
        return -1;
    }
}
