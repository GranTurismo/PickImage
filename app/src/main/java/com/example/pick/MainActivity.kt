package com.example.pick

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pick.databinding.ActivityMainBinding
import java.security.Permission
import java.security.Permissions

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    val rr = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK)
        {
            var bmp = (it.data!!.extras!!.get("data") as Bitmap)
            binding.image.setImageBitmap(bmp)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.select.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 0)
            } else
            {
                var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                rr.launch(intent)
            }
        }
    }
}