package book.bean;

public class Book {
	public int id;
	public String bookName;
	public float price;
	
	public Book() {}
	
	public Book(int id, String bookName, float price) {
		this.id = id;
		this.bookName = bookName;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
