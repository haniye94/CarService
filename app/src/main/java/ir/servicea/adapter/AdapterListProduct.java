package ir.servicea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.servicea.R;
import ir.servicea.app.G;
import ir.servicea.model.ModelServicesCustomer;
import ir.servicea.model.ReserveProduct;

public class AdapterListProduct extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ReserveProduct> productList;
    private final Context context;
    private final OnAddProductClick onAddProductClick;
    List<ReserveProduct> products = new ArrayList<>();


    public AdapterListProduct(List<ReserveProduct> productList, Context context, OnAddProductClick onAddProductClick) {
        this.productList = productList;
        this.onAddProductClick = onAddProductClick;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        // Decide which layout to use based on the position or some logic
        return 1; // Alternate layouts (0 for layout one, 1 for layout two)
//        return position == 4 ? 0 : 1; // Alternate layouts (0 for layout one, 1 for layout two)
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
            return new BannerViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReserveProduct product = productList.get(position);

        if (holder instanceof ProductViewHolder) {
            ((ProductViewHolder) holder).tvTitle.setText(product.getProduct_name());

            String url = G.PreImagesURL + "product_images/" + product.getImageUrl();
            Picasso.get().load(url)
//                .placeholder(R.drawable.product) // optional placeholder
//                .error(R.drawable.error_circle) // optional error image
                    .into(((ProductViewHolder) holder).ivImage);

            ((ProductViewHolder) holder).bind(context, product, ((ProductViewHolder) holder), onAddProductClick);

            if (product.getExist()) {
                ((ProductViewHolder) holder).ivExist.setVisibility(View.VISIBLE);
                ((ProductViewHolder) holder).btnAdd.setBackgroundResource(R.drawable.shap_add_inactive);
                ((ProductViewHolder) holder).btnAdd.setText("افزوده شده");
            } else {
                ((ProductViewHolder) holder).ivExist.setVisibility(View.INVISIBLE);
                ((ProductViewHolder) holder).btnAdd.setBackgroundResource(R.drawable.shap_add_blue);
                ((ProductViewHolder) holder).btnAdd.setText("افزودن");
            }
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivImage, ivExist;
        Button btnAdd;

        public ProductViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_product_name);
            ivImage = itemView.findViewById(R.id.iv_product_image);
            ivExist = itemView.findViewById(R.id.iv_exist);
            btnAdd = itemView.findViewById(R.id.btn_add_product);
        }

        ;


        public void bind(Context context, final ReserveProduct item, final AdapterListProduct.ProductViewHolder holder, final AdapterListProduct.OnAddProductClick listener) {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAddProductClick(item);
                }
            });
        }
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.img_banner);
        }


        public void bind(Context context, final ReserveProduct item, final AdapterListProduct.ProductViewHolder holder, final AdapterListProduct.OnAddProductClick listener) {
        }
    }

    public interface OnAddProductClick {
        void onAddProductClick(ReserveProduct reserveProduct);
    }


    public void updateData(List<ReserveProduct> reserveProducts) {
        this.productList.clear();
        this.productList.addAll(reserveProducts);
        notifyDataSetChanged();
    }


    public void updateItemData(int product_id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProduct_id() == product_id) {
                productList.get(i).setExist(true);
                productList.set(i, productList.get(i)); // Update the item
                notifyItemChanged(i); // Notify the adapter about the item update
                break; // Exit the loop once the item is found and updated
            }
        }
    }

}
