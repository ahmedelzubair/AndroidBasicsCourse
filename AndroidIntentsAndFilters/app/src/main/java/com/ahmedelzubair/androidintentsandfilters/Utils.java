package com.ahmedelzubair.androidintentsandfilters;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

// intents examples
public class Utils {


    public static void openAppOnGooglePlay(Activity activity) {
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }

    public static void contactUsViaWhatsApp(Context context, String phone) {
        String url = "https://api.whatsapp.com/send?phone=" + phone;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    public static void openWebBrowserWith(Activity activity, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    public static void shareApp(Activity activity) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, activity.getResources().getString(R.string.app_name));
        String sAux = "حمل تطبيق \n" + activity.getResources().getString(R.string.app_name) + " من هنا وشاركه مع اصدقائك ";
        sAux = sAux + "https://play.google.com/store/apps/details?id=" + activity.getPackageName() + "\n\n";
        i.putExtra(Intent.EXTRA_TEXT, sAux);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(Intent.createChooser(i, "Choose one"));
    }


}
