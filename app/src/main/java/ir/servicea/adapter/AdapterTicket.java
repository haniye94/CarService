package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import ir.servicea.R;
import ir.servicea.app.CalendarTool;
import ir.servicea.app.G;
import ir.servicea.model.ModelML;
import ir.servicea.model.Ticket;

public class AdapterTicket extends RecyclerView.Adapter<AdapterTicket.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<Ticket> models;

    public AdapterTicket(Context context, List<Ticket> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_ticket, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.user.setTypeface(G.Bold);
        holder.user.setText(models.get(position).getTitle() + "");

        holder.content.setTypeface(G.Bold);
        holder.content.setText(models.get(position).getContent());

        String date = models.get(position).getCreate_at();
        holder.date.setTypeface(G.Bold);
        if (date.contains("-") && date.contains(":") && date.contains(" ")) {
            CalendarTool calendarTool = new CalendarTool();
            String[] date_time = date.split(" ");
            String[] dates = date_time[0].split("-");
            calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
            date = calendarTool.getIranianDate() + " " + date_time[1];
            holder.date.setText(date);
        } else if (date.contains("-")) {
            date = date.replace(" ", "");
            CalendarTool calendarTool = new CalendarTool();
            String[] dates = date.split("-");
            calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
            date = calendarTool.getIranianDate();
            holder.date.setText(date);
        }
        if(!models.get(position).isExpanded()){
            holder.expandableLayout.collapse();
            holder.toggle.setImageResource(R.drawable.arrow_down_24);
            models.get(position).setExpanded(false);
        }else{
            holder.expandableLayout.expand();
            holder.toggle.setImageResource(R.drawable.arrow_up_24);
            models.get(position).setExpanded(true);
        }
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.expandableLayout.isExpanded()){
                    holder.expandableLayout.collapse();
                    holder.toggle.setImageResource(R.drawable.arrow_down_24);
                    models.get(position).setExpanded(false);
                }else {
                    holder.expandableLayout.expand();
                    holder.toggle.setImageResource(R.drawable.arrow_up_24);
                    models.get(position).setExpanded(true);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView char_count, user, user_phone, date, price, content;
        ExpandableLayout expandableLayout;
        ImageView toggle;
        ViewGroup root;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user_id);
            date = itemView.findViewById(R.id.create_at);
            content = itemView.findViewById(R.id.content);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            expandableLayout.collapse();
            root = itemView.findViewById(R.id.root);
            toggle = itemView.findViewById(R.id.toggle);
        }
    }

    public void swapData(List<Ticket> tickets){
        this.models = tickets;
        this.notifyDataSetChanged();
    }
}