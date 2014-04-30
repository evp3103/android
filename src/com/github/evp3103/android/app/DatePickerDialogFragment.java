package com.github.evp3103.android.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	public static final String DATE = "date";
	public static final String YEAR = "year";
	public static final String MONTH = "month";
	public static final String DAY = "day";

	private int mYear;
	private int mMonth;
	private int mDay;

	public void setArguments(String date) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(date));
			// calendar.setTime(DateFormat.getDateInstance().parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		setArguments(year, month, day);
	}

	public void setArguments(int year, int month, int day) {
		Bundle args = new Bundle();
		args.putInt(YEAR, year);
		args.putInt(MONTH, month);
		args.putInt(DAY, day);

		setArguments(args);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mYear = getArguments().getInt(YEAR);
		mMonth = getArguments().getInt(MONTH);
		mDay = getArguments().getInt(DAY);

		return new DatePickerDialog(getActivity(), this, mYear, mMonth, mDay) {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				super.onClick(dialog, which);

				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, mYear);
					calendar.set(Calendar.MONTH, mMonth);
					calendar.set(Calendar.DAY_OF_MONTH, mDay);

					Intent data = new Intent();
					data.putExtra(YEAR, mYear);
					data.putExtra(MONTH, mMonth);
					data.putExtra(DAY, mDay);
					data.putExtra(DATE, new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(calendar.getTime()));
					// data.putExtra(DATE, DateFormat.getDateInstance().format(calendar.getTime()));

					getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, data);
					break;
				case DialogInterface.BUTTON_NEGATIVE:
					getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
					break;
				case DialogInterface.BUTTON_NEUTRAL:
					getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_FIRST_USER, null);
					break;
				}
			}
		};
	}

	// ****************************************************************
	// DatePickerDialog.OnDateSetListener
	// ****************************************************************

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		mYear = year;
		mMonth = month;
		mDay = day;
	}

}