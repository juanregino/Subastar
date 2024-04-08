package repository;

import entity.Offer;
import entity.Product;

import java.util.List;

public interface OfferCRUDRepository {
    void addOffer(Offer offer);

    Offer findByID(String id);

    List<Offer> findAll();

    void updateOffer(Offer offer);

    void deleteOffer(String id);
}
