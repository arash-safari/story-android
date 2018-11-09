package com.safari.arash.dastan.mainPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.safari.arash.dastan.R;
import com.safari.arash.dastan.dialogs.RegisterDialog;
import com.safari.arash.dastan.dialogs.WaitDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PresenterView {
    private static final String TAG = "MainActivity";
    private MainPresenter presenter;
    private RecyclerView recycler;
    private RecyclerAdapter recyclerAdapter;
    private RegisterDialog registerDialog;
    private WaitDialog waitDialog;
    public final int DIALOG_RIGHT = 0, DIALOG_LEFT = 1, ACTION = 2, IMAGE_RIGHT = 3, IMAGE_LEFT = 4, CLICK_AREA=5;
    private Article article;
    private long timeSWait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        registerDialog = new RegisterDialog(this);
        waitDialog = new WaitDialog(this);
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter();
        recycler.setAdapter(recyclerAdapter);
        article = presenter.getArticle();
        int initialIndex = presenter.getInitialIndex();
        for (int i = 0; i < initialIndex; i++) {
            presenter.nextClick();
        }
    }

    @Override
    public void addItem(Item item) {
        recyclerAdapter.addItem(item);
    }

    @Override
    public void goToSubscribtion() {

    }

    @Override
    public void takeWait(long timeS) {
        timeSWait = timeS;
    }

    @Override
    public void showWaitDialog() {
        waitDialog.showDialog(timeSWait);
    }

    @Override
    public Context getCtx() {
        return this;
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<Item> items = new ArrayList<>();

        public void setItems(List<Item> items) {
            this.items = items;
        }

        void addItem(Item item) {
            this.items.add(item);
            recycler.post(new Runnable() {
                @Override
                public void run() {
                    // Call smooth scroll
                    recycler.smoothScrollToPosition(recyclerAdapter.getItemCount() - 1);
                }
            });
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater
                    .from(viewGroup.getContext());
            switch (viewType) {
                case DIALOG_RIGHT:
                    return new RightDialogHolder(layoutInflater.inflate
                            (R.layout.item_dialog_right, viewGroup, false));
                case DIALOG_LEFT:
                    return new LeftDialogHolder(layoutInflater.inflate
                            (R.layout.item_dialog_left, viewGroup, false));
                case ACTION:
                    return new ActionHolder(layoutInflater.inflate
                            (R.layout.item_dialog_action, viewGroup, false));
                case IMAGE_RIGHT:
                    return new ImageHolderRight(layoutInflater.inflate
                            (R.layout.item_dialog_image_right, viewGroup, false));
                case IMAGE_LEFT:
                    return new ImageHolderLeft(layoutInflater.inflate
                            (R.layout.item_dialog_image_left, viewGroup, false));
                default:
                    return new ClickAreaHolder(layoutInflater.inflate
                            (R.layout.item_dialog_click_area, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            switch (viewHolder.getItemViewType()) {
                case DIALOG_RIGHT:
                    RightDialogHolder holderR = (RightDialogHolder) viewHolder;
                    holderR.name.setText(article.rightSpeaker);
                    holderR.message_body.setText(items.get(i).getText());
                    break;
                case DIALOG_LEFT:
                    LeftDialogHolder holderL = (LeftDialogHolder) viewHolder;
                    holderL.name.setText(article.leftSpeaker);
                    holderL.message_body.setText(items.get(i).getText());
                    break;
                case ACTION:
                    ActionHolder holderA = (ActionHolder) viewHolder;
                    holderA.message_body.setText(items.get(i).getText());
                    break;
                case IMAGE_RIGHT:
                    ImageHolderRight holderIR = (ImageHolderRight) viewHolder;
                    Glide
                            .with(getCtx())
                            .load(R.drawable.blur_image)
                            .into(holderIR.imageView);
                    holderIR.imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            registerDialog.showDialog();
                        }
                    });
                    break;
                case IMAGE_LEFT:
                    ImageHolderLeft holderIL = (ImageHolderLeft) viewHolder;
                    break;
                case CLICK_AREA:
                    ClickAreaHolder holderC = (ClickAreaHolder) viewHolder;
                    holderC.click_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.nextClick();
                        }
                    });
                    break;
            }

        }

        @Override
        public int getItemCount() {
            return items.size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            // Just as an example, return 0 or 2 depending on position
            // Note that unlike in ListView adapters, types don't have to be contiguous
            if(position>=items.size())
                return CLICK_AREA;
            switch (items.get(position).getType()) {
                case "dialog":
                    if (items.get(position).getSpeaker().equals("right")) {
                        return DIALOG_RIGHT;
                    } else {
                        return DIALOG_LEFT;
                    }
                case "action":
                    return ACTION;
                case "image":
                    if (items.get(position).getSpeaker().equals("right")) {
                        return IMAGE_RIGHT;
                    } else {
                        return IMAGE_LEFT;
                    }
                default:
                    return DIALOG_RIGHT;
            }
        }
    }

    private class ImageHolderLeft extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageHolderLeft(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    private class ImageHolderRight extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageHolderRight(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    private class ActionHolder extends RecyclerView.ViewHolder {
        TextView message_body;

        public ActionHolder(@NonNull View itemView) {
            super(itemView);
            message_body = itemView.findViewById(R.id.message_body);
        }

    }
    private class ClickAreaHolder extends RecyclerView.ViewHolder {
        View click_view;

        public ClickAreaHolder(@NonNull View itemView) {
            super(itemView);
            click_view = itemView.findViewById(R.id.click_view);
        }

    }

    private class LeftDialogHolder extends RecyclerView.ViewHolder {
        TextView message_body;
        TextView name;

        public LeftDialogHolder(@NonNull View itemView) {
            super(itemView);
            message_body = itemView.findViewById(R.id.message_body);
            name = itemView.findViewById(R.id.name);
        }
    }

    private class RightDialogHolder extends RecyclerView.ViewHolder {
        TextView message_body;
        TextView name;

        public RightDialogHolder(@NonNull View itemView) {
            super(itemView);
            message_body = itemView.findViewById(R.id.message_body);
            name = itemView.findViewById(R.id.name);
        }
    }
}