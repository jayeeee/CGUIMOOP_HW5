
 class Card {
	 public Suit suit; // Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3,
		// Spades=4	
	 public int rank; // 1~13

	public enum Suit {Club, Diamond, Heart, Spade};

	public Card(Suit s, int value) {
	suit = s;
	rank = value;
	}

	public void printCard() {		
		System.out.println(suit.toString() + "," + rank);
	}

	public Suit getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}

}
