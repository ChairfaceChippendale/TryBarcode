package com.ujujzk.trybarcode;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
import com.ujujzk.trybarcode.BarcodeUtil.BarcodeController;
import com.ujujzk.trybarcode.BarcodeUtil.BarcodeControllerImpl;
import com.ujujzk.trybarcode.ShareUtil.ShareController;
import com.ujujzk.trybarcode.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements MainView {

    ActivityMainBinding binding;

    BarcodeController barcodeController;
    ShareController shareController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        barcodeController = new BarcodeControllerImpl(this);




        initViews();

    }


    void initViews() {

        ArrayAdapter<String> barcodeTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, barcodeController.provideBarcodeTypes());
        barcodeTypeAdapter.setDropDownViewResource(R.layout.blue_spinner_dropdown_item);
        binding.barcodeTypeSnr.setAdapter(barcodeTypeAdapter);

        binding.barcodeValueEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    binding.barcodeValueContainer.setError(null);
                }
            }
        });



        binding.makeBarcodeBtn.setOnClickListener(
                v -> {
                    binding.barcodeValueContainer.setError(null);
                    binding.makeBarcodeBtn.setEnabled(false);
                    barcodeController.makeBarcode((String) binding.barcodeTypeSnr.getSelectedItem(), binding.barcodeValueEdt.getText().toString());
                }
        );

    }




    @Override
    public void showValueError(String errorMessage) {
        binding.barcodeValueContainer.setError(errorMessage);
    }

    @Override
    public void showBarcodeImage(Bitmap image) {
        binding.barcodeImg.setImageBitmap(image);
        binding.makeBarcodeBtn.setEnabled(true);
    }
}

