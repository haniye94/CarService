package ir.tehranOil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.tehranOil.app.G;
import ir.servicea.R;
import ir.tehranOil.activity.ManageMessageActivity;
import ir.tehranOil.model.ModelMM;

public class AdapterManageMessage extends RecyclerView.Adapter<AdapterManageMessage.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelMM> models;

    public AdapterManageMessage(Context context, List<ModelMM> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_manage_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setTypeface(G.Bold);
        holder.title.setText(models.get(position).getTitle());
        JSONArray msg_provs = models.get(position).getProvs();
        List<String> list_msg_prov = new ArrayList<>();
        List<String> list_msg_prov_text = new ArrayList<>();
        list_msg_prov.add("عدم ارسال");
        for (int i = 0; i < msg_provs.length(); i++) {
            try {
                JSONObject prov = msg_provs.getJSONObject(i);
                int id = prov.getInt("id");
                String title = prov.getString("name");
                String text = prov.getString("text");

                boolean status = prov.getBoolean("status");
                if (status) {
                    list_msg_prov.add(title);
                    list_msg_prov_text.add(text);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        SpinnerAdapter spinnerAdapter2 = new ArrayAdapter(context, R.layout.item_spiner, list_msg_prov);
        ((ArrayAdapter) spinnerAdapter2).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spineer_prov.setAdapter(spinnerAdapter2);
        holder.spineer_prov.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                G.Log("sss");

            }
        });
        holder.spineer_prov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positionSpinner, long id) {
                int msg_prov_id = 0;
                int msg_title_id = 0;
                try {
                    Log.d("AdapterManageMessaages", "onItemSelected:position " + position);
                    Log.d("AdapterManageMessaages", "onItemSelected:positionSpinner " + positionSpinner);
                    //Log.d("AdapterManageMessaages", "onItemSelected:provs:// " + models.get(position).getProvs());
                    if (positionSpinner > 0) {
                        JSONObject prov = models.get(position).getProvs().getJSONObject(positionSpinner - 1);
                        msg_prov_id = prov.getInt("id");
                        String text = prov.getString("text");
                        msg_title_id = models.get(position).getId();
                        Log.d("AdapterManageMessaages", "onItemSelected:name " + prov.getString("name"));
                        Log.d("AdapterManageMessaages", "onItemSelected:text " + text);
                        Log.d("AdapterManageMessaages", "onItemSelected:msg_prov_id " + msg_prov_id);
                        Log.d("AdapterManageMessaages", "onItemSelected:msg_title_id " + msg_title_id);
                        Log.d("AdapterManageMessaages", "onItemSelected:msg_title_id " + msg_title_id);

                        if (text.length() > 5) {
                            if (ManageMessageActivity.show_msg != null) {
                                ManageMessageActivity.show_msg.setText("متن پیامک:\n" + text);
                                ManageMessageActivity.show_msg.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (ManageMessageActivity.show_msg != null) {
                                ManageMessageActivity.show_msg.setText("");
                                ManageMessageActivity.show_msg.setVisibility(View.GONE);
                            }
                        }


                    } else {
                        msg_prov_id = 0;
                        if (ManageMessageActivity.show_msg != null) {
                            ManageMessageActivity.show_msg.setText("");
                            ManageMessageActivity.show_msg.setVisibility(View.GONE);
                        }
                    }
                    JSONObject object = new JSONObject();
                    object.put("msg_title_id", msg_title_id);
                    object.put("msg_prov_id", msg_prov_id);
                    ManageMessageActivity.jsonSave.put(position, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (ManageMessageActivity.jsonSave.length() > position) {
            try {

                JSONObject save = ManageMessageActivity.jsonSave.getJSONObject(position);
                int msg_title_idx = models.get(position).getId();
                int msg_title_id = save.getInt("msg_title_id");
                if (msg_title_idx == msg_title_id) {
                    JSONArray provs = models.get(position).getProvs();

                    for (int v = 0; v < provs.length(); v++) {
                        JSONObject prov = provs.getJSONObject(v);
                        int msg_prov_idx = prov.getInt("id");
                        int msg_prov_id = save.getInt("msg_prov_id");
                        if (msg_prov_idx == msg_prov_id) {
                            holder.spineer_prov.setSelection(v + 1);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        Spinner spineer_prov;
        ViewGroup root;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            spineer_prov = itemView.findViewById(R.id.spineer_prov);
            root = itemView.findViewById(R.id.root);

        }
    }


}
