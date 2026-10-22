# Basic-To-Do-List-APP
Membuat Aplikasi to do list simple sebagai Penilaian untuk UTS mata kuliah Pemrograman Mobile

Fitur : 
Memasukkan task, memasukkan tanggal task, user bisa menentukan apakah prioritasnya tinggi, sedang, rendah, kemudian user bisa menambah task nya 
user bisa mengubah status task menjadi finished, mengedit, dan menghapus

## Alur dan Pembagian Tugas

1. **Rico** — Mengerjakan nama aplikasi, sapaan, dan bagian Finished Task di paling bawah.
2. **Glenn** — Mengerjakan penambahan task, tombol prioritas, tombol tambah, dan status task.
3. **Marcel** — Mengerjakan Unfinished Task, mengubah status task menjadi selesai, mengedit task, dan menghapus task.

---

## Detail Fitur dan Implementasi

### 1. Memasukkan Nama Task
- **Widget:** EditText untuk memasukkan nama task.
- **Activity Lifecycle:** Mempertahankan input sementara ketika Activity dibuat ulang.
- **ConstraintLayout:** Mengatur posisi kolom input nama task.

### 2. Memasukkan Tanggal Task
- **Widget:** DatePickerDialog dan Button untuk memilih tanggal.
- **Activity Lifecycle:** Mempertahankan tanggal yang dipilih ketika Activity dibuat ulang.
- **ConstraintLayout:** Mengatur posisi komponen input tanggal.
- **SharedPreferences:** Menyimpan tanggal sebagai bagian dari data task.

### 3. Menentukan Prioritas Task
- **Widget:** RadioButton untuk memilih prioritas tinggi, sedang, atau rendah.
- **Activity Lifecycle:** Mempertahankan pilihan prioritas sementara ketika Activity dibuat ulang.
- **ConstraintLayout:** Mengatur posisi pilihan prioritas.
- **SharedPreferences:** Menyimpan prioritas task.

### 4. Menambah Task
- **Widget:** Button untuk menambahkan task.
- **RecyclerView:** Menampilkan task baru dalam daftar.
- **ConstraintLayout:** Mengatur posisi form dan tombol tambah.
- **SharedPreferences:** Menyimpan task yang baru ditambahkan.

### 5. Melihat Daftar Task
- **Widget:** TextView untuk menampilkan informasi task.
- **RecyclerView:** Menampilkan seluruh task yang tersimpan.
- **ConstraintLayout:** Mengatur posisi dan ukuran daftar task.
- **SharedPreferences:** Membaca data task yang tersimpan.

### 6. Mengubah Status Task Menjadi Finished
- **Widget:** CheckBox atau Button untuk menandai task selesai.
- **RecyclerView:** Memperbarui tampilan task setelah selesai.
- **SharedPreferences:** Menyimpan perubahan status task.

### 7. Mengedit Task
- **Widget:** Button atau ImageButton untuk membuka fitur edit dan EditText untuk mengubah nama task.
- **RecyclerView:** Memperbarui item task setelah diedit.
- **ConstraintLayout:** Mengatur posisi form edit.
- **SharedPreferences:** Menyimpan perubahan data task.

### 8. Menghapus Task
- **Widget:** Button atau ImageButton untuk menghapus task.
- **RecyclerView:** Menghapus item dari daftar.
- **SharedPreferences:** Memperbarui penyimpanan agar task yang dihapus tidak muncul kembali.
