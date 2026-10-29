package com.example.uts;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
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

    private static final String KEY_LIST_UNDER5 = "key_list_under5";
    private static final String KEY_LIST_UNDER7 = "key_list_under7";
    private static final String KEY_LIST_FINISHED = "key_list_finished";

    private static final String KEY_COUNT_PRIORITAS = "key_count_prioritas";
    private static final String KEY_COUNT_MEDIUM = "key_count_medium";
    private static final String KEY_COUNT_SANTAI = "key_count_santai";

    private static final String KEY_SELISIH_HARI = "key_selisih_hari";
    private static final String KEY_TEKS_TANGGAL = "key_teks_tanggal";


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Simpan List Task
        outState.putSerializable(KEY_LIST_UNDER5, listUnder5);
        outState.putSerializable(KEY_LIST_UNDER7, listUnder7);
        outState.putSerializable(KEY_LIST_FINISHED, listFinished);

        // Simpan Jumlah Counter
        outState.putInt(KEY_COUNT_PRIORITAS, countPrioritas);
        outState.putInt(KEY_COUNT_MEDIUM, countMedium);
        outState.putInt(KEY_COUNT_SANTAI, countSantai);

        // Simpan Data Tanggal Dipilih
        outState.putInt(KEY_SELISIH_HARI, selisihHariDipilih);
        outState.putString(KEY_TEKS_TANGGAL, btnTanggal.getText().toString());
    }

    private void pulihkanData(Bundle state) {
        // 1. Pulihkan ArrayList Task
        ArrayList<Task> restoredUnder5 = (ArrayList<Task>) state.getSerializable(KEY_LIST_UNDER5);
        ArrayList<Task> restoredUnder7 = (ArrayList<Task>) state.getSerializable(KEY_LIST_UNDER7);
        ArrayList<Task> restoredFinished = (ArrayList<Task>) state.getSerializable(KEY_LIST_FINISHED);

        if (restoredUnder5 != null) {
            listUnder5.clear();
            listUnder5.addAll(restoredUnder5);
        }
        if (restoredUnder7 != null) {
            listUnder7.clear();
            listUnder7.addAll(restoredUnder7);
        }
        if (restoredFinished != null) {
            listFinished.clear();
            listFinished.addAll(restoredFinished);
        }

        // Refresh Tampilan RecyclerView
        adapterUnder5.notifyDataSetChanged();
        adapterUnder7.notifyDataSetChanged();
        adapterFinished.notifyDataSetChanged();

        // 2. Pulihkan Angka Counter
        countPrioritas = state.getInt(KEY_COUNT_PRIORITAS, 0);
        countMedium = state.getInt(KEY_COUNT_MEDIUM, 0);
        countSantai = state.getInt(KEY_COUNT_SANTAI, 0);

        angkaPrioritas.setText(String.valueOf(countPrioritas));
        angkaMedium.setText(String.valueOf(countMedium));
        angkaSantai.setText(String.valueOf(countSantai));

        // 3. Pulihkan Tanggal yang Pernah Dipilih
        selisihHariDipilih = state.getInt(KEY_SELISIH_HARI, 0);
        String teksTanggal = state.getString(KEY_TEKS_TANGGAL, "Pilih tanggal");
        btnTanggal.setText(teksTanggal);
    }

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

        rvUnder5.setLayoutManager(new LinearLayoutManager(this));
        rvUnder7.setLayoutManager(new LinearLayoutManager(this));
        rvFinished.setLayoutManager(new LinearLayoutManager(this));

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


        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = textInput.getText().toString().trim();

                if (nama.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Isi nama task terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                String priority = "Prioritas";
                int selectedRadioId = radioGroup1.getCheckedRadioButtonId();

                if (selectedRadioId == R.id.btnPrioritas) {
                    priority = "Prioritas";
                } else if (selectedRadioId == R.id.btnMedium) {
                    priority = "Medium";
                } else if (selectedRadioId == R.id.btnChill) {
                    priority = "Santai";
                }

                Task newTask = new Task(nama, selisihHariDipilih,priority);

                if (selisihHariDipilih < 5) {
                    listUnder5.add(newTask);
                    adapterUnder5.notifyDataSetChanged();
                } else {
                    listUnder7.add(newTask);
                    adapterUnder7.notifyDataSetChanged();
                }

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

        // Panggil dipaling bawah metode onCreate()
        if (savedInstanceState != null) {
            pulihkanData(savedInstanceState);
        }
    }
}