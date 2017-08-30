package com.byoutline.secretsauce.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import com.byoutline.secretsauce.Settings;
import com.byoutline.secretsauce.events.DateSetEvent;
import com.byoutline.secretsauce.utils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = LogUtils.internalMakeLogTag(DatePickerFragment.class);
    private static String DATE_FORMAT_STRING = "dd/MM/yyyy";
    public final Calendar calendar = Calendar.getInstance();

    public static SimpleDateFormat setDisplayDateFormat(String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        DATE_FORMAT_STRING = dateFormat;
        return format;
    }

    public static SimpleDateFormat getDisplayDateFormat() {
        return new SimpleDateFormat(DATE_FORMAT_STRING);
    }

    public void setDateString(String dateString) {
        try {
            Date date = getDisplayDateFormat().parse(dateString);
            calendar.setTime(date);
        } catch (ParseException e) {
            LogUtils.LOGE(TAG, "", e);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);

        String date = getDisplayDateFormat().format(calendar.getTime());
        Settings.INSTANCE.getBUS().post(new DateSetEvent(date));
    }
}
