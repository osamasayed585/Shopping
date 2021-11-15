package com.example.shopping.ui.account

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.shopping.databinding.FragmentAccountBinding
import com.example.shopping.util.AccountStatus
import com.example.shopping.util.view.CircleTransform
import com.squareup.picasso.Picasso
import java.io.IOException

class AccountFragment : Fragment() {

    private lateinit var viewModel: AccountViewModel
    private lateinit var binding: FragmentAccountBinding
    private val GALLERY = 1
    private val CAMERA = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        statusEditText(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AccountViewModel::class.java]
        var status = true
        viewModel.getUserInformation()

        viewModel.statusProgressBar.observe(viewLifecycleOwner,{
            initProgressBar(it)
        })

        viewModel.statusAccount.observe(viewLifecycleOwner, {
            when (it) {
                is AccountStatus.SUCCESS -> {
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
                is AccountStatus.SOME_ERROR -> {
                    Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.dataViewModel.observe(viewLifecycleOwner,{
            binding.accountName.setText(it.data.name)
            binding.accountEmail.setText(it.data.email)
            binding.accountPhone.setText(it.data.phone)
            Picasso.get().load(it.data.image)
                .centerCrop()
                .fit()
                .transform(CircleTransform())
                .into(binding.accountImage)
        })

        binding.accountUpdate.setOnClickListener {

            if (status) {
                status = false;
                binding.accountUpdate.text = "Done"
                statusEditText(true)
            } else {
                status = true;
                binding.accountUpdate.text = "Update"
                statusEditText(false)
                // todo handle save data using room
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            }

        }

        binding.accountChangeImage.setOnClickListener {
            showPictureDialog()
        }
    }

    private fun initProgressBar(status: Boolean) {
       binding.accountProgressBar.isVisible = status
    }

    private fun statusEditText(status: Boolean) {
        binding.accountName.isFocusable = status
        binding.accountName.isFocusableInTouchMode = status
        binding.accountEmail.isFocusable = status
        binding.accountEmail.isFocusableInTouchMode = status
        binding.accountPhone.isFocusable = status
        binding.accountPhone.isFocusableInTouchMode = status
    }

    // code handle open the camera or gallery
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(
            pictureDialogItems
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(context?.contentResolver, contentURI)
                    // todo save image from gallery using room
                    Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show()
                    binding.accountImage.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Failed!", Toast.LENGTH_LONG).show()
                }
            }
        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            binding.accountImage.setImageBitmap(thumbnail)
            // todo save image from camera using room
            Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}