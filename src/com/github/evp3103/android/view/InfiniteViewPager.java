package com.github.evp3103.android.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class InfiniteViewPager extends ViewPager {

	public InfiniteViewPager(Context context) {
		this(context, null);
	}

	public InfiniteViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setAdapter(InfinitePagerAdapter adapter) {
		super.setAdapter(adapter);

		if (adapter != null) {
			setCurrentItem(adapter.getCount() / 2 - adapter.getCount() / 2 % adapter.getSize() + adapter.getPosition(getCurrentItem()), false);
		}
	}

}