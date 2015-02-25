package fr.istic.geoartiste.activities;

import java.util.ArrayList;
import java.util.List;

import fr.istic.geoartiste.R;
import fr.istic.geoartiste.adapters.MenuAdapter;
import fr.istic.geoartiste.models.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends GenericActivity {

	private GridView mGridview;
	private ArrayList<MenuItem> mMenuItems;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mGridview = (GridView) findViewById(R.id.gridview);
		mMenuItems = new ArrayList<MenuItem>();

		//Construction du menu
		mMenuItems.add(new MenuItem(getString(R.string.menu0), new FindArtisteActivity()));
		mGridview.setAdapter(new MenuAdapter(this, mMenuItems));

		//Construction des ecouteurs
		mGridview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(MainActivity.this, mMenuItems.get(position).activity.getClass());
				startActivity(intent);

			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public String toMenu() {
		// TODO Auto-generated method stub
		return "Accueil";
	}
}
