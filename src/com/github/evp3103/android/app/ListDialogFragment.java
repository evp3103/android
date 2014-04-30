package com.github.evp3103.android.app;

import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ListDialogFragment extends DialogFragment {

	public static final String WHICH = "which";

	private static final String TITLE = "title";
	private static final String CANCEL = "cancel";
	private static final String ITEMS = "items";

	public void setArguments(String title, String cancel, Collection<String> items) {
		setArguments(title, cancel, items.toArray(new String[0]));
	}

	public void setArguments(String title, String cancel, String[] items) {
		Bundle args = new Bundle();
		args.putString(TITLE, title);
		args.putString(CANCEL, cancel);
		args.putStringArray(ITEMS, items);

		setArguments(args);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setTitle(getArguments().getString(TITLE))
				.setNegativeButton(getArguments().getString(CANCEL), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
					}
				})
				.setItems(getArguments().getStringArray(ITEMS), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent data = new Intent();
						data.putExtra(WHICH, which);

						getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, data);
					}
				})
				.create();
	}

}