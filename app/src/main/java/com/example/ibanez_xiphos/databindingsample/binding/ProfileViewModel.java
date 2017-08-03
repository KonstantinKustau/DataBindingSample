package com.example.ibanez_xiphos.databindingsample.binding;

import android.content.Context;
import android.os.Handler;

import com.example.ibanez_xiphos.databindingsample.Demo;
import com.example.ibanez_xiphos.databindingsample.binding.fields.ObservableBoolean;
import com.example.ibanez_xiphos.databindingsample.binding.fields.ObservableInt;
import com.example.ibanez_xiphos.databindingsample.binding.fields.ObservableString;
import com.example.ibanez_xiphos.databindingsample.model.User;

public class ProfileViewModel {

    public static final int LOADING_SHORT = 1000;

    public final ObservableString status = new ObservableString();
    public final ObservableBoolean isLoaded = new ObservableBoolean(true);
    public final ObservableBoolean isOnline = new ObservableBoolean();
    public final ObservableBoolean isFriend = new ObservableBoolean();
    public final ObservableInt friendsCount = new ObservableInt();
    public final ObservableInt starsCount = new ObservableInt();
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

        Demo.simulateLoading(() -> isOnline.set(true), true);
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

}
