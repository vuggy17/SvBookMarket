import android.net.Uri
import android.util.Log
import com.example.svbookmarket.activities.LoginActivity
import com.example.svbookmarket.activities.common.Constants
import com.example.svbookmarket.activities.model.*
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase

public class CurrentUserInfo private constructor(var currentProfile: AppAccount = AppAccount(),
                                                 var lstUserCart: MutableList<Cart> = mutableListOf(),
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
            refToDeliveryAddress.addSnapshotListener (object :
                EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                error?.let {
                    Log.d("app-db-error", it.message!!)
                    return
                }

                    val addressList: MutableList<UserDeliverAddress> = ArrayList()
                for (dc in value!!) {
                            var data: MutableMap<String?, Any?> = dc.data
                            var bool = data["chose"].toString() == "true"
                            addressList.add(
                                UserDeliverAddress(
                                    dc.id,
                                    data["fullName"].toString(),
                                    data["phoneNumber"].toString(),
                                    data["addressLane"].toString(),
                                    data["district"].toString(),
                                    data["city"].toString(),
                                    bool
                                )
                            )
                        }
                    lstUserDeliverAddress =addressList
                    }
                }
            )

            //snap for user current cart list
            var refToUserCart = ref.collection("userCart")
            refToUserCart.addSnapshotListener  (object :
                EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.w(Constants.VMTAG, "Listen failed.", error)
                        return
                    }

                    val cartList: MutableList<Cart> = ArrayList()
                    for (doc in value!!) {
                        var bool = doc.data["isChose"].toString() == "true"
                        var item = Cart("", doc.data["image"].toString(),doc.data["title"].toString(), doc.data["author"].toString(),
                            doc.data["Quantity"].toString().toInt(), doc.data["price"].toString().toLong(), bool)
                        item.id = doc.id
                        cartList.add(item)
                    }
                    lstUserCart = cartList
                }

            })
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

