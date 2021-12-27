package com.example.p4_lamzone_mareu.ui.list;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.p4_lamzone_mareu.R;
import com.example.p4_lamzone_mareu.TimePickerFragment;
import com.example.p4_lamzone_mareu.di.DI;
import com.example.p4_lamzone_mareu.events.DeleteMeetingEvent;
import com.example.p4_lamzone_mareu.model.Meeting;
import com.example.p4_lamzone_mareu.model.MeetingRoom;
import com.example.p4_lamzone_mareu.service.MeetingApiService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingListActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private MeetingApiService mApiService;
    private List<Meeting> mMeetings;

    @BindView(R.id.list_neighbours)
    public RecyclerView mRecyclerView;
    public MyMeetingRecyclerViewAdapter mAdaptor;

    private TextInputEditText mEditHourText;
    private int meetingStartHour = 7;
    private int meetingStartMin = 0;
    private MeetingRoom actualMeetingRoomSelected;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    @Nullable
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        ButterKnife.bind(this);

        mApiService = DI.getMeetingApiService();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mMeetings = mApiService.getMeetings();
        mAdaptor = new MyMeetingRecyclerViewAdapter(mMeetings);
        mRecyclerView.setAdapter(mAdaptor);
    }

    private void reInitList(int position, boolean isDeleteAction) {
        Meeting meeting = mMeetings.get(position);

        if (!isDeleteAction) {
            mAdaptor.notifyItemInserted(position);
        } else {
            mAdaptor.notifyItemRemoved(position);
            mApiService.deleteMeeting(meeting);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.add_meeting)
    void addNeighbour() {
        // Init Dialog
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_add_meeting);
        dialog.show();

        // Set focus on Hour EditText
        mEditHourText = dialog.findViewById(R.id.meeting_hour);
        mEditHourText.setFocusable(false);

        // Init all MeetingRooms for Select
        MeetingRoom[] meetingRooms = mApiService.getMeetingRooms();
        // Init MeetingRooms Select
        Spinner spinner = dialog.findViewById(R.id.spinner1);

        MeetingRoomSpinnerAdapter adapter = new MeetingRoomSpinnerAdapter(MeetingListActivity.this,
                android.R.layout.simple_spinner_item, meetingRooms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Init Select On Change Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualMeetingRoomSelected = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Save new Meeting Listener
        dialog.findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewMeeting(dialog);
            }
        });
    }

    public void saveNewMeeting(Dialog dialog)
    {
        TextInputEditText mTitle = dialog.findViewById(R.id.meeting_title);
        TextInputEditText mAllAttendees = dialog.findViewById(R.id.meeting_all_attendees);

        String title = mTitle.getText().toString();
        String allAttendeesText = mAllAttendees.getText().toString();
        List<String> allAttendees = new ArrayList<String>(Arrays.asList(allAttendeesText.split(",")));
        Date startAt = new Date(2022, 1, 1, meetingStartHour, meetingStartMin);

        Meeting newMeeting = new Meeting(System.currentTimeMillis(), title, actualMeetingRoomSelected, allAttendees, startAt);
        mMeetings = mApiService.createMeeting(newMeeting);

        dialog.dismiss();
        reInitList(mMeetings.size() - 1, false);
    }

    public void showDatePickerDialog(View v) {
        // https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
        //new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String newMinute = "" + minute;

        if (minute < 10) newMinute = "0" + minute;

        String newHour = hourOfDay + ":" + newMinute;
        mEditHourText.setText(newHour);
        meetingStartHour = hourOfDay;
        meetingStartMin = minute;
    }

    /**
     * Fired if the user clicks on a delete button
     */
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        int position = mMeetings.indexOf(event.meeting);
        reInitList(position, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.search_btn);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Rechercher une réunion ...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                System.out.println("Text ["+ newText +"]");
                mAdaptor.getFilter().filter(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
