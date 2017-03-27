package com.ujujzk.trybarcode.BarcodeUtil;


import java.util.List;

public interface BarcodeController {


    void makeBarcode(String type, String value);

    List<String> provideBarcodeTypes();


}
