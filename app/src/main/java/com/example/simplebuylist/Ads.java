package com.example.simplebuylist;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Ads {

    public static void displayBannerAd(AdView mAdView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}
