package ir.servicea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.servicea.activity.DetectServiceActivity;
import ir.servicea.app.G;
import ir.servicea.R;
import ir.servicea.model.ModelProduct;
import ir.servicea.app.PreferenceUtil;

public class AdapterListDetectGroup extends RecyclerView.Adapter<AdapterListDetectGroup.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelProduct> models;
    PreferenceUtil preferenceUtil;
    private OnItemClickListener listener;

    public AdapterListDetectGroup(Context context, List<ModelProduct> models, OnItemClickListener listener) {
        this.context = context;
        this.models = models;
        this.listener = listener;
        layoutInflater = LayoutInflater.from(context);
        preferenceUtil = new PreferenceUtil(context);
    }

    public interface OnItemClickListener {
        void onItemClick(ModelProduct model, CheckBox item, ViewHolder holder, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_layout_produce_group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txt_kala.setTypeface(G.ExtraBold);
        holder.txt_kala.setText(models.get(position).getNameKala().toString());
        holder.checkbox.setChecked(models.get(position).isCheck());
        List<CheckBox> items = new ArrayList<CheckBox>();
        for (CheckBox item : items) {
            if (item.isChecked()) {
                preferenceUtil.cashType_service(item.getText().toString());
            }
            Toast.makeText(context, "" + item.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!DetectServiceActivity.isLoading) {
                    List<CheckBox> items = new ArrayList<CheckBox>();
                    for (CheckBox item : items) {
                        if (item.isChecked()) {
                            preferenceUtil.cashType_service(item.getText().toString());
                        }
                        Toast.makeText(context, "" + item.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.bind(context, models.get(position), holder, listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_kala;
        CheckBox checkbox, chbox_message;
        LinearLayout ly_check_msg;

        public void bind(Context context, final ModelProduct item, final ViewHolder holder, final OnItemClickListener listener) {

            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(compoundButton.isPressed())
                    listener.onItemClick(item, checkbox, holder, getAdapterPosition());
                }
            });
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_kala = itemView.findViewById(R.id.txt_kala);
            checkbox = itemView.findViewById(R.id.checkbox);
            chbox_message = itemView.findViewById(R.id.chbox_message);
            ly_check_msg = itemView.findViewById(R.id.ly_check_msg);
        }
    }

}
