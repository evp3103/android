package com.github.evp3103.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.github.evp3103.android.R;

public class GridView extends android.widget.GridView {

	private boolean mExpanded;

	public GridView(Context context) {
		this(context, null);
	}

	public GridView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.gridViewStyle);
	}

	public GridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.GridView, defStyle, 0);

		setExpanded(styledAttrs.getBoolean(R.styleable.GridView_expanded, false));

		styledAttrs.recycle();
	}

	public boolean isExpanded() {
		return mExpanded;
	}

	public void setExpanded(boolean expanded) {
		mExpanded = expanded;
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// HACK! TAKE THAT ANDROID!
		if (isExpanded()) {
			// Calculate entire height by providing a very large height hint.
			// View.MEASURED_SIZE_MASK represents the largest height possible.
			super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST));

			getLayoutParams().height = getMeasuredHeight();
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

}