package com.example.shopping.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.databinding.FragmentProfileBinding
import com.hrhera.login.utils.AnimationUtil.slideDown
import com.hrhera.login.utils.Static
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: ProfileViewModel
    private val GALLERY = 1
    private val CAMERA = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.initStatus()

        viewModel.mutableProgressBar.observe(viewLifecycleOwner, {
            initProgressBar(it)
        })

        viewModel.topMessageError.observe(viewLifecycleOwner,{
            initTopErrorMessage(it)
        })

        viewModel.mutableStatus.observe(viewLifecycleOwner, {
            binding.profileTextErrorMessage.text = it
            binding.profileTextLogin.setOnClickListener {
                Static.onUserLogin?.onLogOut()
            }
        })

        viewModel.nameLiveData.observe(viewLifecycleOwner, {
            binding.profileName.text = it.data.name
        })

        binding.ProfileChangeImage.setOnClickListener {
            showPictureDialog()
        }

        binding.profileAccount.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_accountFragment)
        }
        binding.profileLanguage.setOnClickListener {
            // todo
        }
        binding.profilePassword.setOnClickListener {
            // todo
        }
        binding.profileFavourite.setOnClickListener {
            // todo
        }
        binding.profileAbout.setOnClickListener {
            // todo
        }
        binding.profileLogout.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_loginFragment2)
            Static.onUserLogin?.onLogOut()
        }
    }

    fun initProgressBar(status: Boolean) {
        binding.profileProgressBar.isVisible = status
    }

    fun initTopErrorMessage(status: Boolean){
        binding.profileMessageToast.isVisible = status
        binding.profileMessageToast.slideDown(900)
    }

    // code handle open the camera or gallery
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, contentURI)
                    Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show()
                    binding.profileImage.setImageBitmap(bitmap)
                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Failed!", Toast.LENGTH_LONG).show()
                }
            }
        }
        else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            binding.profileImage.setImageBitmap(thumbnail)
            Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
