package me.cheshmak.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.util.Log;
import me.cheshmak.android.sdk.core.Cheshmak;

public class CheshmakCordovaPlugin extends CordovaPlugin {
    private static final String TAG = "cheshmak";
    public CordovaInterface cordova = null;

    @Override
    public void initialize (CordovaInterface initCordova, CordovaWebView webView) {
        Log.e (TAG, "CheshmakCordovaPlugin initialize");
        cordova = initCordova;
        super.initialize (cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if("addTag".equals(action)) {
            addTag(args, callbackContext);
            return true;
        }
        if ("deleteTag".equals(action)) {
            deleteTag(args, callbackContext);
            return true;
        }
        if ("deleteAllTags".equals(action)) {
            deleteAllTags(callbackContext);
            return true;
        }
        if ("startView".equals(action)) {
            startView(callbackContext);
            return true;
        }
        if ("stopView".equals(action)) {
            stopView(callbackContext);
            return true;
        }

        if ("setTest".equals(action)) {
            setTest(args, callbackContext);
            return true;
        }
        if("getData".equals(action)) {
            getData(args, callbackContext);
            return true;
        }


        return false;
    }

    public void addTag(JSONArray args, final CallbackContext callbackContext) throws JSONException {
        try {
            String tagName = args.getString(0);
            Cheshmak.sendTag(tagName);
            callbackContext.success();
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
        }
    }

    public void deleteTag(JSONArray args, CallbackContext callbackContext){
        try {
            String tagName = args.getString(0);
            Cheshmak.deleteTag(tagName);
            callbackContext.success();
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
        }
    }

    public void deleteAllTags(final CallbackContext callbackContext) {
        Cheshmak.deleteAllTags();
        callbackContext.success();
    }

    public void startView(final CallbackContext callbackContext){
        Cheshmak.startView(cordova.getActivity().toString());
        callbackContext.success();
    }

    public void stopView(CallbackContext callbackContext){
        Cheshmak.stopView(cordova.getActivity().toString());
        callbackContext.success();
    }



    public void setTest(JSONArray args, final CallbackContext callbackContext) throws JSONException{
        try {
            boolean test = args.getBoolean(0);
            Cheshmak.isTestDevice(test);
            callbackContext.success();
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
        }
    }

    public void getData(JSONArray args, final CallbackContext callbackContext) throws JSONException{
        Intent intent = cordova.getActivity().getIntent();
        if (intent != null) {
            if (intent.getExtras() != null) {
                callbackContext.success(intent.getExtras().getString("me.cheshmak.data"));
            }
        } else {
            callbackContext.error(0);
        }
    }


//region Advertise

    //endregion
}
