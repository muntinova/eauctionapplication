package app.service;



import app.repository.ProductRepository;
import app.controller.AuctionBuyerController;
import app.models.Bid;
import app.models.Product;
import app.repository.BidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AuctionBuyerService {

	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    private BidRepository bidRepository;
	
	public static final Logger log = LoggerFactory.getLogger(AuctionBuyerController.class);

	public Bid saveBid(Bid bid) {
		return bidRepository.save(bid);
	}
	
	public Bid updateBid(String productId, String emailId, double bidAmount) {
		Optional<Bid> bid=bidRepository.findByProductIdAndEmail(productId, emailId);
		Bid updatedBid = bid.get();
		updatedBid.setBidAmount(bidAmount);
		return bidRepository.save(updatedBid);
	}
	
	public List<Bid> getAllBids(String productId){
		return bidRepository.findAllByProductId(productId);
	}
	public List<Bid> getAll(){
		return bidRepository.findAll();
	}
	public Optional<Product> getProduct(String productId) {
		return productRepository.findById(productId);
	}
	
	public Optional<Bid> getBidByProduct(String productId, String email){
		return bidRepository.findByProductIdAndEmail(productId, email);
	}
	
	

}
