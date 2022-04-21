package com.devoverse.wallah.physics.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devoverse.wallah.physics.R;
import com.devoverse.wallah.physics.adapters.TeacherCardAdapter;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private CircularProgressIndicator loader;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecyclerView();
        loader = view.findViewById(R.id.main_loader);

        //Using ViewModel to fetch and Store data
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getTeachers().observe(getViewLifecycleOwner(), teacherCardModels -> {
            //Hiding Loader and making recycler view visible
            loader.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            //Updating RecyclerView on data updates
            TeacherCardAdapter teacherCardAdapter = new TeacherCardAdapter(teacherCardModels);
            recyclerView.setAdapter(teacherCardAdapter);
        });

    }

    private void initializeRecyclerView() {
        recyclerView = getView().findViewById(R.id.main_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


}