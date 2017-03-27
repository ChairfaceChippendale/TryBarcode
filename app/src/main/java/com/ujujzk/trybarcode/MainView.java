package com.ujujzk.trybarcode;


import android.graphics.Bitmap;

public interface MainView {

    void showBarcodeImage(Bitmap barcodeImg);

    void showValueError(String errorMessage);
}
