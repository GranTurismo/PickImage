package com.example.pick

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pick.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding:ActivityMainBinding
    val rr = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    if(it.resultCode== RESULT_OK)
    {
        var uri = it.data!!.data!!
        var stream = contentResolver.openInputStream(uri)
        var bmp = BitmapFactory.decodeStream(stream)

        binding.image.setImageBitmap(bmp)
    }
}
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.select.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            rr.launch(intent)
        }
    }
}