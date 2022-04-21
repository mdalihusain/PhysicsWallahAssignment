package com.devoverse.wallah.physics.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.devoverse.wallah.physics.R;
import com.devoverse.wallah.physics.models.TeacherCardModel;
import com.devoverse.wallah.physics.utils.DownloadImageTask;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TeacherCardAdapter extends RecyclerView.Adapter<TeacherCardAdapter.ViewHolder> {
    ArrayList<TeacherCardModel> localDataSet;

    public TeacherCardAdapter(ArrayList<TeacherCardModel> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTeacherName;
        private final TextView tvTeacherSubjectQualification;
        private final TextView tvViewMore;
        private final ShapeableImageView shapeableImageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvTeacherName = view.findViewById(R.id.teacher_card_tv_name);
            tvTeacherSubjectQualification = view.findViewById(R.id.teacher_card_tv_subject_qualification);
            tvViewMore = view.findViewById(R.id.teacher_card_btn_view_more);
            shapeableImageView = view.findViewById(R.id.teacher_card_image);
        }

        public TextView getTvTeacherName() {
            return tvTeacherName;
        }

        public TextView getTvTeacherSubjectQualification() {
            return tvTeacherSubjectQualification;
        }

        public TextView getTvViewMore() {
            return tvViewMore;
        }

        public ShapeableImageView getShapeableImageView() {
            return shapeableImageView;
        }
    }

    @NonNull
    @Override
    public TeacherCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_teacher_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // SETTING VALUES TO TEACHER CARD
        TeacherCardModel model = localDataSet.get(position);
        holder.getTvTeacherName().setText(model.getTeacherName());
        holder.getTvTeacherSubjectQualification().setText(model.getTeacherSubject() + "  \u2022  " + model.getTeacherQualification());
        holder.getTvViewMore().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // VIEW TEACHER DETAILS
                int id = model.getTeacherID();
            }
        });

        // LAZY LOADING TEACHER IMAGE
        new DownloadImageTask(holder.getShapeableImageView()).execute(model.getTeacherImageURL());

        // CHECKING IS IT IS THE LAST ELEMENT
        if (position == localDataSet.size() - 1) {
            // ADDING BOTTOM MARGIN TO LAST ELEMENT
            CardView cardView = (CardView) holder.getTvTeacherName().getParent().getParent();
            CardView.LayoutParams params = new CardView.LayoutParams(
                    CardView.LayoutParams.WRAP_CONTENT,
                    CardView.LayoutParams.WRAP_CONTENT
            );
            int bottomPx = convertDpToPx(32, cardView.getContext());
            int topPx = convertDpToPx(16, cardView.getContext());
            params.setMargins(0, topPx, 0, bottomPx);
            cardView.setLayoutParams(params);
        }
    }

    private int convertDpToPx(int dp, Context context) {
        // CONVERTING DP TO PIXELS
        Resources r = context.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
        return px;
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


}
