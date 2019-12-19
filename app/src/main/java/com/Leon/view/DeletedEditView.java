package com.Leon.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.Leon.RetrofitPratice.R;

/**
 * @author leixinqiao
 * @create on 2019-12-03 10:40
 */
public class DeletedEditView extends FrameLayout {


    public DeletedEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.delete_edittext_layout, this);
        EditText editText = inflate.findViewById(R.id.edit_text);

        ForkView forkView = inflate.findViewById(R.id.fork_view);
        forkView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {

                    forkView.setVisibility(GONE);

                } else {

                    forkView.setVisibility(VISIBLE);

                }
            }
        });


    }
}
