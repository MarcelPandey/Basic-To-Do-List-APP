# Basic-To-Do-List-APP
Membuat Aplikasi to do list simple sebagai Penilaian untuk UTS mata kuliah Pemrograman Mobile

Fitur : 
Memasukkan task, memasukkan tanggal task, user bisa menentukan apakah prioritasnya tinggi, sedang, rendah, kemudian user bisa menambah task nya 
user bisa mengubah status task menjadi finished, mengedit, dan menghapus

Alur dan pembagian tugas : 
1. Rico = mengerjakan bagian nama apps, sapaan, dan task finished bagian paling bawah 
2. ⁠Glenn = mengerjakan bagian penambahan task, penentuan button prioritas, button tambah, dan status task 
3. ⁠Marcel = mengerjakan bagian unfinished task, bisa mengubah task menjadi 'selesai’ , mengedit task, dan menghapus task


1. Memasukkan Nama Task

* Widget: EditText untuk memasukkan nama task.
* Activity Lifecycle: Mempertahankan input sementara ketika Activity dibuat ulang.
* ConstraintLayout: Mengatur posisi kolom input nama task.

2. Memasukkan Tanggal Task

* Widget: DatePickerDialog untuk memilih tanggal dan Button untuk membuka pemilih tanggal.
* Activity Lifecycle: Mempertahankan tanggal yang dipilih ketika Activity dibuat ulang.
* ConstraintLayout: Mengatur posisi komponen input tanggal.
* SharedPreferences: Menyimpan tanggal sebagai bagian dari data task.

3. Menentukan Prioritas Task

* Widget: RadioButton untuk memilih prioritas tinggi, sedang, atau rendah.
* Activity Lifecycle: Mempertahankan pilihan prioritas sementara ketika Activity dibuat ulang.
* ConstraintLayout: Mengatur posisi pilihan prioritas.
* SharedPreferences: Menyimpan prioritas sebagai bagian dari data task.

4. Menambah Task

* Widget: Button untuk menambahkan task.
* RecyclerView: Menampilkan task baru dalam daftar.
* ConstraintLayout: Mengatur posisi form dan tombol tambah.
* SharedPreferences: Menyimpan data task yang baru ditambahkan.

5. Melihat Daftar Task

* Widget: TextView untuk menampilkan informasi task.
* RecyclerView: Menampilkan seluruh task yang telah ditambahkan.
* ConstraintLayout: Mengatur posisi dan ukuran daftar task.
* SharedPreferences: Membaca data task yang telah tersimpan.

6. Mengubah Status Task Menjadi Finished

* Widget: CheckBox atau Button untuk menandai task selesai.
* RecyclerView: Memperbarui tampilan dan memindahkan task ke daftar Finished jika menggunakan pemisahan daftar.
* SharedPreferences: Menyimpan perubahan status task.

7. Mengedit Task

* Widget: Button atau ImageButton untuk membuka fitur edit dan EditText untuk mengubah nama task.
* RecyclerView: Memperbarui item task setelah diedit.
* ConstraintLayout: Mengatur posisi form edit.
* SharedPreferences: Menyimpan perubahan data task.

8. Menghapus Task

* Widget: Button atau ImageButton untuk menghapus task.
* RecyclerView: Menghapus item dari daftar.
* SharedPreferences: Memperbarui penyimpanan agar task yang dihapus tidak muncul kembali.
