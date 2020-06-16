package com.example.myapplication


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import java.io.File
import java.util.*


/**
 * RealWear Development Software, Source Code and Object Code
 * (c) RealWear, Inc. All rights reserved.
 * <p>
 * Contact info@realwear.com for further information about the use of this code.
 */


/**
 * Activity that shows how to use the camera to take a picture on a HMT-1 device
 */
class PhotoActivity : Activity() {
    private var mImageView: ImageView? = null
    private var contentUri: Uri? = null

    /**
     * Called when the activity is created
     *
     * @param savedInstanceState See Android docs
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/

        setContentView(R.layout.photo_main)

        mImageView = findViewById<View>(R.id.camera_image_view) as ImageView

        val mStartActBtn2 = findViewById<Button>(com.example.myapplication.R.id.button_photo)

        mStartActBtn2.setOnClickListener {
            //start activity intent
            startActivity(Intent(this@PhotoActivity, SelectionalActivity::class.java))
        }
    }

    /**
     * Listener for when the basic camera button is clicked
     *
     * @param view The launch camera button
     */
    fun onLaunchCameraBasic(view: View?) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // OR ACTION_VIDEO_CAPTURE
        startActivityForResult(intent, BASIC_CAMERA_REQUEST_CODE)
    }

    /**
     * Listener for when the FileProvider camera button is clicked
     *
     * @param view The FileProvider camera button
     */
    fun onLaunchCameraFileProvider(view: View?) {
        val mediaStorageDir =
            getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val file =
            File(mediaStorageDir, "devexamples-" + UUID.randomUUID() + ".jpg")
        contentUri = FileProvider.getUriForFile(
            applicationContext,
            applicationContext.packageName + ".fileprovider",
            file
        )
        val captureIntent =
            Intent(MediaStore.ACTION_IMAGE_CAPTURE) // OR ACTION_VIDEO_CAPTURE
        captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
        startActivityForResult(
            captureIntent,
            FILEPROVIDER_CAMERA_REQUEST_CODE
        )
    }

    /**
     * Listener for result from external activities. Receives image data from camera.
     *
     * @param requestCode See Android docs
     * @param resultCode  See Android docs
     * @param data        See Android docs
     */
    public override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent
    ) {
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                BASIC_CAMERA_REQUEST_CODE -> {
                    val photo = data.extras
                        ?.getParcelable<Bitmap>(EXTRA_RESULT)
                    mImageView!!.setImageBitmap(photo)
                }
                FILEPROVIDER_CAMERA_REQUEST_CODE -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    intent.setDataAndType(contentUri, "image/*")
                    intent.putExtra("zoom", "2")
                    startActivityForResult(intent, 1234)
                }
            }
        }
    }

    companion object {
        // Request code identifying camera events
        private const val BASIC_CAMERA_REQUEST_CODE = 1889
        private const val FILEPROVIDER_CAMERA_REQUEST_CODE = 1998

        // Identifier for the image returned by the camera
        private const val EXTRA_RESULT = "data"
    }
}