package com.example.uts;

public class Task {
    private String namaTask;
    private int jumlahHari; // Misal: 3 hari, 6 hari
    private boolean isFinished;

    public Task(String namaTask, int jumlahHari) {
        this.namaTask = namaTask;
        this.jumlahHari = jumlahHari;
        this.isFinished = false;
    }

    public String getNamaTask() {

        return namaTask;
    }

    public void setNamaTask(String namaTask) {

        this.namaTask = namaTask;
    }

    public int getJumlahHari() {

        return jumlahHari;
    }

    public boolean isFinished() {

        return isFinished;
    }

    public void setFinished(boolean finished) {

        isFinished = finished;
    }
}
