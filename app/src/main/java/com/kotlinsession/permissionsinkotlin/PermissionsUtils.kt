package com.kotlinsession.permissionsinkotlin

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionsUtils {

    companion object {

        val REQUEST_WRITE_STORAGE = 112

        val SD_WRITE_PERMISSIONS = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )


        val REQUEST_CALL = 113

        val CALL_PERMISSIONS = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE
        )

        val REQUEST_CONTACT = 115

        val CONTACT_PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS
        )


        val REQUEST_LOCATION = 116

        val LOCATION_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val REQUEST_SMS = 117

        val SMS_PERMISSIONS = arrayOf(
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS
        )


        val REQUEST_CAMERA = 118

        val CAMERA_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )


        fun isPermissionGranted(
            activity: Activity,
            permissions: Array<String>,
            requestCode: Int
        ): Boolean {
            var requirePermission = false

            if (permissions != null && permissions.isNotEmpty()) {
                for (permission in permissions) {
                    if (ContextCompat.checkSelfPermission(
                            activity,
                            permission
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        requirePermission = true
                        break
                    }
                }
            }

            if (requirePermission) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode);
                return false
            } else {
                return true
            }
        }


        fun isPermissionGranted(
            activity: Activity,
            fragment: Fragment,
            permissions: Array<String>,
            requestCode: Int
        ): Boolean {
            var requirePermission = false

            if (permissions != null && permissions.isNotEmpty()) {
                for (permission in permissions) {
                    if (ContextCompat.checkSelfPermission(
                            activity,
                            permission
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        requirePermission = true
                        break
                    }
                }
            }

            if (requirePermission) {
                fragment.requestPermissions(permissions, requestCode)
                return false
            } else {
                return true
            }
        }

        fun isPermissionResultGranted(grantResults: IntArray): Boolean {
            var allGranted = true

            if (grantResults != null && grantResults.isNotEmpty()) {
                for (i in grantResults) {
                    if (i != PackageManager.PERMISSION_GRANTED) {
                        allGranted = false
                        break
                    }
                }
            }
            return allGranted
        }

    }
}