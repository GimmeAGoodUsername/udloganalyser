package de.universeDawn.fightscriptanalyser.repo;

import de.universeDawn.fightscriptanalyser.user.SrOrder;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<SrOrder, Long> {

    @Query(value = "SELECT o FROM SrOrder o WHERE o.deliveryBoy IS NULL")
     List<SrOrder> getAllOpenOrders();

    @Query(value = "SELECT o FROM SrOrder o WHERE o.deliveryBoy = ?1")
    List<SrOrder> getOpenOrdersFromUser(SrUser srUser);

}
