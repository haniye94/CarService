package ir.tehranOil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import ir.tehranOil.app.DataBaseHelper;
import ir.tehranOil.app.G;
import ir.tehranOil.model.dbModel.ModelSaveKhadamat;
import ir.servicea.R;

import java.util.List;


public class AdapterDoneServiceType extends RecyclerView.Adapter<AdapterDoneServiceType.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelSaveKhadamat> models;
    AlertDialog alertDialogs_detect_type;
    DataBaseHelper mDBHelper;

    Boolean is_reserve_list;

    public AdapterDoneServiceType(Context context, List<ModelSaveKhadamat> models, boolean is_reserve_list) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
        mDBHelper = new DataBaseHelper(context);
        this.is_reserve_list = is_reserve_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_layout_info_service_car, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.btn_type.setTypeface(G.ExtraBold);
        if (is_reserve_list){
            holder.btn_type.setText(models.get(position).getTitle().toString());
        } else {
            if (models.get(position).getSelb()) {
                holder.btn_type.setText(models.get(position).getTitle().toString());
                if (models.get(position).getType() != null && models.get(position).getType().toString().length() > 0) {
                    holder.btn_type.append(" - ");
                    if (models.get(position).getValue() != null && models.get(position).getValue().toString().length() > 0) {
                        holder.btn_type.append("(" + models.get(position).getType().toString() + " - " + models.get(position).getValue().toString() + ")");
                    } else {
                        holder.btn_type.append("(" + models.get(position).getType().toString() + ")");
                    }
                }
                holder.btn_type.setBackgroundResource(R.drawable.state_shap_button);

            } else if (models.get(position).getSelt()) {
                holder.btn_type.setText(models.get(position).getTitle().toString());
                holder.btn_type.setBackgroundResource(R.drawable.state_shap_button_green);
            }
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView btn_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_type = itemView.findViewById(R.id.btn_type);

        }
    }


}
