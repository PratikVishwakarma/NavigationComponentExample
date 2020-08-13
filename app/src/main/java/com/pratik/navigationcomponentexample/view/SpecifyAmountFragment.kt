package com.pratik.navigationcomponentexample.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pratik.navigationcomponentexample.Money
import com.pratik.navigationcomponentexample.R
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal

class SpecifyAmountFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var recipient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = requireArguments()["recipient"].toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        send_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
        tvRecipient.text = "Sending money to $recipient"
    }

    override fun onClick(v: View?) {
        when (v) {
            send_btn -> {
                if (!TextUtils.isEmpty(input_amount.text.toString())) {
                    val bundle = bundleOf(
                        "amount" to Money(BigDecimal(input_amount.text.toString())),
                        "recipient" to recipient
                    )
                    navController.navigate(
                        R.id.action_specifyAmountFragment_to_confirmationFragment,
                        bundle
                    )
                }
                else input_amount.error = "Enter amount"
            }
            cancel_btn -> requireActivity().onBackPressed()
        }
    }
}