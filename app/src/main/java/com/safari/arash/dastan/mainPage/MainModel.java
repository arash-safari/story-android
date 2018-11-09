package com.safari.arash.dastan.mainPage;

import android.content.Context;
import android.util.Log;

import com.safari.arash.dastan.utils.IOUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements PresenterModel{
    private static final String TAG = "MainModel";
    private MainPresenter presenter;
    private Context context;
    public MainModel(MainPresenter presenter) {
        this.presenter = presenter;
        this.context = presenter.getContext();
    }

    @Override
    public JSONObject getAllItem(String name) {
        String stringModel = IOUtils.getInstance(context).
                loadJSONFromAsset(name);
        Log.d(TAG, "getAllItem: " + stringModel);
        JSONObject obj = null;
        try {
            obj= new JSONObject(stringModel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public Article parsArticle(JSONObject article) {
        IOUtils ioUtils = IOUtils.getInstance(context);
        return (new Article(ioUtils.jsonStringParser(article,"name"),
                ioUtils.jsonStringParser(article,"title"),
                ioUtils.jsonIntParser(article,"likeCount"),
                ioUtils.jsonIntParser(article,"viewCount"),
                ioUtils.jsonIntParser(article,"commentCount"),
                ioUtils.jsonStringParser(article,"src"),
                ioUtils.jsonStringParser(article,"author"),
                ioUtils.jsonStringParser(article,"authorProfile"),
                ioUtils.jsonStringParser(article,"rightSpeaker"),
                ioUtils.jsonStringParser(article,"leftSpeaker")

        ));
    }
    public List<Item> parsItemList(JSONArray data) {
        List<Item> items=new ArrayList<>();
        try {
            for (int i = 0; i < data.length(); i++) {
                items.add(parsItem(data.getJSONObject(i)));
            }
            return items;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Item parsItem(JSONObject jsonObject) {
        IOUtils ioUtils = IOUtils.getInstance(context);
        return (new Item(ioUtils.jsonStringParser(jsonObject,"name"),
                ioUtils.jsonStringParser(jsonObject,"type"),
                ioUtils.jsonStringParser(jsonObject,"src"),
                ioUtils.jsonStringParser(jsonObject,"text"),
                ioUtils.jsonStringParser(jsonObject,"speaker")
        ));
    }

    @Override
    public List<Article> getAllArticle() {
        return null;
    }

}
