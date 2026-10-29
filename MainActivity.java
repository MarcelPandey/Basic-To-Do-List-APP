package com.example.uts;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText textInput;
    private Button btnTanggal, btnTambah;
    private RecyclerView rvUnder5, rvUnder7, rvFinished;

    private TextView angkaPrioritas, angkaMedium, angkaSantai;
    private RadioGroup radioGroup1;

    private int countPrioritas = 0;
    private int countMedium = 0;
    private int countSantai = 0;

    private ArrayList<Task> listUnder5 = new ArrayList<>();
    private ArrayList<Task> listUnder7 = new ArrayList<>();
    private ArrayList<Task> listFinished = new ArrayList<>();

    private TaskAdapter adapterUnder5;
    private TaskAdapter adapterUnder7;
    private TaskAdapter adapterFinished;

    private int selisihHariDipilih = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. diklarasi view
        textInput = findViewById(R.id.textInput);
        btnTanggal = findViewById(R.id.btnTanggal);
        btnTambah = findViewById(R.id.btnTambah);
        rvUnder5 = findViewById(R.id.daftaritem5);
        rvUnder7 = findViewById(R.id.daftaritem7);
        rvFinished = findViewById(R.id.daftarselesai);
        angkaPrioritas = findViewById(R.id.angkaPrioritas);
        angkaMedium = findViewById(R.id.angkaMedium);
        angkaSantai = findViewById(R.id.angkaSantai);
        radioGroup1 = findViewById(R.id.radioGroup1);
        angkaPrioritas.setText("0");
        angkaMedium.setText("0");
        angkaSantai.setText("0");

        // 2. Set LayoutManager untuk RecyclerView
        rvUnder5.setLayoutManager(new LinearLayoutManager(this));
        rvUnder7.setLayoutManager(new LinearLayoutManager(this));
        rvFinished.setLayoutManager(new LinearLayoutManager(this));

        // 3. Inisialisasi Adapter dengan Callback Listener
        adapterUnder5 = new TaskAdapter(listUnder5, new TaskAdapter.OnTaskChangeListener() {
            @Override
            public void onCheckChanged(int position) {
                Task task = listUnder5.remove(position);
                task.setFinished(true);
                listFinished.add(task);

                adapterUnder5.notifyDataSetChanged();
                adapterFinished.notifyDataSetChanged();
            }
        });

        adapterUnder7 = new TaskAdapter(listUnder7, new TaskAdapter.OnTaskChangeListener() {
            @Override
            public void onCheckChanged(int position) {
                Task task = listUnder7.remove(position);
                task.setFinished(true);
                listFinished.add(task);

                adapterUnder7.notifyDataSetChanged();
                adapterFinished.notifyDataSetChanged();
            }
        });

        adapterFinished = new TaskAdapter(listFinished, new TaskAdapter.OnTaskChangeListener() {
            @Override
            public void onCheckChanged(int position) {
                Task task = listFinished.remove(position);
                task.setFinished(false);
                listUnder5.add(task);

                adapterFinished.notifyDataSetChanged();
                adapterUnder5.notifyDataSetChanged();
            }
        });

        rvUnder5.setAdapter(adapterUnder5);
        rvUnder7.setAdapter(adapterUnder7);
        rvFinished.setAdapter(adapterFinished);



        // 4. Kalender untuk Pilih Tanggal
        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar selectedCal = Calendar.getInstance();
                        selectedCal.set(selectedYear, selectedMonth, selectedDay);

                        long diff = selectedCal.getTimeInMillis() - System.currentTimeMillis();
                        selisihHariDipilih = (int) (diff / (1000 * 60 * 60 * 24)) + 1;

                        btnTanggal.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    }
                }, year, month, day);

                dialog.show();
            }
        });

        // 5. Tombol Tambah Task
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = textInput.getText().toString().trim();

                if (nama.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Isi nama task terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                //memanggil kelas Task
                Task newTask = new Task(nama, selisihHariDipilih);

                if (selisihHariDipilih < 5) {
                    listUnder5.add(newTask);
                    adapterUnder5.notifyDataSetChanged();
                } else {
                    listUnder7.add(newTask);
                    adapterUnder7.notifyDataSetChanged();
                }

                int selectedRadioId = radioGroup1.getCheckedRadioButtonId();

                if (selectedRadioId == R.id.btnPrioritas) {
                    countPrioritas++;
                    angkaPrioritas.setText(String.valueOf(countPrioritas));
                } else if (selectedRadioId == R.id.btnMedium) {
                    countMedium++;
                    angkaMedium.setText(String.valueOf(countMedium));
                } else if (selectedRadioId == R.id.btnChill) {
                    countSantai++;
                    angkaSantai.setText(String.valueOf(countSantai));
                }

                textInput.setText("");
                btnTanggal.setText("Pilih tanggal");
                selisihHariDipilih = 0;
            }
        });
    }
}
