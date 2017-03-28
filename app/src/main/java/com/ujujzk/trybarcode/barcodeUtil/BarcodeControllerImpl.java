package com.ujujzk.trybarcode.barcodeUtil;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ujujzk.trybarcode.MainView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BarcodeControllerImpl implements BarcodeController {

    private MainView mainActivity;

    private BarcodeControllerImpl() {
        throw new AssertionError();
    }

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
        } catch (WriterException | IllegalArgumentException e) {
            e.printStackTrace();
            mainActivity.showValueError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            //Code93 writer throws it if value invalid
            e.printStackTrace();
            mainActivity.showValueError("Invalid value");
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
