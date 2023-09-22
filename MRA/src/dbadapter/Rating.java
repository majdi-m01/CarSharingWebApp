package dbadapter;

public class Rating {

	int ruid;
	String title;
	int rating;
	String comment;

	public Rating(int ruid, String title, int rating, String comment) {
		super();
		this.ruid = ruid;
		this.title = title;
		this.comment = comment;
	}

	public int getRuid() {
		return ruid;
	}

	public void setRuid(int ruid) {
		this.ruid = ruid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean available(int a, String b) {
		if (a == ruid & b == title) {
			return false;
		}
		
		return true;
	}

}
