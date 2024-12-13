package ir.tehranOil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.servicea.R;
import ir.tehranOil.model.ReserveProduct;

public class AdapterListReserveProduct extends RecyclerView.Adapter<AdapterListReserveProduct.ProductViewHolder> {

    private final List<ReserveProduct> productList;
    private final Context context;
    private final OnAddProductClick onAddProductClick;

    public AdapterListReserveProduct(List<ReserveProduct> productList, Context context, OnAddProductClick onAddProductClick) {
        this.productList = productList;
        this.onAddProductClick = onAddProductClick;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ReserveProduct product = productList.get(position);
        holder.tvTitle.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());
        //holder.ivImage.setImageResource(product.getImageResId());

        /*Picasso.get()
                .load(product.getImageResId())
               // .placeholder(R.drawable.placeholder) // optional placeholder
               // .error(R.drawable.error) // optional error image
                .into(holder.ivImage);*/

      /*  holder.btnAdd.setOnClickListener(v -> {
            // Handle the add button click
            Toast.makeText(context, product.getName() + " added", Toast.LENGTH_SHORT).show();
        });*/

        holder.bind(context, product, holder, onAddProductClick);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView ivImage;
        Button btnAdd;

        public ProductViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_product_title);
            tvDescription = itemView.findViewById(R.id.tv_product_description);
            ivImage = itemView.findViewById(R.id.iv_product_image);
            btnAdd = itemView.findViewById(R.id.btn_add_product);
        };


        public void bind(Context context, final ReserveProduct item, final AdapterListReserveProduct.ProductViewHolder holder, final AdapterListReserveProduct.OnAddProductClick listener) {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAddProductClick(item.getId());
                }
            });
        }
    }

    public interface OnAddProductClick {
        void onAddProductClick(int productId);
    }
}
