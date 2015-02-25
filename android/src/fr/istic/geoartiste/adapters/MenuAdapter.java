package fr.istic.geoartiste.adapters;


import java.util.List;

import fr.istic.geoartiste.R;
import fr.istic.geoartiste.activities.GenericActivity;
import fr.istic.geoartiste.models.MenuItem;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	
	private List<MenuItem> mItems;
	private Activity mActivity;

	public MenuAdapter(GenericActivity activity, List<MenuItem> items){
		this.mItems =  items;
		this.mActivity = activity;
	}
	public int getCount() {
		return mItems.size();
	}

	public MenuItem getItem(int position) {
		return mItems.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolderMenu viewHolder;
		Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "medium.ttf");
		if (convertView == null) {
			viewHolder = new ViewHolderMenu();
			convertView = mActivity.getLayoutInflater().inflate(
					R.layout.item_menu, parent, false);

			viewHolder.title = (TextView) convertView
					.findViewById(R.id.title_id);
			
			viewHolder.title.setTypeface(font);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolderMenu) convertView.getTag();
		}

		viewHolder.title.setText(getItem(position).title);
		viewHolder.title.setTypeface(font, Typeface.NORMAL);
		viewHolder.title.setBackgroundColor(mActivity.getResources().getColor(android.R.color.transparent));
		viewHolder.title.setTextColor(mActivity.getResources().getColor(R.color.main));
		
		return convertView;
	}
	
	/**
	 * View holder for menu
	 */
	private class ViewHolderMenu {
		public TextView title;
	}
	
}

