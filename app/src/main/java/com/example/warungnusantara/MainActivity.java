package com.example.warungnusantara;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNama;
    RadioGroup radioGroup;
    RadioButton rbDineIn, rbTakeAway;
    CheckBox cbPedas, cbAlat;
    Button btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hubungkan variabel dengan ID di XML
        etNama = findViewById(R.id.etNama);
        radioGroup = findViewById(R.id.radioGroupTipe);
        rbDineIn = findViewById(R.id.rbDineIn);
        rbTakeAway = findViewById(R.id.rbTakeAway);
        cbPedas = findViewById(R.id.cbPedas);
        cbAlat = findViewById(R.id.cbAlatMakan);
        btnPesan = findViewById(R.id.btnPesan);

        // Event Klik Tombol
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesOrder();
            }
        });
    }

    private void prosesOrder() {
        // Ambil teks dari EditText
        String nama = etNama.getText().toString().trim();

        // --- VALIDASI ---
        if(nama.isEmpty()){
            etNama.setError("Wajib diisi!");
            Toast.makeText(this, "Silakan isi nama dulu", Toast.LENGTH_SHORT).show();
            return; // Hentikan proses
        }

        // Ambil pilihan Radio Button
        String tipePesanan;
        if(rbDineIn.isChecked()){
            tipePesanan = "Makan di Tempat";
        } else {
            tipePesanan = "Bawa Pulang";
        }

        // Ambil pilihan CheckBox
        String tambahan = "";
        if(cbPedas.isChecked()){
            tambahan += "- Extra Pedas\n";
        }
        if(cbAlatMakan.isChecked()){
            tambahan += "- Pakai Alat Makan\n";
        }
        if(tambahan.equals("")){
            tambahan = "Tidak ada tambahan";
        }

        // --- TAMPILKAN ALERT DIALOG ---
        AlertDialog.Builder pesan = new AlertDialog.Builder(this);
        pesan.setTitle("Konfirmasi Pesanan");
        pesan.setMessage(
                "Nama : " + nama + "\n\n" +
                "Tipe : " + tipePesanan + "\n\n" +
                "Catatan : \n" + tambahan
        );

        pesan.setPositiveButton("OK", (dialog, which) -> {
            Toast.makeText(this, "Pesanan Diproses!", Toast.LENGTH_SHORT).show();
            // Kosongkan form
            etNama.setText("");
            radioGroup.check(R.id.rbDineIn);
            cbPedas.setChecked(false);
            cbAlatMakan.setChecked(false);
        });

        pesan.setNegativeButton("Batal", null);
        pesan.show("Add MainActivity.java");
    }
}

