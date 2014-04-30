package com.github.evp3103.android.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class ArrayAdapter<T> extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<T> mItems;

	private final Object mLock = new Object();

	public ArrayAdapter(Context context) {
		this(context, new ArrayList<T>());
	}

	public ArrayAdapter(Context context, T[] objects) {
		this(context, Arrays.asList(objects));
	}

	public ArrayAdapter(Context context, List<T> objects) {
		mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mItems = new ArrayList<T>(objects);
	}

	public void add(T item) {
		synchronized (mLock) {
			mItems.add(item);
		}
		notifyDataSetChanged();
	}

	public void addAll(Collection<? extends T> items) {
		synchronized (mLock) {
			mItems.addAll(items);
		}
		notifyDataSetChanged();
	}

	public void addAll(T... items) {
		synchronized (mLock) {
			Collections.addAll(mItems, items);
		}
		notifyDataSetChanged();
	}

	public void insert(T item, int index) {
		synchronized (mLock) {
			mItems.add(index, item);
		}
		notifyDataSetChanged();
	}

	public void remove(T item) {
		synchronized (mLock) {
			mItems.remove(item);
		}
		notifyDataSetChanged();
	}

	public void clear() {
		synchronized (mLock) {
			mItems.clear();
		}
		notifyDataSetChanged();
	}

	public void sort(Comparator<? super T> comparator) {
		synchronized (mLock) {
			Collections.sort(mItems, comparator);
		}
		notifyDataSetChanged();
	}

	public Context getContext() {
		return mContext;
	}

	public LayoutInflater getInflater() {
		return mInflater;
	}

	public List<T> getItems() {
		return mItems;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	public int getPosition(T item) {
		return mItems.indexOf(item);
	}

	@Override
	public T getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}