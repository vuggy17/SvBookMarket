package com.example.svbookmarket.activities.data

import android.net.Uri
import com.example.svbookmarket.R
import com.example.svbookmarket.activities.model.*

class DataSource {
    fun loadCheckoutCard(): MutableList<CheckoutCard> {
        return mutableListOf(
            CheckoutCard("dsadsa", "name", "adr", "100", "123"),
            CheckoutCard("dsadsa", "name", "adr", "100", "123"),
            CheckoutCard("dsadsa", "name", "adr", "100", "123"),
            CheckoutCard("dsadsa", "name", "adr", "100", "123"),
            CheckoutCard("dsadsa", "name", "adr", "100", "123"),
            CheckoutCard("dsadsa", "name", "adr", "100", "123"),

            )
    }

    fun loadCart(): MutableList<CartModel> {
        return mutableListOf(
            CartModel("name","name","name",10,1000 ),
            CartModel("name","name","name",9,200 ),
            CartModel("name","name1","name",8,113 ),
            CartModel("name","name","name",12,1997 ),
            CartModel("name","name","name",3,2001 ),
            CartModel("name","name1","name",1,1000 ),

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
    fun loadSearchItem(): MutableList<SearchResultItem> {
        return mutableListOf(
            SearchResultItem("Nguyễn Nhật Ánh", "21:00, May 8, 2021"),
            SearchResultItem("Địa chính trị", "21:00, May 8, 2021"),
            SearchResultItem("Địa Lý", "21:00, May 8, 2021"),
            SearchResultItem("Lịch Sử", "21:00, May 8, 2021"),
            SearchResultItem("OOP fundamentals", "21:00, May 8, 2021"),
            SearchResultItem("Head of Design Pattern", "21:00, May 8, 2021"),
            SearchResultItem("Toán", "21:00, May 8, 2021"),
            SearchResultItem("Những tù nhân của địa lý", "21:00, May 8, 2021"),
            SearchResultItem("Sinh viên", "21:00, May 8, 2021"),
            SearchResultItem("ĐH CNTT", "21:00, May 8, 2021"),
            SearchResultItem("Công nghệ phần mềm", "21:00, May 8, 2021"),
            SearchResultItem("Kỹ thuật phần mềm", "21:00, May 8, 2021"),
        )
    }
}