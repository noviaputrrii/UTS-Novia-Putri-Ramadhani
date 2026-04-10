package com.example.warungnusantara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    ImageView ivHasilFoto;
    TextView tvHasilID;
    Button btnLokasi, btnSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivHasilFoto = findViewById(R.id.ivHasilFoto);
        tvHasilID = findViewById(R.id.tvHasilID);
        btnLokasi = findViewById(R.id.btnLokasi);
        btnSelesai = findViewById(R.id.btnSelesai);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            String idKaryawan = data.getString("DATA_ID");
            Bitmap foto = data.getParcelable("DATA_FOTO");

            tvHasilID.setText("ID Karyawan: " + idKaryawan);
            ivHasilFoto.setImageBitmap(foto);
        }

        btnLokasi.setOnClickListener(v -> {
            Uri lokasi = Uri.parse("geo:-6.200000, 106.816666");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, lokasi);
            mapIntent.setPackage("com.google.android.apps

