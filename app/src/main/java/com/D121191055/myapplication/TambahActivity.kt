package com.D121191055.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.lifecycle.lifecycleScope
import com.D121191055.myapplication.database.appDatabase
import com.D121191055.myapplication.database.user
import com.D121191055.myapplication.databinding.ActivityTambahBinding
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class TambahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahBinding
    private var pakai : user? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pakai = intent.getSerializableExtra("Daata") as user?

        if (pakai == null) {
            binding.buttonSave.text = "Save"
        } else{
            binding.buttonSave.text = "Update"
            binding.editTitle.setText(pakai?.title.toString())
            binding.editNote.setText(pakai?.note.toString())
        }

        binding.buttonSave.setOnClickListener { addUser() }

    }

    private fun addUser() {
        val judul = binding.editTitle.text.toString()
        val isi = binding.editNote.text.toString()
        val kategori = binding.editKategori.checkedRadioButtonId
        val kategori2 = findViewById<RadioButton>(kategori)
        val kategori3 = "${kategori2.text}"

        lifecycleScope.launch {
            if (pakai == null){
                val User = user(title = judul, note = isi, Kategori = kategori3)
                appDatabase(this@TambahActivity).getUserDao().addUser(User)
                finish()
            }else{
                val u = user(judul,isi,kategori3)
                u.id = pakai?.id ?:0
                appDatabase(this@TambahActivity).getUserDao().updateUser(u)
                finish()

            }
        }


    }
}
