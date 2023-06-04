package com.example.MeeshoApp.common;


import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class Common {

    Vibrator vibrator;
    Context context;
    public  Common (Context context){
        this.context= context;
    }
    public void vibrate_on_click(){
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(1);
        }
    }
//    public void switchFragment(Fragment fragments) {
//        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        FragmentTransaction replace = fragmentTransaction.replace(R.id.conatiner, fragments);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//
//    }

}
