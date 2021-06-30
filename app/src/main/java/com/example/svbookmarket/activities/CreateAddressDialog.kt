package com.example.svbookmarket.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.example.svbookmarket.activities.viewmodel.CreateAddressViewModel
import com.example.svbookmarket.databinding.DialogCreateAddressBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateAddressDialog(private val listener: OnCreateAddressListener) :
    BottomSheetDialogFragment() {

    private lateinit var binding: DialogCreateAddressBinding
    private lateinit var viewmodel: CreateAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCreateAddressBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(this).get(CreateAddressViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val name = binding.edtName
        val phone = binding.edtPhoneNumber
        val lane = binding.edtAddressLane
        val district = binding.edtDistrict
        val city = binding.edtCity

        val addBtn = binding.btnAddAddress


        viewmodel.createAddressFormState.observe(this, Observer@{
            val state = it ?: return@Observer

            addBtn.apply {
                isEnabled = it.isDataValid
                if (isEnabled)
                    setBackgroundColor(binding.root.resources.getColor(R.color.blue_dark))
            }

            if (state.nameError != null)
                name.error = getString(state.nameError)
            if (state.laneError != null)
                lane.error = getString(state.laneError)
            if (state.districtError != null)
                district.error = getString(state.districtError)
            if (state.cityError != null)
                city.error = getString(state.cityError)
            if(state.phoneError !=null)
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
                            listener.onCreateAddress(bind())
                            this@CreateAddressDialog.dismiss()
                        }
                }
                false
            }

            addBtn.setOnClickListener {
                listener.onCreateAddress(bind())
                this@CreateAddressDialog.dismiss()
            }

        }
    }

    private fun bind(): UserDeliverAddress {

        val namet = binding.edtName.text.toString()
        val phonet = binding.edtPhoneNumber.text.toString()
        val lanet = binding.edtAddressLane.text.toString()
        val districtt = binding.edtDistrict.text.toString()
        val cityt = binding.edtCity.text.toString()


        return UserDeliverAddress("", namet, phonet, lanet, districtt, cityt)
    }

}


