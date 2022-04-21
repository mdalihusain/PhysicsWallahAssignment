package com.devoverse.wallah.physics.ui.main;

import android.util.Log;

import com.devoverse.wallah.physics.adapters.TeacherCardAdapter;
import com.devoverse.wallah.physics.models.TeacherCardModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TeacherCardModel>> teachers;
    private OkHttpClient client = new OkHttpClient();

    // TODO: Implement the ViewModel
    public LiveData<ArrayList<TeacherCardModel>> getTeachers() {
        // CHECKING IF THE METHOD IS BEING CALLED FOR THE FIRST TIME
        if (teachers == null) {
            teachers = new MutableLiveData<ArrayList<TeacherCardModel>>();
            loadTeachers();
        }
        return teachers;
    }

    private void loadTeachers() {
        loadTeachersThread.start();
    }


    Thread loadTeachersThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                //CREATING JSON ARRAY FROM RESPONSE
                String response = get("https://my-json-server.typicode.com/easygautam/data/users");
                JSONArray array = new JSONArray(response);

                //FILLING DATA IN ARRAY LIST
                ArrayList<TeacherCardModel> arrayList = new ArrayList<TeacherCardModel>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    TeacherCardModel teacherCardModel = new TeacherCardModel(jsonObject.getInt("id"),
                            jsonObject.getString("name"),
                            jsonObject.getJSONArray("subjects").get(0).toString(),
                            jsonObject.getJSONArray("qualification").get(0).toString(),
                            jsonObject.getString("profileImage"));
                    arrayList.add(teacherCardModel);
                }
                teachers.postValue(arrayList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    String get(String url) throws IOException {
        //Building request to fetch data
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Fetching Response From URL
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}