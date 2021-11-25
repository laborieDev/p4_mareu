package com.example.p4_lamzone_mareu.ui.list;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.p4_lamzone_mareu.R;
import com.example.p4_lamzone_mareu.di.DI;
import com.example.p4_lamzone_mareu.events.DeleteMeetingEvent;
import com.example.p4_lamzone_mareu.model.Meeting;
import com.example.p4_lamzone_mareu.service.DummyMeetingApiService;
import com.example.p4_lamzone_mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    private MeetingApiService meetingApiService;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);

        String meetingStartAt = DI.getMeetingApiService().getStringStartAt(meeting);

        String resumeDatas =
                meeting.getSubject() + " - " + meetingStartAt + " - " +meeting.getMeetingRoom();
        holder.mMeetingResumeDatas.setText(resumeDatas);

        holder.mMeetingAttendees.setText(String.join(", ", meeting.getAllAttendees()));

        setMeetingCircle(holder.mMeetingCircle, meeting);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public void setMeetingCircle(ImageView mCircle, Meeting meeting)
    {
        if (meeting.getIsImportant()) {
            Glide.with(mCircle.getContext())
                    .load(R.drawable.ic_circle_warning)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(mCircle);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_circle)
        public ImageView mMeetingCircle;
        @BindView(R.id.item_list_resume_datas)
        public TextView mMeetingResumeDatas;
        @BindView(R.id.item_list_attendees)
        public TextView mMeetingAttendees;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
