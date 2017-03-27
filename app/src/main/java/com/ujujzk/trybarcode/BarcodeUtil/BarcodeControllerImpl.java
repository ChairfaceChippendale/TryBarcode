package com.ujujzk.trybarcode.BarcodeUtil;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ujujzk.trybarcode.BuildConfig;
import com.ujujzk.trybarcode.MainView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BarcodeControllerImpl implements BarcodeController {

    MainView mainActivity;

    public BarcodeControllerImpl(MainView mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void makeBarcode(String type, String value) {

        Bitmap bmp = null;

        MultiFormatWriter writer = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = writer.encode(value, BarcodeFormat.valueOf(type), 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
            mainActivity.showValueError(e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            mainActivity.showValueError(e.getMessage());
        }
        mainActivity.showBarcodeImage(bmp);
    }


    @Override
    public List<String> provideBarcodeTypes() {

        List<String> barcodeTypes;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            barcodeTypes = Stream.of(BarcodeFormat.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
        } else {
            barcodeTypes = new ArrayList<>();
            BarcodeFormat[] bf = BarcodeFormat.values();
            for (BarcodeFormat aBf : bf) {
                barcodeTypes.add(aBf.name());
            }
        }
        return barcodeTypes;
    }
}
