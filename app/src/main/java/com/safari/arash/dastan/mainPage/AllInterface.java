package com.safari.arash.dastan.mainPage;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

interface PresenterModel {
    JSONObject getAllItem(String name);
    List<Article> getAllArticle();
    List<Item> parsItemList(JSONArray data);
    Article parsArticle(JSONObject article);
}

interface PresenterView {
//    void showArticleList(List<Article> articles);
    void addItem(Item item);
    void goToSubscribtion();
    void takeWait(long timeS);
    void showWaitDialog();
    Context getCtx();
}
interface ViewPresenter {
//    void articleSelect(String name);
    void itemClick(int itemNumber);
    void nextClick();
    int getInitialIndex();
    Article getArticle( );
}
interface ModelPresenter {
    Context getContext();
}
