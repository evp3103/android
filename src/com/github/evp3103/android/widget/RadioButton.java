package com.github.evp3103.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.github.evp3103.android.R;

public class RadioButton extends android.widget.RadioButton {

	public RadioButton(Context context) {
		this(context, null);
	}

	public RadioButton(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.radioButtonStyle);
	}

	public RadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.RadioButton, defStyle, 0);

		setTypeface(styledAttrs.getString(R.styleable.RadioButton_typeface));

		styledAttrs.recycle();
	}

	public void setTypeface(String typeface) {
		if (typeface != null) {
			setTypeface(Typeface.createFromAsset(getContext().getAssets(), typeface));
		}
	}

	public void setTypefaceId(int typefaceId) {
		setTypeface(getContext().getResources().getString(typefaceId));
	}

}