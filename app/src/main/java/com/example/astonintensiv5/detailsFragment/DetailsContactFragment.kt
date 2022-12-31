package com.example.astonintensiv5.detailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.astonintensiv5.databinding.FragmentDetailsContactBinding
import com.example.astonintensiv5.listFragment.ContactsListFragment

class DetailsContactFragment : Fragment() {

    private var _binding: FragmentDetailsContactBinding? = null
    private val binding: FragmentDetailsContactBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailsContactBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            name.text = requireArguments().getString(NAME_KEY)
            surname.text = requireArguments().getString(SURNAME_KEY)
            phone.text = requireArguments().getString(PHONE_KEY)
            buttonChange.setOnClickListener {
                changeName.isVisible = true
                changePhone.isVisible = true
                changeSurname.isVisible = true
                buttonSave.isVisible = true
            }
            buttonSave.setOnClickListener {
                parentFragmentManager
                    .setFragmentResult(
                        ContactsListFragment.KEY_RESULT,
                        bundleOf(
                            ContactsListFragment.KEY_POSITION to requireArguments().getInt(
                                POSITION
                            ),
                            ContactsListFragment.KEY_NAME_RESULT to changeName.text.toString(),
                            ContactsListFragment.KEY_SURNAME_RESULT to changeSurname.text.toString(),
                            ContactsListFragment.KEY_PHONE_RESULT to changePhone.text.toString()
                        )
                    )
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val NAME_KEY = "NAME_KEY"
        private const val SURNAME_KEY = "SURNAME_KEY"
        private const val PHONE_KEY = "PHONE_KEY"
        private const val POSITION = "POSITION"

        fun getInstance(
            position: Int,
            name: String,
            surname: String,
            phone: String
        ): DetailsContactFragment {
            return DetailsContactFragment().apply {
                arguments = bundleOf(
                    POSITION to position,
                    NAME_KEY to name,
                    SURNAME_KEY to surname,
                    PHONE_KEY to phone
                )
            }
        }
    }
}