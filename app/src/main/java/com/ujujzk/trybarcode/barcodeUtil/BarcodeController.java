package com.ujujzk.trybarcode.barcodeUtil;


import java.util.List;

public interface BarcodeController {

    void makeBarcode(String type, String value);
    List<String> provideBarcodeTypes();
}
