package com.keelim.valueation

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, getString(R.string.native_key))
    }
}