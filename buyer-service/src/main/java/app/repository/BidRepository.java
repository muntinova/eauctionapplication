package app.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import app.models.Bid;

import java.util.List;
import java.util.Optional;


//No need implementation, just one interface, and you have CRUD, thanks Spring Data!
@Repository
public interface BidRepository extends MongoRepository<Bid, String> {

	List<Bid> findAllByProductId(String productId);

	Optional<Bid> findByProductIdAndEmail(String productId, String emailId);


}
