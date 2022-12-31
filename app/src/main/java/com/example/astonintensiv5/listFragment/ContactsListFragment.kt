package com.example.astonintensiv5.listFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.astonintensiv5.dataSource.ListContacts
import com.example.astonintensiv5.databinding.FragmentContactListBinding
import com.example.astonintensiv5.detailsFragment.DetailsContactFragment
import com.example.astonintensiv5.extension.addFragment

class ContactsListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding: FragmentContactListBinding
        get() = requireNotNull(_binding)

    private val adapter by lazy {
        ContactsListAdapter(
            context = requireContext(),
            onContactClicked = { position, contact ->
                val detailFragment = DetailsContactFragment
                    .getInstance(position, contact.name, contact.surname, contact.phoneNumber)
                addFragment(detailFragment)
            }
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentContactListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            listContacts.adapter = adapter
            adapter.submitList(ListContacts.listContacts)
            parentFragmentManager
                .setFragmentResultListener(KEY_RESULT, viewLifecycleOwner) { _, bundle ->
                    val changingContact = ListContacts.listContacts[bundle.getInt(KEY_POSITION)]
                    changingContact
                        .changeContact(
                            bundle.getString(KEY_NAME_RESULT),
                            bundle.getString(KEY_SURNAME_RESULT),
                            bundle.getString(KEY_PHONE_RESULT)
                        )
                    ListContacts.listContacts[bundle.getInt(KEY_POSITION)] = changingContact
                    adapter.submitList(ListContacts.listContacts)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_RESULT = "KEY_RESULT"
        const val KEY_POSITION = "KEY_POSITION"
        const val KEY_NAME_RESULT = "KEY_NAME_RESULT"
        const val KEY_SURNAME_RESULT = "KEY_SURNAME_RESULT"
        const val KEY_PHONE_RESULT = "KEY_PHONE_RESULT"
    }
}
