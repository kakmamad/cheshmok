package me.cheshmak.cordova;

import android.app.Application;
import android.util.Log;

import me.cheshmak.android.sdk.core.Cheshmak;


public class CheshmakApplication extends Application {
   public static final String LOGTAG = "CheshmakApplication";

   @Override
   public void onCreate(){
      super.onCreate();

      try {
    	  CheshmakConfigReader config = new CheshmakConfigReader(getApplicationContext(), null);
    	  String appKey = config.getAppKey();

          Cheshmak.with(this);
          Cheshmak.initTracker(appKey);
          Cheshmak.enableAdvertises();
        } catch(CheshmakConfigException ex){
         Log.e(LOGTAG, ex.toString());
      }
   }
}