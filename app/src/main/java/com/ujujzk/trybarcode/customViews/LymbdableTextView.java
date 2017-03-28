package com.ujujzk.trybarcode.customViews;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 *
 * Proxy for {@link TextView} that allows use functional interface {@link TextChangeListener}
 * instead of {@link TextWatcher}
 *
 *
 * @author ujujzk@gmail.com
 *
 */
public class LymbdableTextView {

    private TextView editText;

    private LymbdableTextView(final TextView editText) {
        this.editText = editText;
    }

    public static LymbdableTextView with(final TextView editText) {
        return new LymbdableTextView(editText);
    }

    public void setOnTextChangedListener (final TextChangeListener tcl) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable newText) {
                tcl.onTextChanged(newText);
            }
        });
    }

}
