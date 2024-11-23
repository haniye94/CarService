package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

import ir.servicea.R;
import ir.servicea.app.CircleTransform;
import ir.servicea.app.G;
import ir.servicea.model.Comment;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder> {
    AppCompatActivity context;
    LayoutInflater layoutInflater;
    List<Comment> models;

    public AdapterComment(AppCompatActivity context, List<Comment> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Comment comment = models.get(position);
        holder.user_name.setText(comment.getUser_name() + "");
        String date = comment.getDate();
        if (date.length() > 10 && date.contains(" ")) {
            date = date.split(" ")[0];
        }
        holder.date.setText(G.toShamsi(date + ""));
        holder.expand_text_view.setText(comment.getContent() + "");
        holder.score.setText(comment.getScore() + "");
        holder.user_profile.setImageResource(0);
        Picasso.get().load(G.PreImagesURL + "profiles/" + comment.getUser_profile()).error(R.drawable.ic_user).placeholder(R.drawable.ic_user).transform(new CircleTransform()).into(holder.user_profile);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewGroup root;
        TextView user_name, date, content, score;
        ImageView user_profile;
        ExpandableTextView expand_text_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            user_name = itemView.findViewById(R.id.user_name);
            user_name.setTypeface(G.ExtraBold);
            date = itemView.findViewById(R.id.date);
            date.setTypeface(G.ExtraBold);
            content = itemView.findViewById(R.id.expandable_text);
            score = itemView.findViewById(R.id.score);
            user_profile = itemView.findViewById(R.id.user_profile);
            expand_text_view = itemView.findViewById(R.id.expand_text_view);

        }
    }
}

