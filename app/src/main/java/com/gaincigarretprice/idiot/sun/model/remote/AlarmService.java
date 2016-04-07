package com.gaincigarretprice.idiot.sun.model.remote;

import com.gaincigarretprice.idiot.sun.model.data.dto.AlarmDTO;
import com.gaincigarretprice.idiot.sun.model.data.dto.ResultDTO;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ladmusician on 2/23/16.
 */
public class AlarmService {

    private Retrofit mRetrofit;

    @Inject
    public AlarmService(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    public Observable<ResultDTO<List<AlarmDTO>>> getAlarmsById(int userId) {
        return mRetrofit.create(com.gaincigarretprice.idiot.sun.model.remote.AlarmService.AlarmApi.class)
                .getAlarmsByUserId(userId);
    }

    public interface AlarmApi {
        @GET("alarm/getAlarmsByUserId/{userId}")
        Observable<ResultDTO<List<AlarmDTO>>> getAlarmsByUserId(
                @Path("userId") int userId
        );
    }
}
