package com.github.evp3103.android.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class InfinitePagerAdapter extends PagerAdapter {

	public int getPosition(int position) {
		return position % getSize();
	}

	public abstract int getSize();

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	public abstract View getView(int position, ViewGroup container);

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = getView(getPosition(position), container);
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

}