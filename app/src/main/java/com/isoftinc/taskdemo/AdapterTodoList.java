package com.isoftinc.taskdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
public class AdapterTodoList extends RecyclerView.Adapter<AdapterTodoList.AdapterTodoListViewHolder>{
    private Context mContext;
    JSONArray jsonArray = new JSONArray();
    public AdapterTodoList(JSONArray jsonArray, Context mContext)
    {
        this.jsonArray = jsonArray;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public AdapterTodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_list,parent,false);
        return new AdapterTodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTodoListViewHolder holder, int position) {
      holder.setIsRecyclable(false);
      try
      {
          JSONObject object = jsonArray.getJSONObject(position);
          holder.txt1.setText(object.getString("title"));
          holder.text2.setText(object.getString("description"));
          holder.view.setBackgroundColor(Color.parseColor(object.getString("priority-color")));
          if (object.getString("status").equalsIgnoreCase("done"))
          {

              holder.txt1.setTextColor(ContextCompat.getColor(mContext,R.color.gray));
              holder.txt1.setPaintFlags(holder.txt1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

          }
          else
          {
              holder.txt1.setTextColor(ContextCompat.getColor(mContext,R.color.black));
          }


      }
      catch (Exception ex)
      {
          ex.printStackTrace();
      }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class AdapterTodoListViewHolder extends RecyclerView.ViewHolder {
        TextView txt1,text2;
        View view;
        public AdapterTodoListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            text2 = itemView.findViewById(R.id.text2);
            view = itemView.findViewById(R.id.view);
        }
    }
}
