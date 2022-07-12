package app.service;

import app.controller.AuctionSellerController;
import app.models.Bid;
import app.models.Product;
import app.repository.BidRepository;
import app.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionSellerService {

	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    private BidRepository bidRepository;
	
	public static final Logger log = LoggerFactory.getLogger(AuctionSellerController.class);


	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProduct(String productId){
		productRepository.deleteById(productId);
	}
	
	public List<Bid> getAllBids(String productId){
		return bidRepository.findAllByProductId(productId);
	}
	
	public Optional<Product> getProduct(String productId) {
		return productRepository.findById(productId);
	}
	
	

}
