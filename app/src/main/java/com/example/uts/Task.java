package com.example.uts;
import java.io.Serializable;

public class Task implements Serializable {
    private String namaTask;
    private int jumlahHari;
    private boolean isFinished;
    private String priority;
    private String tanggal;

    public Task(String namaTask, int jumlahHari, String priority) {
        this.namaTask = namaTask;
        this.jumlahHari = jumlahHari;
        this.isFinished = false;
        this.priority = priority;
        this.tanggal = tanggal;
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

    public String getPriority() {

        return priority;
    }

    public String getTanggal() {

        return tanggal;
    }
}
