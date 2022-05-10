package com.ahmedelzubair.todolistproject.adapters;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelzubair.todolistproject.R;
import com.ahmedelzubair.todolistproject.data.DatabaseHelper;
import com.ahmedelzubair.todolistproject.domain.ToDoItem;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private final Context context;
    private final List<ToDoItem> toDoItems;
    private final DatabaseHelper databaseHelper;
    private UpdateListener listener;

    public ToDoListAdapter(Context context, List<ToDoItem> toDoItems, UpdateListener listener) {
        this.context = context;
        this.toDoItems = toDoItems;
        databaseHelper = DatabaseHelper.getInstance(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.to_do_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoItem item = toDoItems.get(position);
        holder.tvToDoTitle.setText(item.getTitle());
        holder.cbStatus.setChecked(item.getStatus() == 1);

        holder.ivItemEdit.setOnClickListener(view -> {
            updateToDoTask(item);
        });
        holder.ivItemDelete.setOnClickListener(view -> {
            deleteToDoTask(item);
        });

        holder.cbStatus.setOnClickListener(view -> {
            updateToDoTaskStatus(holder.cbStatus.isChecked(), item);
        });

    }

    private void updateToDoTaskStatus(boolean checked, ToDoItem item) {
        int status = checked ? 1 : 0;
        databaseHelper.updateToDoTaskStatus(item.getId(),status);
        listener.onUpdateList();
    }

    private void deleteToDoTask(ToDoItem item) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_delete_confirm);
        dialog.setCancelable(true);

        Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });

        btnConfirm.setOnClickListener(view -> {
            databaseHelper.deleteToDoTask(item.getId());
            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
            listener.onUpdateList();
            dialog.dismiss();
        });

        dialog.show();
    }

    private void updateToDoTask(ToDoItem item) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_edit_todo);
        dialog.setCancelable(true);

        EditText etTitle = dialog.findViewById(R.id.etTitle);
        EditText etBody = dialog.findViewById(R.id.etBody);
        Button btnUpdateToDo = dialog.findViewById(R.id.btnUpdateToDo);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        etTitle.setText(item.getTitle());
        etBody.setText(item.getBody());

        btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });

        btnUpdateToDo.setOnClickListener(view -> {
            String newTitle = etTitle.getText().toString().trim();
            String newBody = etBody.getText().toString().trim();
            if (!TextUtils.isEmpty(newTitle) && !TextUtils.isEmpty(newBody)) {
                databaseHelper.updateToDoTask(item.getId(), newTitle, newBody);
                Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
                listener.onUpdateList();
            }
            dialog.dismiss();
        });

        dialog.show();

    }

    @Override
    public int getItemCount() {
        return toDoItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvToDoTitle;
        public ImageView ivItemEdit, ivItemDelete;
        private CheckBox cbStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvToDoTitle = itemView.findViewById(R.id.tvToDoTitle);
            ivItemEdit = itemView.findViewById(R.id.ivItemEdit);
            ivItemDelete = itemView.findViewById(R.id.ivItemDelete);
            cbStatus = itemView.findViewById(R.id.cbStatus);
        }
    }

}
