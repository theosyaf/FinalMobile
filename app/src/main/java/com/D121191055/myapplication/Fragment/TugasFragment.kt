package com.D121191055.myapplication.Fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121191055.myapplication.TambahActivity
import com.D121191055.myapplication.database.UserAdapter
import com.D121191055.myapplication.database.appDatabase
import com.D121191055.myapplication.database.user
import com.D121191055.myapplication.databinding.FragmentTugasBinding
import kotlinx.coroutines.launch

class TugasFragment : Fragment() {

    private lateinit var binding: FragmentTugasBinding
    private var mAdapter: UserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTugasBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setAdapter(list: List<user>){
        mAdapter?.setData(list)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val userlist = appDatabase(requireActivity()).getUserDao().getAllUser()
            mAdapter = UserAdapter()
            binding.RTugas.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = mAdapter
                setAdapter(userlist)

                mAdapter?.setOnActionEditListener {
                    val intent = Intent( requireActivity(), TambahActivity::class.java)
                    intent.putExtra("Data",it)
                    startActivity(intent)
                }

                mAdapter?.setOnActionDeleteListener {
                    val builder = AlertDialog.Builder(requireActivity())
                    builder.setMessage("Yakin ingin menghapus ?")
                    builder.setPositiveButton("Yes"){ p0, p1 ->
                        lifecycleScope.launch {
                            appDatabase(requireActivity()).getUserDao().deleteUser(it)
                            val list = appDatabase(requireActivity()).getUserDao().getAllUser()
                            setAdapter(list)
                        }
                        p0.dismiss()
                    }
                    builder.setNegativeButton("No"){ p0,p1 ->
                        p0.dismiss()
                    }

                    val dialog = builder.create()
                    dialog.show()
                }

            }

        }
    }
}