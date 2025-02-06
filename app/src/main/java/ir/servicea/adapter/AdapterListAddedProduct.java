package ir.servicea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.servicea.R;
import ir.servicea.app.G;
import ir.servicea.model.ModelAddedProductCenter;

public class AdapterListAddedProduct extends RecyclerView.Adapter<AdapterListAddedProduct.ProductViewHolder> {

    private final List<ModelAddedProductCenter> productList;
    private final Context context;
    private final OnAddProductClick onAddProductClick;
    private final OnHideProductClick onHideProductClick;

    public AdapterListAddedProduct(List<ModelAddedProductCenter> productList, Context context, OnAddProductClick onAddProductClick, OnHideProductClick onHideProductClick) {
        this.productList = productList;
        this.onAddProductClick = onAddProductClick;
        this.onHideProductClick = onHideProductClick;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_added_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ModelAddedProductCenter product = productList.get(position);
        holder.tvTitle.setText(product.getName());

        String url = G.PreImagesURL + "product_images/" + product.getImageUrl();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.noimage) // optional placeholder
                .error(R.drawable.noimage) // optional error image
                .into(holder.ivImage);

        holder.bind(context, product, holder, onAddProductClick, onHideProductClick);
        holder.tvPercent.setText(product.getAmount_discount() + "%");
        holder.tvCustomerPrice.setText(String.valueOf(product.getCustomer_price()));
        if (product.getStatus() == 0) {
            holder.cl_root.setBackgroundResource(R.color.light_gray);
            holder.ivVisibility.setImageResource(R.drawable.ic_visible);
//            holder.itemView.setEnabled(false);
        } else holder.ivVisibility.setImageResource(R.drawable.ic_invisible);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPercent, tvCustomerPrice;
        ImageView ivImage, ivVisibility;
        ConstraintLayout cl_root;

        public ProductViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_product_name);
            ivImage = itemView.findViewById(R.id.iv_product_image);
            ivVisibility = itemView.findViewById(R.id.iv_visibility);
            tvPercent = itemView.findViewById(R.id.tv_percent);
            tvCustomerPrice = itemView.findViewById(R.id.tv_customer_price);
            cl_root = itemView.findViewById(R.id.cl_root);
        }

        ;


        public void bind(Context context, final ModelAddedProductCenter item, final AdapterListAddedProduct.ProductViewHolder holder, final AdapterListAddedProduct.OnAddProductClick listener, final OnHideProductClick hideListener) {
//            if (item.getStatus() == 0)
//                itemView.setEnabled(false);
            ivVisibility.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideListener.onHideProductClick(item, getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* if (!itemView.isEnabled())
                        G.toast("محصول غیر فعال است.");
                    else*/
                    listener.onAddProductClick(item);
                }
            });
        }
    }

    public interface OnAddProductClick {
        void onAddProductClick(ModelAddedProductCenter modelAddedProductCenter);
    }

    public interface OnHideProductClick {
        void onHideProductClick(ModelAddedProductCenter modelAddedProductCenter, int position);
    }

    public void updateData(List<ModelAddedProductCenter> modelAddedProductCenter) {
        this.productList.clear();
        this.productList.addAll(modelAddedProductCenter);
        notifyDataSetChanged();
    }

    public void hideItem(ModelAddedProductCenter modelAddedProductCenter, int position) {
        this.productList.set(position, modelAddedProductCenter);
        notifyItemChanged(position);
    }

    public void updateItemData(ModelAddedProductCenter modelAddedProductCenter) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == modelAddedProductCenter.getId()) {
                productList.set(i, modelAddedProductCenter); // Update the item
                notifyItemChanged(i); // Notify the adapter about the item update
                break; // Exit the loop once the item is found and updated
            }
        }
    }
}
