package com.ujujzk.trybarcode.ShareUtil;


import android.content.Context;
import android.content.Intent;

import com.ujujzk.trybarcode.R;

public class ShareControllerImpl implements ShareController {

    @Override
    public void share(String message, Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        Intent openInChooser = Intent.createChooser(sendIntent, context.getString(R.string.share_message_title));
        context.startActivity(openInChooser);
    }
}
