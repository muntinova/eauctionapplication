package app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "products")
@TypeAlias("Product")
public class Product {
	
	@Id
	private String id;
	@NotNull
    @Size(min=5, max=30)
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private String category;
    private long startingPrice;
    @Future
    private Date bidEndDate;
    private Seller seller;
    
    public Product(String id, String productName, String shortDescription, String detailedDescription, String category,
                   long startingPrice, Date bidEndDate, Seller seller) {

    	this.id = id;
    	this.productName = productName;
    	this.shortDescription = shortDescription;
    	this.detailedDescription = detailedDescription;
    	this.category = category;
    	this.startingPrice = startingPrice;
    	this.bidEndDate = bidEndDate;
    	this.seller = seller;
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(long startingPrice) {
		this.startingPrice = startingPrice;
	}

	public Date getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

    

}
