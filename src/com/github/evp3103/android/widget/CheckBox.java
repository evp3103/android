package com.github.evp3103.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.github.evp3103.android.R;

public class CheckBox extends android.widget.CheckBox {

	public CheckBox(Context context) {
		this(context, null);
	}

	public CheckBox(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.checkboxStyle);
	}

	public CheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.CheckBox, defStyle, 0);

		setTypeface(styledAttrs.getString(R.styleable.CheckBox_typeface));

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