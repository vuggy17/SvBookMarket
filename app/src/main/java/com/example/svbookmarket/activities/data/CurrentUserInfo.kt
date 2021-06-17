import android.net.Uri
import android.util.Log
import com.example.svbookmarket.activities.LoginActivity
import com.example.svbookmarket.activities.model.AppAccount
import com.example.svbookmarket.activities.model.CartModel
import com.example.svbookmarket.activities.model.User
import com.example.svbookmarket.activities.model.UserDeliverAddress
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

public class CurrentUserInfo private constructor(var currentProfile: AppAccount = AppAccount(),
                                                 var lstUserCart: MutableList<CartModel> = mutableListOf(),
                                                 var lstUserDeliverAddress: MutableList<UserDeliverAddress> = mutableListOf())
{
    init {
        getDataFromDb()
    }

    private fun getDataFromDb()
    {
        // snap for Profile
        var ref = FirebaseFirestore.getInstance().collection("accounts").document(LoginActivity.recentAccountLogin.email)
        ref.addSnapshotListener { snapshot, e ->
            e?.let {
                Log.d("app-db-error", it.message!!)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                currentProfile.email = snapshot["email"].toString()
                currentProfile.password = snapshot["password"].toString()
                var userMap = snapshot["user"] as Map<*, *>
                val recentUser: User = User(
                    fullName = userMap["fullName"].toString(),
                    gender = userMap["gender"].toString(),
                    birthDay = userMap["birthDay"].toString(),
                    phoneNumber = userMap["phoneNumber"].toString(),
                    addressLane = userMap["addressLane"].toString(),
                    city = userMap["city"].toString(),
                    district = userMap["district"].toString(),
                )
                currentProfile.user = recentUser
            }


            // Snap for Delivery Address
            var refToDeliveryAddress = ref.collection("userDeliverAddresses")
            refToDeliveryAddress.addSnapshotListener { snapshot, e ->
                e?.let {
                    Log.d("app-db-error", it.message!!)
                    return@addSnapshotListener
                }

                for (dc in snapshot!!.documentChanges)
                {
                    when(dc.type)
                    {
                        DocumentChange.Type.ADDED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            var bool = data["isChose"].toString() == "true"
                            lstUserDeliverAddress.add(UserDeliverAddress(dc.document.id, data["fullName"].toString(), data["phoneNumber"].toString(),
                            data["AddressLane"].toString(), data["District"].toString(), data["City"].toString(), bool))
                        }
                        DocumentChange.Type.MODIFIED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            var bool = data["isChose"].toString() == "true"
                            for(i in 0 until lstUserDeliverAddress.size)
                            {
                                if (lstUserDeliverAddress[i].id == dc.document.id)
                                {
                                    lstUserDeliverAddress[i].fullName =  data["fullName"].toString()
                                    lstUserDeliverAddress[i].addressLane =  data["AddressLane"].toString()
                                    lstUserDeliverAddress[i].phoneNumber =  data["phoneNumber"].toString()
                                    lstUserDeliverAddress[i].city = data["City"].toString()
                                    lstUserDeliverAddress[i].district= data["District"].toString()
                                    lstUserDeliverAddress[i].isChose = bool
                                    break;
                                }
                            }
                        }
                        DocumentChange.Type.REMOVED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            for(i in 0 until lstUserDeliverAddress.size)
                            {
                                if (lstUserDeliverAddress[i].id == dc.document.id)
                                {
                                    lstUserDeliverAddress.removeAt(i)
                                }
                            }
                        }
                    }
                }
            }

            //snap for user current cart list
            var refToUserCart = ref.collection("userCart")
            refToUserCart.addSnapshotListener { snapshot, e ->
                e?.let {
                    Log.d("app-db-error", it.message!!)
                    return@addSnapshotListener
                }
                for (dc in snapshot!!.documentChanges)
                {
                    when(dc.type)
                    {
                        DocumentChange.Type.ADDED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            var bool = data["isChose"].toString() == "true"
                            lstUserCart.add(
                                CartModel(dc.document.id, Uri.parse(data["image"].toString()),data["title"].toString(), data["author"].toString(),
                                data["Quantity"].toString().toInt(), data["price"].toString().toLong(), bool)
                            )
                        }
                        DocumentChange.Type.MODIFIED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            var bool = data["isChose"].toString() == "true"
                            for(i in 0 until lstUserCart.size)
                            {
                                if (lstUserCart[i].id == dc.document.id)
                                {
                                    lstUserCart[i].name =  data["title"].toString()
                                    lstUserCart[i].author =  data["author"].toString()
                                    lstUserCart[i].quantity =  data["Quantity"].toString().toInt()
                                    lstUserCart[i].imgUrl = Uri.parse(data["image"].toString())
                                    lstUserCart[i].price= data["price"].toString().toLong()
                                    lstUserCart[i].isChose = bool
                                    break;
                                }
                            }
                        }
                        DocumentChange.Type.REMOVED -> {
                            var data:MutableMap<String?, Any?> = dc.document.data
                            for(i in 0 until lstUserCart.size)
                            {
                                if (lstUserCart[i].id == dc.document.id)
                                {
                                    lstUserCart.removeAt(i)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private object Holder { val INSTANCE = CurrentUserInfo() }

    companion object {
        @JvmStatic
        fun getInstance(): CurrentUserInfo{
            return Holder.INSTANCE
        }
    }
}

