package app.eospocket.android.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class DoubleEditText extends AppCompatEditText implements TextWatcher{

    private static final int ERROR_TEXT_COLOR = Color.RED;
    private static final int NORMAL_TEXT_COLOR = Color.BLACK;

    public static final double INVAILD_VALUE = -1;

    public DoubleEditText(Context context) {
        super(context);
        initUi();
    }

    public DoubleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUi();

    }

    public DoubleEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi();
    }

    private void initUi() {
        setTextColor(NORMAL_TEXT_COLOR);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (getCurrentTextColor() != NORMAL_TEXT_COLOR) {
            setTextColor(NORMAL_TEXT_COLOR);
        }
    }

    public double getDoubleValue() {
        try {
            String string = getText().toString();
            double doubleValue = Double.parseDouble(string);
            return doubleValue;
        } catch (NumberFormatException e) {
            setTextColor(ERROR_TEXT_COLOR);
        }
        return -1;
    }
}
