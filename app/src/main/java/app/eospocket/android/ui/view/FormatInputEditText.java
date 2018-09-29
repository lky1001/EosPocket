package app.eospocket.android.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import app.eospocket.android.R;
import io.mithrilcoin.eos.util.StringUtils;

public class FormatInputEditText extends FrameLayout implements TextWatcher, View.OnFocusChangeListener {

    private TextInputLayout inputLayout;
    private TextInputEditText inputEditText;
    private Format format;

    public FormatInputEditText(Context context) {
        this(context, null);
    }

    public FormatInputEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public FormatInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_double_edit_text, this, true);
        inputLayout = view.findViewById(R.id.root_view);
        inputLayout.setDefaultHintTextColor(getResources().getColorStateList(R.color.format_edit_text_color));
        inputLayout.setHelperTextColor(getResources().getColorStateList(R.color.format_edit_text_color));
        inputLayout.setErrorTextColor(getResources().getColorStateList(R.color.format_edit_text_color));

        inputEditText = inputLayout.findViewById(R.id.editText);
        inputEditText.addTextChangedListener(this);
        inputEditText.setTextColor(Color.BLACK);
        inputEditText.setOnFocusChangeListener(this);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.InputEditText, defStyleAttr, 0);
        String hint = typedArray.getString(R.styleable.InputEditText_hint);
        format = Format.valueOf(typedArray.getInt(R.styleable.InputEditText_format, 0));

        if (!StringUtils.isEmpty(hint)) {
            inputLayout.setHint(hint);
        }
        typedArray.recycle();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        inputLayout.setError(null);
    }

    private boolean beforeFocusStatus;
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus && beforeFocusStatus) {
            checkValid();
        }
        beforeFocusStatus = hasFocus;
    }

    public boolean checkValid() {
        String string = inputEditText.getText().toString();

        switch (format) {
            case DOUBLE:
                try {
                    double doubleValue = Double.parseDouble(string);
                } catch (NumberFormatException e) {
                    inputLayout.setError("Input is Invaild ");
                    return false;
                }
        }
        return true;
    }

    public String getInputValue() {
        checkValid();
        return inputEditText.getText().toString();
    }

    private enum Format {
        STRING, DOUBLE;

        static Format valueOf(int o) {
            for (Format f : values()) {
                if (o == f.ordinal()) {
                    return f;
                }
            }
            return STRING;
        }
    }
}
