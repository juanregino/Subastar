package controller;

import entity.Offer;
import entity.Product;
import model.OfferModel;
import services.OfferServices;

import java.util.ArrayList;
import java.util.List;

public class OfferController {
    OfferServices objOfferServices = new OfferServices();
    OfferModel objOfferModel = new OfferModel();
    List<Offer> listOffers = new ArrayList<>();

    public void listAllActiveProducts() {
        List<Product> listTemp = objOfferServices.listActiveProdcuts();
        for (Product productTemp : listTemp) {
            System.out.println(productTemp.toString() + "\n");
        }
    }

    public void listProductId(String id){
       Product objProduct =  objOfferServices.listProductById(id);

        System.out.println(objProduct.toString());
    }

    public String calcOffers(String id){

        return (objOfferServices.calcOfferActuallity(id));

    }

    public void addOffer(Offer offer){
        objOfferModel.addOffer(offer);
    }


}
