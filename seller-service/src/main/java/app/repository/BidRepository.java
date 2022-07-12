package app.repository;


import app.models.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface BidRepository extends MongoRepository<Bid, String>  {

	List<Bid> findAllByProductId(String productId);

	Optional<Bid> findByProductIdAndEmail(String productId, String emailId);


}
