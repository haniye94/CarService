package ir.servicea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.servicea.app.G;
import ir.servicea.model.dbModel.ModelAddMessage;

import java.util.List;

import ir.servicea.R;

public class AdapterShowOnvanMessage extends RecyclerView.Adapter<AdapterShowOnvanMessage.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelAddMessage> models;
    private OnItemClickListener listener;

    public AdapterShowOnvanMessage(Context context, List<ModelAddMessage> models, OnItemClickListener listener) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ModelAddMessage model, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_layout_onvan_mesg, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txt_onvan_msg.setTypeface(G.Normal);
        holder.txt_onvan_msg.setText(models.get(position).getTitle());
        holder.bind(context, models.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_onvan_msg;

        public void bind(Context context, final ModelAddMessage item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, getAdapterPosition());
                }
            });
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_onvan_msg = itemView.findViewById(R.id.txt_onvan_msg);
        }
    }
}
