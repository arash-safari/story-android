package com.safari.arash.dastan.mainPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.safari.arash.dastan.utils.IOUtils;
import com.safari.arash.dastan.utils.SharePrefUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements ModelPresenter, ViewPresenter {
    private static final String TAG = "MainPresenter";
    SharePrefUtil sharePrefUtil;
    private MainActivity main;
    private MainModel mainModel;
    private JSONObject modelJsonObject;
    private List<Item> itemList;
    private Article article;
    private int itemIndex = 0;
    private long timeSWait=0;
    private String dastanId = "sara_maman";
    public MainPresenter(MainActivity main) {
        this.main = main;
        this.mainModel = new MainModel(this);
        sharePrefUtil = SharePrefUtil.getInstance(main);
        modelJsonObject = this.mainModel.getAllItem("MainModel1.json");
        try {
            itemList = mainModel
                    .parsItemList(modelJsonObject
                            .getJSONArray("data"));
            article = mainModel
                    .parsArticle(modelJsonObject
                            .getJSONObject("article"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemClick(int itemNumber) {

    }
    private boolean isWatingState() {
        Log.d(TAG, "isWatingState: timesWait" + timeSWait);
        Log.d(TAG, "isWatingState: currentTimeMillis" + System.currentTimeMillis());
        return (System.currentTimeMillis()/1000<=timeSWait);
    }
    @Override
    public void nextClick() {
        if (itemList.size() > itemIndex) {
            if(!isWatingState()){
                main.addItem(itemList.get(this.itemIndex++));
                Log.d(TAG, "nextClick: " + itemIndex);
                if(sharePrefUtil==null){
                    sharePrefUtil = SharePrefUtil.getInstance(getContext());
                }
                sharePrefUtil.setPosition(this.dastanId,itemIndex);
                timeSWait = waitPolicy(itemIndex);
                if(timeSWait>0){
                    main.takeWait(timeSWait);
                    sharePrefUtil.setTimeSWait(this.dastanId,timeSWait);
                    sharePrefUtil.setLastWaitIndex(dastanId,itemIndex);
                }
            }else {
                Log.d(TAG, "showDialog ");
                main.showWaitDialog();
            }
        }
    }

    private long waitPolicy(int itemIndex) {
        if(sharePrefUtil.getLastWaitIndex(dastanId)==itemIndex)
            return sharePrefUtil.getTimeSWait(dastanId);
        if(itemIndex%20==0){
            return System.currentTimeMillis()/1000 + 900;
        }
        return 0;
    }

    @Override
    public int getInitialIndex() {
        if(sharePrefUtil==null){
            sharePrefUtil = SharePrefUtil.getInstance(getContext());
        }
        return sharePrefUtil.getPosition("sara_maman");
    }

    @Override
    public Article getArticle() {
        return article;
    }


    @Override
    public Context getContext() {
        return this.main.getCtx();
    }

}
