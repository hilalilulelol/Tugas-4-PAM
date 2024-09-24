package com.example.tugas4pa

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private val dataList = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = RecyclerViewAdapter(dataList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val addButton: Button = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            showAddItemDialog()
        }
        addSampleData()
    }

    // Fungsi opsional untuk menambahkan data sample
    private fun addSampleData() {
        dataList.add(DataModel("Mobil", "Xenia RRR"))
        dataList.add(DataModel("Motor", "Vespa Matic"))
        dataList.add(DataModel("Galon", "5 Liter"))
        adapter.notifyDataSetChanged()
    }

    private fun showAddItemDialog() {
        // Inflate layout dialog_add_item.xml
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.etTitle)
        val etDescription = dialogView.findViewById<EditText>(R.id.etDescription)

        // Membuat AlertDialog untuk input data
        val dialog = AlertDialog.Builder(this)
            .setTitle("Tambahkan Barang")
            .setView(dialogView)
            .setPositiveButton("Tambah") { _, _ ->
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()

                // Validasi input kosong
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    addNewData(title, description)
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Kembali", null)
            .create()

        // Mengatur latar belakang dialog dan gaya tombol
        dialog.setOnShowListener {
            // Membuat latar belakang rounded menggunakan GradientDrawable
            val background = android.graphics.drawable.GradientDrawable()
            background.setColor(android.graphics.Color.parseColor("#4CAF50")) // Warna latar belakang
            background.cornerRadius = 50f // Mengatur radius sudut menjadi lebih rounded

            // Mengatur latar belakang dialog menjadi GradientDrawable yang dibuat
            dialog.window?.setBackgroundDrawable(background)

            // Mengubah warna dan gaya teks tombol
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.let { button ->
                button.setTextColor(android.graphics.Color.WHITE) // Ubah warna teks tombol menjadi putih
                button.setTypeface(null, android.graphics.Typeface.BOLD) // Ubah teks menjadi tebal
            }

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.let { button ->
                button.setTextColor(android.graphics.Color.WHITE) // Ubah warna teks tombol menjadi putih
                button.setTypeface(null, android.graphics.Typeface.BOLD) // Ubah teks menjadi tebal
            }
        }

        dialog.show()
    }





    // Fungsi untuk menambah item baru ke RecyclerView
    private fun addNewData(name: String, description: String) {
        val newData = DataModel(name, description)
        dataList.add(newData)
        adapter.notifyItemInserted(dataList.size - 1)
        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
    }


}
