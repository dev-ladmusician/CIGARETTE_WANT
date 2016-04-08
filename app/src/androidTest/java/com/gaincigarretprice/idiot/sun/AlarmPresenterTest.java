package com.gaincigarretprice.idiot.sun;

import android.support.test.runner.AndroidJUnit4;

import com.gaincigarretprice.idiot.sun.model.remote.AlarmService;
import com.gaincigarretprice.idiot.sun.presenter.AlarmPresenter;
import com.gaincigarretprice.idiot.sun.presenter.AlarmPresenterImpl;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapterDataModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;

/**
 * Created by jihoon on 2016. 4. 9..
 */
@RunWith(AndroidJUnit4.class)
public class AlarmPresenterTest {

    AlarmPresenter alarmPresenter;
    AlarmPresenter.View view;
    AlarmService alarmService;
    AlarmAdapterDataModel alarmAdapterDataModel;

    @Before
    public void setUp() throws Exception {
        view = mock(AlarmPresenter.View.class);
        alarmService = mock(AlarmService.class);
        alarmAdapterDataModel = mock(AlarmAdapterDataModel.class);
        alarmPresenter = new AlarmPresenterImpl(view, alarmService, alarmAdapterDataModel);
    }


    @Test
    public void testLoadItems() throws Exception {
        alarmPresenter.loadItems();
    }


    @Test void testAddItem() throws Exception {

    }
}
