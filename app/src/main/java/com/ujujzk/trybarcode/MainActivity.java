package com.ujujzk.trybarcode;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.ujujzk.trybarcode.barcodeUtil.BarcodeControllerImpl;
import com.ujujzk.trybarcode.customViews.LymbdableTextView;
import com.ujujzk.trybarcode.databinding.ActivityMainBinding;
import com.ujujzk.trybarcode.shareUtil.ShareControllerImpl;

public class MainActivity extends AppCompatActivity implements MainView {

    ActivityMainBinding binding;

    BarcodeControllerImpl barcodeController;
    ShareControllerImpl shareController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        barcodeController = new BarcodeControllerImpl(this);
        shareController = new ShareControllerImpl(this);

        initViews();
    }

    @Override
    public Context provideContext() {
        return this;
    }

    @Override
    public void showValueError(String errorMessage) {
        binding.barcodeValueContainer.setError(errorMessage);
    }

    @Override
    public void showBarcodeImage(Bitmap image) {
        binding.barcodeImg.setImageBitmap(image);
    }

    private void initViews() {

        ArrayAdapter<String> barcodeTypeAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, barcodeController.provideBarcodeTypes());
        barcodeTypeAdapter.setDropDownViewResource(R.layout.blue_spinner_dropdown_item);
        binding.barcodeTypeSnr.setAdapter(barcodeTypeAdapter);

        LymbdableTextView.with(binding.barcodeValueEdt).setOnTextChangedListener(
                newText -> binding.barcodeValueContainer.setError(null)
        );

        binding.makeBarcodeBtn.setOnClickListener(
                v -> barcodeController.makeBarcode((String) binding.barcodeTypeSnr.getSelectedItem(), binding.barcodeValueEdt.getText().toString())
        );
    }
}

