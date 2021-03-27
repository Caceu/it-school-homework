package com.example.mediaplayer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PlayerFragment extends Fragment {
    private Audio audioPlayer;

    public PlayerFragment() {
        audioPlayer = new Audio();
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button).setOnClickListener(v -> {
            audioPlayer.play(getActivity());
            Button btn = (Button)v;
            if(audioPlayer.isPlaying()){
                btn.setText(R.string.stop);
            }
            else {
                btn.setText(R.string.play);
            }
        });
    }
}