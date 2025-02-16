package ir.servicea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.servicea.R;
import ir.servicea.model.ModelBankCard;

public class AdapterListBankCard extends RecyclerView.Adapter<AdapterListBankCard.ProductViewHolder> {

    private final List<ModelBankCard> bankCardList;
    private final Context context;
    private final OnSwitchClick onSwitchClick;

    public AdapterListBankCard(List<ModelBankCard> bankCardList, Context context, OnSwitchClick onSwitchClick) {
        this.bankCardList = bankCardList;
        this.context = context;
        this.onSwitchClick = onSwitchClick;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ModelBankCard bankCard
                = bankCardList.get(position);

        holder.bind(context, bankCard, holder, onSwitchClick);
//        holder.tvBankNumber.setText(bankCard.getAmount_discount() + "%");
//        holder.tvSheba.setText(String.valueOf(bankCard.getCustomer_price()));
//        holder.tvBankName.setText(String.valueOf(bankCard.getCustomer_price()));
    }

    @Override
    public int getItemCount() {
        return bankCardList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvCardNo, tvSheba, tvBankName;
        Switch setDefault;

        public ProductViewHolder(View itemView) {
            super(itemView);
            tvCardNo = itemView.findViewById(R.id.tv_card_no);
            tvSheba = itemView.findViewById(R.id.tv_sheba);
            tvBankName = itemView.findViewById(R.id.tv_bank_name);
            setDefault = itemView.findViewById(R.id.sw_default);
        }


        public void bind(Context context, final ModelBankCard item, final AdapterListBankCard.ProductViewHolder holder, final AdapterListBankCard.OnSwitchClick listener) {

            setDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSwitchClickClick(item, getAdapterPosition());
                }
            });
        }
    }

    public interface OnSwitchClick {
        void onSwitchClickClick(ModelBankCard modelBankCard, int position);
    }

    public void selectBankCardDefaultItem(ModelBankCard modelBankCard, int position) {
        this.bankCardList.set(position, modelBankCard);
        notifyItemChanged(position);
    }


}
