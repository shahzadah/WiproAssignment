package com.demo.facts.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.demo.facts.R;

/**
 * Helper class for Alert based on the single and Multiple button variations.
 */
class DialogHelper {

    private final Context mContext;
    private View mDialogView;

    private final String mHeaderMessage;
    private final String mErrorMessage;
    private final String mButtonText;
    private final View.OnClickListener mClickListener;

    DialogHelper(Context context, String header, String message, String button1, View.OnClickListener button1ClickListener) {
        mContext = context;
        mHeaderMessage = header;
        mErrorMessage = message;
        mButtonText = button1;
        mClickListener = button1ClickListener;

        init();
    }

    /**
     * This method stands for initializing single button
     */
    private void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mDialogView = inflater.inflate(R.layout.custom_error_dialog, null);

        TextView errorHeaderTv = mDialogView.findViewById(R.id.errorHeaderTextView);
        TextView errorMessageTv = mDialogView.findViewById(R.id.errorMessageTextView);
        TextView button1 = mDialogView.findViewById(R.id.button_okay);
        button1.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(mHeaderMessage)) {
            errorHeaderTv.setVisibility(View.VISIBLE);
            errorHeaderTv.setText(mHeaderMessage);
        } else {
            errorHeaderTv.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(mErrorMessage)) {
            errorMessageTv.setVisibility(View.VISIBLE);
            errorMessageTv.setText(mErrorMessage);
        } else {
            errorMessageTv.setVisibility(View.GONE);
        }
        button1.setTypeface(null, Typeface.BOLD);
        button1.setText(mButtonText);
        button1.setOnClickListener(mClickListener);
    }

    /**
     * This method will return the dialog view
     *
     * @return : Dialog View
     */
    View getDialogView() {
        return mDialogView;
    }
}