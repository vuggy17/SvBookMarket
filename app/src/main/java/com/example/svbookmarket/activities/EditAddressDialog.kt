package com.example.svbookmarket.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.example.svbookmarket.activities.viewmodel.EditAddressViewModel
import com.example.svbookmarket.databinding.ActivityEditAdressBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditAddressDialog(
    private val editListener: OnEditAddressListener,
    private val address: UserDeliverAddress,
    private val deleteListener: OnDeleteAddressListener,
) : BottomSheetDialogFragment() {

    private lateinit var binding: ActivityEditAdressBinding
    private lateinit var viewmodel: EditAddressViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditAdressBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(this).get(EditAddressViewModel::class.java)

        display(address)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding.edtName
        val phone = binding.edtPhoneNumber
        val lane = binding.edtAddressLane
        val district = binding.edtDistrict
        val city = binding.edtCity

        val editBtn = binding.btnEditAddress
        val delBtn = binding.edDelete

        //delete btn
        delBtn.setOnClickListener {
            deleteListener.onDeleteAddress(bind())
            this.dismiss()
            Toast.makeText(binding.root.context, "Address deleted successful!", Toast.LENGTH_SHORT).show()
        }

        viewmodel.createAddressFormState.observe(this, Observer@{
            val state = it ?: return@Observer

            editBtn.apply {
                isEnabled = it.isDataValid
                if (isEnabled)
                    setBackgroundColor(binding.root.resources.getColor(R.color.green))
            }

            if (state.nameError != null)
                name.error = getString(state.nameError)
            if (state.laneError != null)
                lane.error = getString(state.laneError)
            if (state.districtError != null)
                district.error = getString(state.districtError)
            if (state.cityError != null)
                city.error = getString(state.cityError)
            if (state.phoneError != null)
                phone.error = getString(state.phoneError)
        })

        name.doAfterTextChanged {
            viewmodel.formDataChanged(bind())
        }
        phone.doAfterTextChanged {
            viewmodel.formDataChanged(bind())
        }
        lane.doAfterTextChanged {
            viewmodel.formDataChanged(bind())
        }
        district.doAfterTextChanged {
            viewmodel.formDataChanged(bind())
        }
        city.apply {
            doAfterTextChanged {
                viewmodel.formDataChanged(bind())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        run {
                            this@EditAddressDialog.dismiss()
                        }
                }
                false
            }

            editBtn.setOnClickListener {
                editListener.onEditAddress(bind())
                this@EditAddressDialog.dismiss()
            }

        }

    }

    /**
     * combine information into UserDeliverAddress object
     */
    private fun bind(): UserDeliverAddress {

        val namet = binding.edtName.text.toString()
        val phonet = binding.edtPhoneNumber.text.toString()
        val lanet = binding.edtAddressLane.text.toString()
        val districtt = binding.edtDistrict.text.toString()
        val cityt = binding.edtCity.text.toString()
        val idt = address.id
        val chosen = address.chose


        return UserDeliverAddress(idt, namet, phonet, lanet, districtt, cityt, chosen)
    }

    /**
     * display information to screen
     */
    private fun display(item: UserDeliverAddress) {
        val name = binding.edtName
        val phone = binding.edtPhoneNumber
        val lane = binding.edtAddressLane
        val district = binding.edtDistrict
        val city = binding.edtCity

        name.setText(item.fullName)
        phone.setText(item.phoneNumber)
        lane.setText(item.addressLane)
        district.setText(item.district)
        city.setText(item.city)
    }


    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val rootView = View.inflate(context, R.layout.activity_edit_adress, null)
        dialog.setContentView(rootView)

        val bottomSheet = dialog.window?.findViewById(R.id.design_bottom_sheet) as FrameLayout
        val behaviour = BottomSheetBehavior.from(bottomSheet)

        behaviour.peekHeight = 1600


        // TODO: 11/06/2021 vadilation input 
    }
}