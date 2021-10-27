package com.example.shopping.ui.account

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.databinding.FragmentAccountBinding
import com.example.shopping.util.initToolbar
import com.nex3z.notificationbadge.NotificationBadge
import androidx.core.view.isVisible as isVisible1

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var accountViewModel: AccountViewModel

    private var item_Notification: MenuItem? = null
    var notificationBadgeView: View? = null
    var notificationBadge: NotificationBadge? = null
    var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountViewModel = ViewModelProvider(requireActivity()).get(AccountViewModel::class.java)

        binding.profileLogout.setOnClickListener {
            // toDo I'll implementation this a code next time
            Toast.makeText(context, "I'll implementation this a code next time", Toast.LENGTH_SHORT)
                .show()
        }

        binding.toolbar.inflateMenu(R.menu.top_account_menu)
        item_Notification = binding.toolbar.menu.findItem(R.id.orders)
        notificationBadgeView = item_Notification?.actionView
        notificationBadge =
            notificationBadgeView?.findViewById(R.id.cartNotification)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.fav -> {
                    findNavController().navigate(R.id.action_navigation_account_to_favouriteFragment)
                    true
                }
                R.id.orders -> {
                    findNavController().navigate(R.id.action_navigation_account_to_ordersFragment)
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
        binding.profileAccount.setOnClickListener {

        }
    }
}