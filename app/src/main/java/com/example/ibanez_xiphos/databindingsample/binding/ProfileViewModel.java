package com.example.ibanez_xiphos.databindingsample.binding;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import com.example.ibanez_xiphos.databindingsample.BR;
import com.example.ibanez_xiphos.databindingsample.Demo;
import com.example.ibanez_xiphos.databindingsample.R;
import com.example.ibanez_xiphos.databindingsample.adapters.RecyclerBindingAdapter;
import com.example.ibanez_xiphos.databindingsample.binding.fields.ObservableBoolean;
import com.example.ibanez_xiphos.databindingsample.binding.fields.ObservableInt;
import com.example.ibanez_xiphos.databindingsample.binding.fields.ObservableString;
import com.example.ibanez_xiphos.databindingsample.binding.fields.RecyclerConfiguration;
import com.example.ibanez_xiphos.databindingsample.model.User;
import com.example.ibanez_xiphos.databindingsample.utils.AppUtilities;

import java.util.ArrayList;

public class ProfileViewModel {

    private static final int LAYOUT_HOLDER = R.layout.item_photo;
    private static final int PHOTOS_COLUMN_COUNT = 3;
    public static final int LOADING_SHORT = 1000;

    public final ObservableString status = new ObservableString();
    public final ObservableBoolean isLoaded = new ObservableBoolean(true);
    public final ObservableBoolean isOnline = new ObservableBoolean();
    public final ObservableBoolean isFriend = new ObservableBoolean();
    public final ObservableInt friendsCount = new ObservableInt();
    public final ObservableInt starsCount = new ObservableInt();
    public final RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration();
    public User user;

    private Context context;

    public ProfileViewModel(Context context, User user) {
        this.context = context;
        this.user = user;

        status.set(user.getStatus());
        isOnline.set(user.isOnline());
        isFriend.set(user.isFriend());
        friendsCount.set(user.getCounters().getFriends());
        starsCount.set(user.getCounters().getStars());

        initRecycler();
        Demo.simulateLoading(() -> isOnline.set(true), true);
    }

    private void initRecycler() {
        RecyclerBindingAdapter<String> adapter = getAdapter();

        recyclerConfiguration.setLayoutManager(new GridLayoutManager(context, PHOTOS_COLUMN_COUNT));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<String> getAdapter() {
        ArrayList<String> photos = Demo.getPhotos();
        RecyclerBindingAdapter<String> adapter = new RecyclerBindingAdapter<>(LAYOUT_HOLDER, BR.url, photos);
        adapter.setOnItemClickListener((position, item)
                -> AppUtilities.showSnackbar(
                context,
                context.getString(R.string.photo_message, position + 1),
                false));
        return adapter;
    }

    public void changeFriendshipStatus() {
        load(() -> isFriend.set(!isFriend.get()));
    }

    private void load(Runnable onLoaded) {
        isLoaded.set(false);
        new Handler().postDelayed(() -> {
            isFriend.set(!isFriend.get());
            isFriend.set(true);
        }, LOADING_SHORT);
    }

    public void showDevMessage() {
        AppUtilities.showSnackbar(context, R.string.dev_message, false);
    }

}
