package com.kotlinsession.permissionsinkotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    val REQUEST_IMAGE_CAPTURE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()

    }

    private fun initListener() {
        btnRequestStorage.setOnClickListener { view ->
            if (PermissionsUtils.isPermissionGranted(
                    this,
                    PermissionsUtils.SD_WRITE_PERMISSIONS,
                    PermissionsUtils.REQUEST_WRITE_STORAGE
                )
            ) {
                Toast.makeText(this, "Request Already Granted", Toast.LENGTH_SHORT).show()

            }

        }

        btnRequestCamera.setOnClickListener { view ->
            if (PermissionsUtils.isPermissionGranted(
                    this,
                    PermissionsUtils.CAMERA_PERMISSIONS,
                    PermissionsUtils.REQUEST_CAMERA
                )
            ) {
                dispatchTakePictureIntent()

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (PermissionsUtils.isPermissionResultGranted(grantResults)) {
            if (requestCode == PermissionsUtils.REQUEST_WRITE_STORAGE) {

                Toast.makeText(this, "Request Granted", Toast.LENGTH_SHORT).show()
            } else if (requestCode == PermissionsUtils.REQUEST_CAMERA) {
                dispatchTakePictureIntent()
            }
        } else {

            Toast.makeText(this, "Request Denied", Toast.LENGTH_SHORT).show()
        }

    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data.extras.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }
}
