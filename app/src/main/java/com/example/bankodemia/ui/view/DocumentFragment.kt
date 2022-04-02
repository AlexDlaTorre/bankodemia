package com.example.bankodemia.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.core.types.IdentityType
import com.example.bankodemia.databinding.FragmentDocumentBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class DocumentFragment : Fragment() {
    private lateinit var absolutePathImagen: String

    private var _binding: FragmentDocumentBinding? = null
    private val mBinding get() = _binding!!
    private var photoFile: File? = null

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                SharedPreferencesInstance.setStringValue(
                    getString(R.string.saved_image_path),
                    absolutePathImagen
                )
                val image = BitmapFactory.decodeFile(absolutePathImagen)
                try {
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                    val encoded: String = Base64.encodeToString(byteArray, 0, 64, Base64.NO_WRAP)
                    SharedPreferencesInstance.setStringValue(
                        getString(R.string.saved_image_string),
                        encoded
                    )
                } catch (exception: IOException) {
                    exception.printStackTrace()
                }
                mBinding.documentIvTakePhoto.visibility = View.VISIBLE
                mBinding.documentIvTakePhoto.setImageBitmap(image)
                activateButton(
                    fragment = this@DocumentFragment,
                    mBinding.documentBtnUploadInfo,
                    true
                )
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDocumentBinding.inflate(inflater, container, false)
        initializeComponents()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding) {

            when (SharedPreferencesInstance.getStringValue(getString(R.string.saved_document))) {
                IdentityType.INE.value -> documentTvTypeDocument.text = getString(R.string.ine)
                IdentityType.PASSPORT.value -> documentTvTypeDocument.text = getString(R.string.passport)
                IdentityType.MIGRATION_FORM.value -> documentTvTypeDocument.text = getString(R.string.migratoryDocument)
            }

            documentBtnUploadInfo.setOnClickListener {
                findNavController().navigate(R.id.action_ineFragment_to_passwordFragment)
            }

            documentBtnBackToIdentityMenu.setOnClickListener {
                findNavController().navigate(R.id.action_ineFragment_to_identityMenuFragment)
            }

            documentBtnTakePhoto.setOnClickListener {
                checarPermisos()
            }
        }
    }

    private fun checarPermisos() {
        val permisoCamara =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        if (permisoCamara == PackageManager.PERMISSION_GRANTED) {
            lanzarCamara()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                100
            )
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun lanzarCamara() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                photoFile = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.example.bankodemia.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    takePictureIntent.putExtra("REQUEST_TAKE_PHOTO", 100)
                    startForResult.launch(takePictureIntent)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            absolutePathImagen = absolutePath
        }
    }
}