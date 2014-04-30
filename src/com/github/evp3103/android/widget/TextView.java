package com.github.evp3103.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.AttributeSet;

import com.github.evp3103.android.R;

public class TextView extends android.widget.TextView {

	// private boolean mAutoScrolling;
	// private TextUtils.TruncateAt mEllipsize;
	// private int mMarqueeRepeatLimit;
	// private int mMaxLines;
	// private boolean mHorizontalFadingEdgeEnabled;
	// private boolean mHorizontallyScrolling;
	// private boolean mSingleLine;

	public TextView(Context context) {
		this(context, null);
	}

	public TextView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.textViewStyle);
	}

	public TextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TextView, defStyle, 0);

		// if (mAutoScrolling = styledAttrs.getBoolean(R.styleable.TextView_autoScrolling, false)) {
		// setAutoScrolling(true);
		// }
		setTypeface(styledAttrs.getString(R.styleable.TextView_typeface));

		styledAttrs.recycle();

		styledAttrs = context.obtainStyledAttributes(attrs, new int[] {
				android.R.attr.lineSpacingExtra,
				android.R.attr.lineSpacingMultiplier,
		});

		setLineSpacing(styledAttrs.getDimensionPixelSize(0, 0), styledAttrs.getFloat(1, 1));

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

	private boolean mNegativeLineSpacing = false;

	@Override
	public void setLineSpacing(float add, float mult) {
		mNegativeLineSpacing = add < 0 || mult < 1;
		super.setLineSpacing(add, mult);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		if (mNegativeLineSpacing) {
			Layout layout = getLayout();
			int height = getLineHeight();

			setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + (int) ((height - layout.getSpacingAdd()) / layout.getSpacingMultiplier()) - height);
		}
	}

	// public boolean isAutoScrolling() {
	// return mAutoScrolling;
	// }
	//
	// public void setAutoScrolling(boolean autoScrolling) {
	// if (autoScrolling) {
	// super.setEllipsize(TextUtils.TruncateAt.MARQUEE);
	// super.setMarqueeRepeatLimit(-1);
	// Log.e("" + this, "setAutoScrolling " + autoScrolling + " " + mMaxLines);
	// super.setMaxLines(1);
	// super.setHorizontalFadingEdgeEnabled(true);
	// super.setHorizontallyScrolling(true);
	// // super.setSingleLine(true);
	// } else {
	// super.setEllipsize(mEllipsize);
	// super.setMarqueeRepeatLimit(mMarqueeRepeatLimit);
	// Log.e("" + this, "setAutoScrolling " + autoScrolling + " " + mMaxLines);
	// super.setMaxLines(mMaxLines);
	// super.setHorizontalFadingEdgeEnabled(mHorizontalFadingEdgeEnabled);
	// super.setHorizontallyScrolling(mHorizontallyScrolling);
	// // super.setSingleLine(mSingleLine);
	// }
	// mAutoScrolling = autoScrolling;
	// }
	//
	// @Override
	// public void setEllipsize(TextUtils.TruncateAt where) {
	// if (!isAutoScrolling()) {
	// super.setEllipsize(where);
	// }
	// mEllipsize = where;
	// }
	//
	// @Override
	// public void setMarqueeRepeatLimit(int marqueeLimit) {
	// if (!isAutoScrolling()) {
	// super.setMarqueeRepeatLimit(marqueeLimit);
	// }
	// mMarqueeRepeatLimit = marqueeLimit;
	// }
	//
	// public void setMaxLines(int maxlines) {
	// if (!isAutoScrolling()) {
	// super.setMaxLines(maxlines);
	// }
	// Log.e("" + this, "setMaxLines " + maxlines);
	// mMaxLines = maxlines;
	// }
	//
	// @Override
	// public void setHorizontalFadingEdgeEnabled(boolean horizontalFadingEdgeEnabled) {
	// if (!isAutoScrolling()) {
	// super.setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled);
	// }
	// mHorizontalFadingEdgeEnabled = horizontalFadingEdgeEnabled;
	// }
	//
	// @Override
	// public void setHorizontallyScrolling(boolean whether) {
	// if (!isAutoScrolling()) {
	// super.setHorizontallyScrolling(whether);
	// }
	// mHorizontallyScrolling = whether;
	// }
	//
	// @Override
	// public void setSingleLine(boolean singleLine) {
	// if (!isAutoScrolling()) {
	// super.setSingleLine(singleLine);
	// }
	// mSingleLine = singleLine;
	// }
	//
	// @Override
	// protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
	// if (!isAutoScrolling() || focused) {
	// super.onFocusChanged(focused, direction, previouslyFocusedRect);
	// }
	// }
	//
	// @Override
	// public void onWindowFocusChanged(boolean focused) {
	// if (!isAutoScrolling() || focused) {
	// super.onWindowFocusChanged(focused);
	// }
	// }
	//
	// @Override
	// public boolean isFocused() {
	// if (!isAutoScrolling()) {
	// return super.isFocused();
	// }
	// return true;
	// }

}