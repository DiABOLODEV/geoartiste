package fr.istic.geoartiste.activities;

import java.util.ArrayList;

import org.json.JSONArray;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ViewFlipper;
import fr.istic.geoartiste.R;
import fr.istic.geoartiste.utils.TraceLog;
import fr.istic.geoartiste.webservices.RestClient;

public class FindArtisteActivity extends GenericActivity{
	
	private ViewFlipper mViewFlipper;
	private Spinner mType;
	private Spinner mMatiere;
	private Spinner mCouleur;
	private Spinner mAge;
	private Button mChercher;
	private RadioButton mGeolocalisation;
	
	private RestClient mRestClient;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findartiste);
		
		mViewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
		mType = (Spinner) findViewById(R.id.type);
		mMatiere = (Spinner) findViewById(R.id.matiere);
		mCouleur = (Spinner) findViewById(R.id.couleur);
		mAge = (Spinner) findViewById(R.id.age);
		mGeolocalisation = (RadioButton) findViewById(R.id.geolocalisation);
		mChercher = (Button) findViewById(R.id.chercher);
		
		//Construction des ecouteurs
		mChercher.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				//on recupere les parametres
				String type = mType.getSelectedItem().toString();
				String matiere = mMatiere.getSelectedItem().toString();
				String couleur = mCouleur.getSelectedItem().toString();
				String age = mAge.getSelectedItem().toString();
				int longitude = 0;
				int latitude = 0;
				
				//On regarde si la geolocalisation est cochee 
				if(mGeolocalisation.isChecked()){
					
				}
				
				RequestParams params = new RequestParams();
				params.put("type", type);
				params.put("matiere", matiere);
				params.put("couleur", couleur);
				params.put("age", age);
				params.put("longitude", longitude);
				params.put("latitude", latitude);
				
				//On fait la requête
			    mRestClient.setCustomBaseUrl("http://geo-artiste.irisa.fr");
			    mRestClient.get("/api", params, new JsonHttpResponseHandler(){
			    	@Override
			    	public void onStart() {
			    		super.onStart();
			    		
			    		//On change de vue pour montrer le chargement
			    		mViewFlipper.setInAnimation(FindArtisteActivity.this, R.anim.slide_in_from_left);
			    		mViewFlipper.setOutAnimation(FindArtisteActivity.this, R.anim.slide_out_from_right);
		                mViewFlipper.showNext();
			    	}
			    	@Override
			    	public void onSuccess(int statusCode, JSONArray response) {
			    		super.onSuccess(statusCode, response);
			    		TraceLog.debug("reponse à l'appel de l'API pour la recherche d'un artiste : "+response.toString());
			    		
			    		//On change de vue pour montrer le resultat
			    		mViewFlipper.setInAnimation(FindArtisteActivity.this, R.anim.slide_in_from_left);
			    		mViewFlipper.setOutAnimation(FindArtisteActivity.this, R.anim.slide_out_from_right);
		                mViewFlipper.showNext();
			    	}
			    	
			    	@Override
			    	public void onFailure(String responseBody, Throwable error) {
			    		super.onFailure(responseBody, error);
			    		TraceLog.error("erreur dans l'appel de l'API pour la recherche d'un artiste");
			    		
			    		//On revient a la premiere vue 
			    		mViewFlipper.setInAnimation(FindArtisteActivity.this, R.anim.slide_in_from_right);
			    		mViewFlipper.setOutAnimation(FindArtisteActivity.this, R.anim.slide_out_from_left);
			    		mViewFlipper.showPrevious();
			    		mViewFlipper.showPrevious();
			    	}
			    	
			    });
			}
		});
	}
	
	@Override
	public String toMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
