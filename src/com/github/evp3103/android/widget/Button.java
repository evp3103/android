package com.github.evp3103.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.github.evp3103.android.R;

public class Button extends android.widget.Button {

	public Button(Context context) {
		this(context, null);
	}

	public Button(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.buttonStyle);
	}

	public Button(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setMinWidth(0);
		setMinHeight(0);
		setMinimumWidth(0);
		setMinimumHeight(0);

		TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.Button, defStyle, 0);

		setTypeface(styledAttrs.getString(R.styleable.Button_typeface));

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