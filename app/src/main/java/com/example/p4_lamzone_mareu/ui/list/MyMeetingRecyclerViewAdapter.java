package com.example.p4_lamzone_mareu.ui.list;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p4_lamzone_mareu.R;
import com.example.p4_lamzone_mareu.di.DI;
import com.example.p4_lamzone_mareu.events.DeleteMeetingEvent;
import com.example.p4_lamzone_mareu.model.Meeting;
import com.example.p4_lamzone_mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter
        extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder>
        implements Filterable {

    private List<Meeting> mMeetings;
    private List<Meeting> mMeetingsFiltered;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
        mMeetingsFiltered = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_meeting, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetingsFiltered.get(position);

        String meetingStartAt = DI.getMeetingApiService().getStringStartAt(meeting);

        String resumeDatas =
                meeting.getSubject() + " - " + meetingStartAt + " - " +meeting.getMeetingRoom().getName();
        holder.mMeetingResumeDatas.setText(resumeDatas);

        holder.mMeetingAttendees.setText(String.join(", ", meeting.getAllAttendees()));

        int meetingRoomColor = Color.parseColor(meeting.getMeetingRoom().getColor());
        holder.mMeetingCircle.setColorFilter(meetingRoomColor);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetingsFiltered.size();
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

    public void updateList(List<Meeting> newList) {
        mMeetings = newList;
        mMeetingsFiltered = newList;
    }

    public void addMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mMeetingsFiltered = mMeetings;
                } else {
                    List<Meeting> filteredList = new ArrayList<>();
                    for (Meeting meeting : mMeetings) {
                        String hourStartAt = DI.getMeetingApiService().getStringStartAt(meeting);

                        if (
                                meeting.getSubject().toLowerCase().contains(charString.toLowerCase()) ||
                                meeting.getMeetingRoom().getName().toLowerCase().contains(charString.toLowerCase()) ||
                                hourStartAt.contains(charString)
                        ) {
                            filteredList.add(meeting);
                        }
                    }

                    mMeetingsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mMeetingsFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mMeetingsFiltered = (ArrayList<Meeting>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
}
