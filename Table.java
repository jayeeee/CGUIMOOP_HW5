import java.util.ArrayList;

public class Table {

	public static int MAXPLAYER = 4;
	private Deck deck;
	private Player[] players;
	private Dealer dealer;
	private int[] pos_betArray;
	
	public Table(int nDeck){
		deck = new Deck(nDeck);
		players = new Player[MAXPLAYER];
		pos_betArray = new int[MAXPLAYER];
	}
	
	public void set_player(int pos,Player p){
		players[pos] = p;
	}
	
	public Player[] get_player(){
		return players;
	}
	
	public void set_dealer(Dealer d){
		dealer = d;
	}
	
	public Card get_face_up_card_of_dealer(){
		
		return dealer.getOneRoundCard().get(1);
		
	}
	
	private void ask_each_player_about_bets(){
		
		
		for(int i=0;i<players.length;i++){
			players[i].say_hello();
			
			if(pos_betArray[i]==0){
				pos_betArray[i] = players[i].make_bet();			
			}
		}
		
	}
	
	private void distribute_cards_to_dealer_and_players(){
		for(Player p:players){
			
			ArrayList<Card> Pcards = new ArrayList<Card>();
			Pcards.add(deck.getOneCard(true));
			Pcards.add(deck.getOneCard(true));
			
			p.setOneRoundCard(Pcards);
		}
		
		ArrayList<Card> Dcards = new ArrayList<Card>();
		Dcards.add(deck.getOneCard(false));
		
		Card c  = deck.getOneCard(true);
		System.out.print("Dealer's face up card is ");
		c.printCard();
		
		Dcards.add(c);
		dealer.setOneRoundCard(Dcards);
		
		
	}
	
	private void ask_each_player_about_hits(){
		for(Player p:players){
			
			System.out.print(p.get_name()+"'s Cards now:");
			for(Card c : p.getOneRoundCard()){
				c.printCard();
			}
			
			boolean hit=false;
			do{
				if(p.getTotalValue()<=21){
					hit=p.hit_me(this); //this
					if(hit){
						
						ArrayList<Card> currentCards=p.getOneRoundCard();
						currentCards.add(deck.getOneCard(true));
						p.setOneRoundCard(currentCards);
						System.out.print("Hit! ");
						System.out.print(p.get_name()+"'s Cards now:");
						for(Card c : p.getOneRoundCard()){
							c.printCard();
						}
					}else{
						System.out.println("Pass hit!");
						System.out.println(p.get_name()+"'s hit is over!");
						
						hit=false;
						
					}
					
				}else{
					System.out.println(p.get_name()+"'s hit is over!");
					
					hit=false;
				}
				
			}while(hit);
			
			hit=false;
		}
	}
	
	private void ask_dealer_about_hits(){
		
		
		boolean hit=false;
		do{
			if(dealer.getTotalValue()<=21){
				hit=dealer.hit_me(this); //this
				if(hit){
					
					ArrayList<Card> currentCards=dealer.getOneRoundCard();
					currentCards.add(deck.getOneCard(true));
					dealer.setOneRoundCard(currentCards);
					System.out.print("Hit! ");
					
				}else{
					System.out.println("Dealer's hit is over!");
					
					hit=false;
					
				}
				
			}else{
				System.out.println("Dealer's hit is over!");
				
				hit=false;
			}
			
			
			
		}while(hit);
		
		hit=false;
		
	}
	
	private void calculate_chips(){
		System.out.print("Dealer's card value is "+dealer.getTotalValue());
		System.out.print(",Cards:");
		dealer.printAllCard();
		
		for(int i=0;i<players.length;i++){
			System.out.print(players[i].get_name()+"'s Cards:");
			players[i].printAllCard();
			
			System.out.print(players[i].get_name()+" card value is "+
					players[i].getTotalValue());
			
			
			if(dealer.getTotalValue()<21 && players[i].getTotalValue()>21){
				
				players[i].increase_chips(-pos_betArray[i]);
				System.out.println(",Loss"+pos_betArray[i]+" Chips,the Chips now is "
						+players[i].get_current_chips());
				
			}else if(dealer.getTotalValue()>21 && players[i].getTotalValue()<21){
				
				players[i].increase_chips(pos_betArray[i]);
				System.out.println(",Get "+pos_betArray[i]+" Chips,the Chips now is "
						+players[i].get_current_chips());	
				
			}else if(dealer.getTotalValue()>21 && players[i].getTotalValue()>21){
				System.out.println(",chips have no change!The Chips now is:"+
						players[i].get_current_chips());
			}else if(dealer.getTotalValue()>players[i].getTotalValue()&&dealer.getTotalValue()<=21){
				
				players[i].increase_chips(-pos_betArray[i]);
				System.out.println(",Loss "+pos_betArray[i]+" Chips,the Chips now is "
						+players[i].get_current_chips());
				
			}else if(dealer.getTotalValue()<players[i].getTotalValue()&&players[i].getTotalValue()<=21){
				
				players[i].increase_chips(pos_betArray[i]);
				System.out.println(",Get"+pos_betArray[i]+" Chips,the Chips now is "
						+players[i].get_current_chips());	
				
			}else{
				System.out.println(",chips have no change!The Chips now is:"+
						players[i].get_current_chips());
			}
		}
		
		
	}
	
	public int[] get_players_bets(){
		return pos_betArray;
	}
	
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();

	}
	

}
