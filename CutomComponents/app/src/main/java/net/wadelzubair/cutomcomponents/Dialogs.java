package net.wadelzubair.cutomcomponents;

import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dialogs {


    public static void showMessageDialog(MainActivity mainActivity, String message) {
        Dialog dialog = new Dialog(mainActivity);
        dialog.setContentView(R.layout.dialog_message);
        dialog.setCancelable(true);
        Button btnDialog = dialog.findViewById(R.id.btnDialog);
        TextView tvMessage = dialog.findViewById(R.id.tvMessage);

        tvMessage.setText(message);

        btnDialog.setOnClickListener(view -> {
            dialog.dismiss();
            Toast.makeText(mainActivity, "Dialog btn clicked", Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }
}
