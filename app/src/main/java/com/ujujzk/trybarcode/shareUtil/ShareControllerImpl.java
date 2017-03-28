package com.ujujzk.trybarcode.shareUtil;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.ujujzk.trybarcode.MainView;
import com.ujujzk.trybarcode.R;

public class ShareControllerImpl implements ShareController {

    private MainView mainActivity;

    public ShareControllerImpl(MainView mainView) {
        mainActivity = mainView;
    }

    private ShareControllerImpl() {
        throw new AssertionError();
    }

    @Override
    public void share(String message) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        Intent openInChooser = Intent.createChooser(sendIntent, mainActivity.provideContext().getString(R.string.share_message_title));
        mainActivity.provideContext().startActivity(openInChooser);
    }
}
