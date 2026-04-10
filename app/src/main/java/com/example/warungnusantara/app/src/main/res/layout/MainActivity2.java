package com.example.warungnusantara;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    ImageView ivFoto;
    EditText etID;
    Button btnCam, btnVerif;
    Bitmap fotoDariKamera;
    final int REQUEST_CAMERA = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ivFoto = findViewById(R.id.ivFoto);
        etID = findViewById(R.id.etID);
        btnCam = findViewById(R.id.btnAmbilFoto);
        btnVerif = findViewById(R.id.btnVerifikasi);

        btnCam.setOnClickListener(v -> {
            Intent intentKamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intentKamera, REQUEST_CAMERA);
        });

        btnVerif.setOnClickListener(v -> {
            String id = etID.getText().toString().trim();

            if(id.isEmpty()){
                etID.setError("ID tidak boleh kosong");
                return;
            }
            if(fotoDariKamera == null){
                Toast.makeText(this, "Ambil foto dulu dong!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent kirim = new Intent(MainActivity2.this, DetailActivity.class);
            kirim.putExtra("DATA_ID", id);
            kirim.putExtra("DATA_FOTO", fotoDariKamera);
            startActivity(kirim);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK && data != null) {
            fotoDariKamera = (Bitmap) data.getExtras().get("data");
            ivFoto.setImageBitmap(fotoDariKamera);
        }
    }
}

