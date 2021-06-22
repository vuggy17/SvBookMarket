package com.example.svbookmarket.activities.common


object Constants {
    //App
    const val VMTAG = "VMTAG"


    //Intents
    const val SPLASH_INTENT = "splashIntent"

    //Bundles
    const val ITEM = "ITEM_TO_DISPLAY"


    //References
    const val USERS_REF = "accounts"
    const val BOOK_REF = "books"
    const val ADDRESS_REF = "userDeliverAddresses"


    //Fields
    const val NAME = "name"
    const val EMAIL = "email"
    const val PHOTO_URL = "photoUrl"
    const val CREATED_AT = "createdAt"
    const val RATING = "rating"

    //Bindings
    const val MOVIE_POSTER = "moviePoster"
    const val PRODUCT_LOGO = "productLogo"

    enum class ACTIVITY {
       PROFILE, MENU, SEARCH, CART, CATEGORY, FEATURE, CATEGORY_DETAIL;

        override fun toString(): String {
            return name.toLowerCase().capitalize()
        }
    }
    enum class TRANSACTION{
        RECEIVED, COMPLETE,DELIVERING,CANCEL;
        override fun toString(): String {
            return name.toLowerCase().capitalize()
        }
    }

    enum class CATEGORY{
        COMIC, FICTION, NOVEL, BUSINESS, TECHNOLOGY, ART;

        override fun toString(): String {
            return name.toLowerCase().capitalize()
        }
    }
}