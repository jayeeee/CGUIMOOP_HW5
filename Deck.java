

import java.util.ArrayList;
import java.util.Random;


class Deck {
	 public ArrayList<Card> cards;
		public ArrayList<Card> usedCard;
		private ArrayList<Card> openCard;  
		//存放此副牌中所有打開的牌，洗牌時要重置
		public int nUsed;

		public Deck(int nDeck) {

			cards = new ArrayList<Card>();
			usedCard= new ArrayList<Card>();
			
			for(int deck =1;deck<=nDeck;deck++)
			{
				for(Card.Suit suit: Card.Suit.values())
					{
						for(int rank=1;rank<=13;rank++)
							{
								Card card= new Card(suit,rank);
								card.getSuit();		
								card.getRank();
								cards.add(card);
							}
					}
			}
			shuffle();
		}

		public void printDeck() {
			for (Card card : cards) {
				card.printCard();
			}


		}

		public ArrayList<Card> getAllCards() {
			return cards;
		}

		//洗牌時記得重置private ArrayList<Card> openCard;
		public void shuffle() {  
			
			openCard = new ArrayList<Card>();
			
			for (int k = 0; k < usedCard.size(); k++) {
				cards.add(usedCard.get(k));
			}
		
			for (int i = 0; i < cards.size(); i++) {

				Random rnd = new Random();
				int j = rnd.nextInt(cards.size());

				Card temp = cards.get(i);
				cards.set(i, cards.get(j));
				cards.set(j, temp);
			}
			
			usedCard.clear();
			nUsed = 0;
		}
	//拿到一張牌，修改原有method，加入isOpened參數，決定發出去的牌是開著還是蓋起來的
		public Card getOneCard(boolean isOpened) {
			
			if (cards.size() == 0) {
				shuffle();
			}
			Card card ;
			card= cards.get(0);

			nUsed += 1;
			usedCard.add(card);
			cards.remove(0);
			
			if(isOpened){
				openCard.add(card);
			}
			return card;
		}
		//回傳此副牌中所有打開過的牌，意即openCard
		public ArrayList<Card> getOpenedCard(){
			return openCard;
		}
}
