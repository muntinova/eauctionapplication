package app.controller;


import app.exception.ResourceNotFoundException;
import app.models.Bid;
import app.models.BidDetails;
import app.models.Product;
import app.service.AuctionSellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e-auction/api/v1")
public class AuctionSellerController {
	
	@Autowired
	private AuctionSellerService auctionService;

	public static final Logger log = LoggerFactory.getLogger(AuctionSellerController.class);

	@GetMapping(value = "/seller/show-bids/{productId}")
	public ResponseEntity<?> getAllBidDetails(@PathVariable(value="productId") String productId) throws ResourceNotFoundException {
		List<Bid> bidData = auctionService.getAllBids(productId);
		List<BidDetails> bidDetailsList = new ArrayList<BidDetails>();
		
		if(bidData != null && bidData.size() > 0) {
			log.info("Fetched bid details successfully");
			for(Bid bid : bidData) {
				BidDetails BidDetails = new BidDetails();
				Optional<Product> product = auctionService.getProduct(bid.getProductId());
				if (!product.isPresent()) {
	                throw new ResourceNotFoundException("Cant find any product under given ID");
	            }
				BidDetails.setBid(bid);
				BidDetails.setProduct(product.get());
				bidDetailsList.add(BidDetails);
			}
			return ResponseEntity.ok().body(bidDetailsList);
		}
		 else {
			log.error("Error in fetching bid details for the product:",productId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value= "/seller/add-product")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		try {
			if (!product.getCategory().equals("Painting") && !product.getCategory().equals("Sculptor") &&
					 !product.getCategory().equals("Ornament")) {
		            throw new RuntimeException("Product category should be Painting or Sculptor or Ornament ");
		    }
			Product addproduct = new Product(product.getId(),product.getShortDescription(),product.getDetailedDescription(),
					product.getProductName(),product.getCategory(), product.getStartingPrice(), product.getBidEndDate(),
					product.getSeller());
			Product  addedProduct= auctionService.saveProduct(addproduct);
			
			log.info("Product added: {}", addedProduct);
			return new ResponseEntity(addedProduct, HttpStatus.CREATED);
		
			
		} catch (Exception e) {
			log.error("Product not added:", e);
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/seller/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value="id") String productId) {
		try {
			Optional<Product> product = auctionService.getProduct(productId);
			if (!product.isPresent()) {
                throw new ResourceNotFoundException("Cant find any product under given ID");
            }
			System.out.println("Current date:"+new Date());
			if(new Date().after(product.get().getBidEndDate())) {
				throw new ResourceNotFoundException("Product cannot be deleted after bid end date");
			}
			List bidData = auctionService.getAllBids(productId);
			
			if(bidData != null && bidData.size() > 0) {
				throw new RuntimeException("Product cannot be deleted as it has bid/bids placed on the product");
			}
			auctionService.deleteProduct(productId);
			log.info("Deleted Product successfully");
			return ResponseEntity.ok().body("Deleted Product with id:"+productId+" successfully");
			
		} catch(Exception e) {
			log.error("Error in deleting product with id:",productId);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
