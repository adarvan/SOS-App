package com.adarvan.s.o.sprojet;



import com.adarvan.s.o.sprojet.R.string;


import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
//import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener { 
	private Button[][] mButton = new Button[7][7];
	private Button mReset;
	private Button mInstructions;
	private RadioButton RadS; 
	

	private TextView txtv1;
	private TextView txtv2;
	private TextView txtv3;
	private TextView txtv4;

	private Spinner spinner;
	private ArrayAdapter<CharSequence> adapter;

	private int joueur = 1;
	private int[] point = new int[4];
	private int njoueur = 2;

	private boolean debut = true;

	static boolean resetgame = true;

	



	View.OnClickListener a = new View.OnClickListener() {//le listener pour les bouttons pour SOS

		@Override
		public void onClick(View v) {
			// TODO Stub de la méthode généré automatiquement

			Button clicker =  (Button)v;
			int pointsTemp =0;



			if (RadS.isChecked() == true)//si le radioButton pour 's' est selectionné affiché 'S' 
			{

				clicker.setText(string.btn_s);	
				clicker.setClickable(false);//rendre le boutton clické inclickable après



				for (int i = 0; i < mButton.length ; i ++)
				{
					for (int j = 0; j < mButton[i].length ;j++)
					{
						if(clicker == mButton[i][j])//trouver quel boutton a été poussé
						{



							try//voir s'il y a une combinaison gagnant (SOS) vers le haut
							{
								if(mButton[i-1][j].getText() == getString(R.string.btn_o) && 
										mButton[i-2][j].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;//si cette combinaison arrive le joueur compte un point 
									mButton[i][j].setBackgroundColor(Color.RED);//changer la couleur du boutton pour indiqué qu'il fait partie d'une combinaison gagnante
									mButton[i-1][j].setBackgroundColor(Color.RED);
									mButton[i-2][j].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}

							try// vers la gauche gauche
							{
								if(mButton[i][j-1].getText() == getString(R.string.btn_o) && 
										mButton[i][j-2].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i][j-1].setBackgroundColor(Color.RED);
									mButton[i][j-2].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}


							try//droite
							{
								if(mButton[i][j+1].getText() == getString(R.string.btn_o) && 
										mButton[i][j+2].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;

									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i][j+1].setBackgroundColor(Color.RED);
									mButton[i][j+2].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}

							try//bas
							{
								if(mButton[i+1][j].getText() == getString(R.string.btn_o) && 
										mButton[i+2][j].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i+1][j].setBackgroundColor(Color.RED);
									mButton[i+2][j].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}

							try//diagonal haut-droite
							{
								if(mButton[i-1][j+1].getText() == getString(R.string.btn_o) && 
										mButton[i-2][j+2].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i-1][j+1].setBackgroundColor(Color.RED);
									mButton[i-2][j+2].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}

							try//diagonal haut-gauche
							{
								if(mButton[i-1][j-1].getText() == getString(R.string.btn_o) && 
										mButton[i-2][j-2].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i-1][j-1].setBackgroundColor(Color.RED);
									mButton[i-2][j-2].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}


							try//diagonal bas-droite
							{
								if(mButton[i+1][j+1].getText() == getString(R.string.btn_o) && 
										mButton[i+2][j+2].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i+1][j+1].setBackgroundColor(Color.RED);
									mButton[i+2][j+2].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}

							try//diagonal haut-gauche
							{
								if(mButton[i+1][j-1].getText() == getString(R.string.btn_o) && 
										mButton[i+2][j-2].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i+1][j-1].setBackgroundColor(Color.RED);
									mButton[i+2][j-2].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}

						}
					}	
				}

			}

			else
			{

				clicker.setText(string.btn_o);	//si ce n'est pas le radiobutton 's' qui est poussé (donce c'est le 'o')
				clicker.setClickable(false);


				for (int i = 0; i < mButton.length ; i ++)
				{
					for (int j = 0; j < mButton[i].length ;j++)
					{
						if(clicker == mButton[i][j])//trouvé le boutton qui a été clické
						{

							try// voir s'il y a une combinaison gagnant (SOS) de haut en bas
							{
								if(mButton[i-1][j].getText() == getString(R.string.btn_s) && 
										mButton[i+1][j].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED); //changer la couleur du boutton pour indiqué qu'il fait partie d'une combinaison gagnante
									mButton[i-1][j].setBackgroundColor(Color.RED);
									mButton[i+1][j].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}

							try// gauche droite
							{
								if(mButton[i][j-1].getText() == getString(R.string.btn_s) && 
										mButton[i][j+1].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i][j-1].setBackgroundColor(Color.RED);
									mButton[i][j+1].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}


							try//diagonal droite gauche
							{
								if(mButton[i-1][j-1].getText() == getString(R.string.btn_s) && 
										mButton[i+1][j+1].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i-1][j-1].setBackgroundColor(Color.RED);
									mButton[i+1][j+1].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}

							try//diagonal gauche-droite
							{
								if(mButton[i-1][j+1].getText() == getString(R.string.btn_s) && 
										mButton[i+1][j-1].getText() == getString(R.string.btn_s) )
								{
									pointsTemp +=1;
									mButton[i][j].setBackgroundColor(Color.RED);
									mButton[i-1][j+1].setBackgroundColor(Color.RED);
									mButton[i+1][j-1].setBackgroundColor(Color.RED);
								}
							}catch(Exception e){}


						}//if clicker ==
					}//for j	
				}// for i




			}//if rad_s




			point[joueur-1] +=pointsTemp; //ajouté les points marqué au bon joueur
			if(pointsTemp > 0) // si un point(s) a été marqué, affiché le nombre de point
			{
				Toast.makeText(MainActivity.this,"+" + Integer.toString(pointsTemp), Toast.LENGTH_SHORT).show();
			}
			else
			{
				joueur ++; // si aucun point a été marqué, changer de joueur
				if(joueur > njoueur)
				{
					joueur = 1;
				}
			}


			//afficher les points
			txtv1.setText(getString(R.string.J1) +" " + Integer.toString(point[0]));
			txtv2.setText(getString(R.string.J2) +" " + Integer.toString(point[1]));
			txtv3.setText(getString(R.string.J3) + " " + Integer.toString(point[2]));
			txtv4.setText(getString(R.string.J4) + " " + Integer.toString(point[3]));

			switch(joueur)//afficher le text du joueur selectionné en vert
			{
			case 1: 
				txtv1.setTextColor(Color.GREEN);
				txtv2.setTextColor(Color.BLACK);
				txtv3.setTextColor(Color.BLACK);
				txtv4.setTextColor(Color.BLACK);

				break;
			case 2:
				txtv1.setTextColor(Color.BLACK);
				txtv2.setTextColor(Color.GREEN);
				txtv3.setTextColor(Color.BLACK);
				txtv4.setTextColor(Color.BLACK);
				break;
			case 3:
				txtv1.setTextColor(Color.BLACK);
				txtv2.setTextColor(Color.BLACK);
				txtv3.setTextColor(Color.GREEN);
				txtv4.setTextColor(Color.BLACK);
				break;
			case 4:
				txtv1.setTextColor(Color.BLACK);
				txtv2.setTextColor(Color.BLACK);
				txtv3.setTextColor(Color.BLACK);
				txtv4.setTextColor(Color.GREEN);
				break;
			}


			int nombreBtn = 0;
			for (int i = 0; i < mButton.length ; i ++)//voir si tout les boutons ont été poussé
			{
				for (int j = 0; j < mButton[i].length ;j++)
				{
					if(mButton[i][j].isClickable() == false)
					{
						nombreBtn ++;
					}
				}
			}
			if(nombreBtn == 49)//si oui, affihcé qui a gagné
			{
				int yo = 0;
				int yo2 = 0;
				for(int i = 0; i< point.length;i++)
				{
					try
					{

						if(point[i] > point[i-1])
							yo =i+1; 
						else if(point[i] == point[i-1])
						{
							yo2 = i;
						}

					}

					catch(Exception e){}

				}
				if(yo!=0)
				Toast.makeText(MainActivity.this, "Player " + String.valueOf(yo) + " won!", Toast.LENGTH_SHORT).show();//affiché le gagnant
				else if( yo == yo2)
				Toast.makeText(MainActivity.this, "It's a tie", Toast.LENGTH_SHORT).show();//s'il y a égalité
				else 
					Toast.makeText(MainActivity.this, "Something whent wrong! Sorry", Toast.LENGTH_SHORT).show();
			
			}



		}//onclic
	};



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mReset = (Button)findViewById(R.id.reset);//ajouter le boutton reset
		mInstructions = (Button)findViewById(R.id.instructions);


		//ajouter les autres bouttons
		mButton[0][0] = (Button)findViewById(R.id.button1);
		mButton[0][1] = (Button)findViewById(R.id.button2);
		mButton[0][2] = (Button)findViewById(R.id.button3);
		mButton[0][3] = (Button)findViewById(R.id.button4);
		mButton[0][4] = (Button)findViewById(R.id.button5);
		mButton[0][5] = (Button)findViewById(R.id.button6);
		mButton[0][6] = (Button)findViewById(R.id.button7);

		mButton[1][0] = (Button)findViewById(R.id.button8);
		mButton[1][1] = (Button)findViewById(R.id.button9);
		mButton[1][2] = (Button)findViewById(R.id.button10);
		mButton[1][3] = (Button)findViewById(R.id.button11);
		mButton[1][4] = (Button)findViewById(R.id.button12);
		mButton[1][5] = (Button)findViewById(R.id.button13);
		mButton[1][6] = (Button)findViewById(R.id.button14);

		mButton[2][0] = (Button)findViewById(R.id.button15);
		mButton[2][1] = (Button)findViewById(R.id.button16);
		mButton[2][2] = (Button)findViewById(R.id.button17);
		mButton[2][3] = (Button)findViewById(R.id.button18);
		mButton[2][4] = (Button)findViewById(R.id.button19);
		mButton[2][5] = (Button)findViewById(R.id.button20);
		mButton[2][6] = (Button)findViewById(R.id.button21);

		mButton[3][0] = (Button)findViewById(R.id.button22);
		mButton[3][1] = (Button)findViewById(R.id.button23);
		mButton[3][2] = (Button)findViewById(R.id.button24);
		mButton[3][3] = (Button)findViewById(R.id.button25);
		mButton[3][4] = (Button)findViewById(R.id.button26);
		mButton[3][5] = (Button)findViewById(R.id.button27);
		mButton[3][6] = (Button)findViewById(R.id.button28);

		mButton[4][0] = (Button)findViewById(R.id.button29);
		mButton[4][1] = (Button)findViewById(R.id.button30);
		mButton[4][2] = (Button)findViewById(R.id.button31);
		mButton[4][3] = (Button)findViewById(R.id.button32);
		mButton[4][4] = (Button)findViewById(R.id.button33);
		mButton[4][5] = (Button)findViewById(R.id.button34);
		mButton[4][6] = (Button)findViewById(R.id.button35);

		mButton[5][0] = (Button)findViewById(R.id.button36);
		mButton[5][1] = (Button)findViewById(R.id.button37);
		mButton[5][2] = (Button)findViewById(R.id.button38);
		mButton[5][3] = (Button)findViewById(R.id.button39);
		mButton[5][4] = (Button)findViewById(R.id.button40);
		mButton[5][5] = (Button)findViewById(R.id.button41);
		mButton[5][6] = (Button)findViewById(R.id.button42);

		mButton[6][0] = (Button)findViewById(R.id.button43);
		mButton[6][1] = (Button)findViewById(R.id.button44);
		mButton[6][2] = (Button)findViewById(R.id.button45);
		mButton[6][3] = (Button)findViewById(R.id.button46);
		mButton[6][4] = (Button)findViewById(R.id.button47);
		mButton[6][5] = (Button)findViewById(R.id.button48);
		mButton[6][6] = (Button)findViewById(R.id.button49);


		//ajouté le spinner (comme un combo box)
		spinner = (Spinner) findViewById(R.id.nJoueur); 
		adapter = ArrayAdapter.createFromResource(this,R.array.nj, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);

		RadS = (RadioButton) findViewById(R.id.radioS);
		//RadO = (RadioButton) findViewById(R.id.radioO);

		txtv1 =(TextView)findViewById(R.id.joueur1);
		txtv2 =(TextView)findViewById(R.id.joueur2);
		txtv3 =(TextView)findViewById(R.id.joueur3);
		txtv4 =(TextView)findViewById(R.id.joueur4);

		txtv1.setTextColor(Color.GREEN);


		for (int i = 0; i < mButton.length ; i ++) //ajouté le listener à chaque boutton
		{
			for (int j = 0; j < mButton[i].length ;j++)
			{
				mButton[i][j].setOnClickListener(a);
				mButton[i][j].setBackgroundResource(android.R.drawable.btn_default);//changer le background du boutton
			}
		}
		mReset.setOnClickListener(new View.OnClickListener(){//ajouter ce listener au boutton reset
			@Override
			public void onClick(View v) {
				reset();
			}});
		mInstructions.setOnClickListener(new View.OnClickListener(){//ajouter ce listener au boutton Instructions
			@Override
			public void onClick(View v) {
				instruction();
			}});

	}

	public void instruction()//afficher les instructions (je ne sais pas pourquoi je suis obligé de le mettre dans une methode mais ça
	//ne marche pas autrement)
	{
		
		/*Les joueurs prennent des tours pour ajouter ou un « S » ou un « O » sur n’importe quel carré " +
		"(Tu n’es pas obligé d’utilisé la même lettres chaque tours)." + "\n"+
		" L’objet du jeu est que chaque joueurs essaye de de créer une suite S-O-S sur des carré connecté (Soi sur une diagonal, horizontal, verticale)" +
		" et de créer au temps  de combinaisons possibles." +"\n"+" Si un joueur créer une combinaison S-O-S il marque un point et rejoue. " +
		"S’il ne marque pas de points, c’est le tour du prochain joueur.*/
		AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
		builder2
		.setTitle("Instructions")
		.setMessage("The players take turns but down either and 'S' or and 'O' on any open square on the board. "+
		"The point of the game is to create the sequence S-O-S using any letters already on the board "+
				"and the one you are placing. If you do, you score a point and get to place another letter. "+
		"This keeps going until you do not score a point, at which point it is the next players turn. The one with the most points wins!")
				.setPositiveButton("OK", null)						
				.show();	
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void reset()//si reset est appeler, affiché un messagebox pour s'assuré que l'utilisateur est sur de son choix
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder
		.setTitle("Restart the game?")
		.setMessage("Are you sure you wanna restart?")
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {			      	



				for(int k = 0; k< 4;k++)
					point[k] = 0;
				txtv1.setText(getString(R.string.J1) +" " + Integer.toString(point[0]));
				txtv2.setText(getString(R.string.J2) +" " + Integer.toString(point[1]));
				txtv3.setText(getString(R.string.J3) + " " + Integer.toString(point[2]));
				txtv4.setText(getString(R.string.J4) + " " + Integer.toString(point[3]));


				switch(njoueur)//change la visibiliter des text view dependans du nombre de joueurs
				{
				case 2:
					txtv1.setVisibility(View.VISIBLE);
					txtv2.setVisibility(View.VISIBLE);
					txtv3.setVisibility(View.INVISIBLE);
					txtv4.setVisibility(View.INVISIBLE);
					break;
				case 3:
					txtv1.setVisibility(View.VISIBLE);
					txtv2.setVisibility(View.VISIBLE);
					txtv3.setVisibility(View.VISIBLE);
					txtv4.setVisibility(View.INVISIBLE);
					break;
				case 4:
					txtv1.setVisibility(View.VISIBLE);
					txtv2.setVisibility(View.VISIBLE);
					txtv3.setVisibility(View.VISIBLE);
					txtv4.setVisibility(View.VISIBLE);
					break;
				}





				for (int i = 0; i < mButton.length ; i ++) //ajouté le listener à chaque boutton
				{
					for (int j = 0; j < mButton[i].length ;j++)
					{
						mButton[i][j].setText(R.string.btn_default);
						mButton[i][j].setBackgroundResource(android.R.drawable.btn_default);//changé le background du boutton
						mButton[i][j].setClickable(true);

					}

				}	
			}
		})
		.setNegativeButton("No", null)						
		.show();

	}






	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {



		njoueur =  Integer.valueOf( parent.getItemAtPosition(pos).toString());

		if(debut)//ne pas activé reset() au debut
		{
			debut = false;

		}
		else
		{
			reset();
		}




	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Stub de la méthode généré automatiquement
	}	


}
