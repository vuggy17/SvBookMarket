//package com.example.svbookmarket.activities.adapter;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.text.Layout;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.svbookmarket.R;
//import com.example.svbookmarket.activities.model.CartModel;
//import com.example.svbookmarket.databinding.CardCheckoutBinding;
//
//
//import mva2.adapter.ItemBinder;
//import mva2.adapter.ItemViewHolder;
//
//public class CarBinder extends ItemBinder<CartModel, CarBinder.CarViewHolder> {
//
//    @Override
//    public CarViewHolder createViewHolder(ViewGroup parent) {
//        return new CarViewHolder(inflate(parent, R.layout.card_checkout,);
//    }
//
//    @Override
//    public boolean canBindData(Object item) {
//        return item instanceof CartModel;
//    }
//
//    @Override
//    public void bindViewHolder(CarViewHolder holder, CartModel item) {
//        holder.tvCarName.setText(item.getName());
//    }
//
//    static class CarViewHolder extends ItemViewHolder<CartModel> {
//        private final CardCheckoutBinding binding;
//        ImageView imgBookImage;
//        TextView tvAuthor;
//        TextView tvBookName;
//        TextView tvNumber;
//        TextView tvprice;
//
//
//        public CarViewHolder(View itemView) {
//            super(itemView);
//            LayoutInflater inflater = new LayoutInflater() {
//                @Override
//                public LayoutInflater cloneInContext(Context newContext) {
//                    return newContext;
//                }
//            }
//            binding = CardCheckoutBinding.inflate(inflater, container, false);
////             imgBookImage = findViewById(R.layout.)
//            tvAuthor =  binding.tvAuthor;
//             tvBookName = binding.tvBookname;
//            tvNumber = binding.tvNumbers;
//             tvprice = binding.tvPrice;
//        }
//
//
//    }
//}
