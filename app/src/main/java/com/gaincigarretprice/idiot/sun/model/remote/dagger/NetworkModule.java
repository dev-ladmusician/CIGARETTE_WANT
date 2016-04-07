package com.gaincigarretprice.idiot.sun.model.remote.dagger;

import com.gaincigarretprice.idiot.sun.model.helper.RetrofitHelper;
import com.gaincigarretprice.idiot.sun.model.remote.AlarmService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetworkModule {
    @Provides
    public Retrofit provideRetrofit() {
        return RetrofitHelper.createRetrofit();
    }

    @Provides
    public AlarmService provideSearchApi(Retrofit retrofit) {
        return new AlarmService(retrofit);
    }
}
