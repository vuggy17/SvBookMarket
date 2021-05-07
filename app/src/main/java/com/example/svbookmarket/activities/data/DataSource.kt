package com.example.svbookmarket.activities.data

import android.net.Uri
import android.renderscript.Sampler
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.*

class DataSource {
    fun loadCheckoutCard(): MutableList<CheckoutCard> {
        return mutableListOf(
            CheckoutCard("dsadsa", "casc", "adr", "100", "123"),
            CheckoutCard("dsadsa", "casc", "adr", "100", "123"),
            CheckoutCard("dsadsa", "casc", "adr", "100", "123"),
            CheckoutCard("dsadsa", "casc", "adr", "100", "123"),
            CheckoutCard("dsadsa", "casc", "adr", "100", "123"),
            CheckoutCard("dsadsa", "casc", "adr", "100", "123"),

            )
    }

    fun loadCart(): MutableList<CartModel> {
        return mutableListOf(
            CartModel("cc","cc","cc",10,1000 ),
            CartModel("cc","cc","cc",9,200 ),
            CartModel("cc","cc1","cc",8,113 ),
            CartModel("cc","cc","cc",12,1997 ),
            CartModel("cc","cc","cc",3,2001 ),
            CartModel("cc","cc1","cc",1,1000 ),

            )
    }

    fun loadAddressCard(): MutableList<AddressModel> {
        return mutableListOf(
            AddressModel(
                "Khuong Duy",
                "0869256174",
                "Ktx khu A, DHQG",
                "Phuong Linh Trung",
                "Quan Thu Duc",
                "Tp.Ho Chi Minh"
            ),
            AddressModel(
                "Khuong Duy",
                "0869256174",
                "Ktx khu A, DHQG",
                "Phuong Linh Trung",
                "Quan Thu Duc",
                "Tp.Ho Chi Minh"
            ),
            AddressModel(
                "Khuong Duy",
                "0869256174",
                "Ktx khu A, DHQG",
                "Phuong Linh Trung",
                "Quan Thu Duc",
                "Tp.Ho Chi Minh"
            ),
            AddressModel(
                "Khuong Duy",
                "0869256174",
                "Ktx khu A, DHQG",
                "Phuong Linh Trung",
                "Quan Thu Duc",
                "Tp.Ho Chi Minh"
            ),
            AddressModel(
                "Khuong Duy",
                "0869256174",
                "Ktx khu A, DHQG",
                "Phuong Linh Trung",
                "Quan Thu Duc",
                "Tp.Ho Chi Minh"
            ),
            AddressModel(
                "Khuong Duy",
                "0869256174",
                "Ktx khu A, DHQG",
                "Phuong Linh Trung",
                "Quan Thu Duc",
                "Tp.Ho Chi Minh"
            ),
            AddressModel(
                "Khuong Duy",
                "0869256174",
                "Ktx khu A, DHQG",
                "Phuong Linh Trung",
                "Quan Thu Duc",
                "Tp.Ho Chi Minh"
            ),
        )
    }

    fun loadBookFeature(): ArrayList<Book> {
        return arrayListOf(
            Book(
                Uri.parse("https://kienviet.net/wp-content/uploads/2019/08/12-diem-du-lich-phai-den-tai-paris-danh-cho-kts-phan-1-kien-viet.jpg"),
                "Sample", "Sample", 100000, 3.5, "none", 50
            ),
            Book(
                Uri.parse("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.viator.com%2Fen-HK%2Ftours%2FParis%2FCity-of-Lights-Tour%2Fd479-48577P5&psig=AOvVaw2WXscYKUJtXfewcb0iNgfL&ust=1619418661896000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPi8q-LimPACFQAAAAAdAAAAABAN"),
                "Sample", "Sample", 100000, 0.8, "none", 50
            ),
            Book(
                Uri.parse("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.viator.com%2Fen-HK%2Ftours%2FParis%2FCity-of-Lights-Tour%2Fd479-48577P5&psig=AOvVaw2WXscYKUJtXfewcb0iNgfL&ust=1619418661896000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPi8q-LimPACFQAAAAAdAAAAABAN"),
                "Sample", "Sample", 100000, 2.0, "none", 50
            ),
            Book(
                Uri.parse("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.viator.com%2Fen-HK%2Ftours%2FParis%2FCity-of-Lights-Tour%2Fd479-48577P5&psig=AOvVaw2WXscYKUJtXfewcb0iNgfL&ust=1619418661896000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPi8q-LimPACFQAAAAAdAAAAABAN"),
                "Sample", "Sample", 100000, 4.1, "none", 50
            ),
            Book(
                Uri.parse("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.viator.com%2Fen-HK%2Ftours%2FParis%2FCity-of-Lights-Tour%2Fd479-48577P5&psig=AOvVaw2WXscYKUJtXfewcb0iNgfL&ust=1619418661896000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPi8q-LimPACFQAAAAAdAAAAABAN"),
                "Sample", "Sample", 100000, 5.0, "none", 50
            ),
            Book(
                Uri.parse("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.viator.com%2Fen-HK%2Ftours%2FParis%2FCity-of-Lights-Tour%2Fd479-48577P5&psig=AOvVaw2WXscYKUJtXfewcb0iNgfL&ust=1619418661896000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPi8q-LimPACFQAAAAAdAAAAABAN"),
                "Sample", "Sample", 100000, 4.1, "none", 50
            ),
            Book(
                Uri.parse("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.viator.com%2Fen-HK%2Ftours%2FParis%2FCity-of-Lights-Tour%2Fd479-48577P5&psig=AOvVaw2WXscYKUJtXfewcb0iNgfL&ust=1619418661896000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPi8q-LimPACFQAAAAAdAAAAABAN"),
                "Sample", "Sample", 100000, 4.1, "none", 50
            )
        )
    }
    fun loadCategory(): ArrayList<Category>{
        return arrayListOf(
            Category(Uri.parse(""), "Fictions"),
            Category(Uri.parse(""), "Comics"),
            Category(Uri.parse(""), "Novels"),
            Category(Uri.parse(""), "Business"),
            Category(Uri.parse(""), "Technology"),
        )
    }
    fun loadAdvertise(): ArrayList<Advertise>{
        return  arrayListOf(
            Advertise("25/4/2021 - 30/4/2021", "Save off all product \nup to 70%", Uri.parse("")),
            Advertise("21/4/2021 - 30/4/2021", "Save off all product \nup to 20%", Uri.parse("")),
            Advertise("20/4/2021 - 30/4/2021", "Save off all product \nup to 50%", Uri.parse("")),
        )
    }
    fun loadFeatureCard(): MutableList<Feature_Item> {
        return mutableListOf(
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang")
        )
    }
    fun loadBestsellingCard(): MutableList<Feature_Item> {
        return mutableListOf(
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 90000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 12000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 15000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 20000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 90000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 12000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 15000"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 20000")
        )
    }
    fun loadSuggestCard(): MutableList<Feature_Item> {
        return mutableListOf(
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 90000", "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 12000",  "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 15000",  "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 20000", "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 90000", "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 12000", "99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 15000","99 Reviews"),
            Feature_Item(R.drawable.img_book_card_example, "Khang", "Khang", "đ 20000","99 Reviews")
        )
    }
}