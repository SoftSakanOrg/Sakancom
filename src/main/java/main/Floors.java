package main;

public class Floors {

    private Integer houseId;
    private String houseName;
    private  String avalibilty;
    private Integer housePrice;
    private String houseLocation;
    private String houseServices;
    private Integer houseParticipants;
    private Integer houseMaxParticipants;

    private Integer bedrooms;
    private Integer bathrooms;
    private Integer contBalcony;

    private String status;




    public Floors() {

    }


    public Floors(FloorParameters floorParameters) {
        this.houseId = floorParameters.houseId();
        this.houseName = floorParameters.houseName();
        this.avalibilty = floorParameters.avalibilty();
        this.housePrice = floorParameters.housePrice();
        this.houseLocation = floorParameters.houseLocation();
        this.houseServices = floorParameters.houseServices();
        this.houseParticipants = floorParameters.houseParticipants();
        this.houseMaxParticipants = floorParameters.houseMaxParticipants();
        this.bedrooms = floorParameters.bedrooms();
        this.bathrooms = floorParameters.bathrooms();
        this.contBalcony = floorParameters.contBalcony();
        this.status= floorParameters.status();
    }


    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getAvalibilty() {
        return avalibilty;
    }

    public void setAvalibilty(String avalibilty) {
        this.avalibilty = avalibilty;
    }

    public Integer getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(Integer housePrice) {
        this.housePrice = housePrice;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }

    public String getHouseServices() {
        return houseServices;
    }

    public void setHouseServices(String houseServices) {
        this.houseServices = houseServices;
    }

    public Integer getHouseParticipants() {
        return houseParticipants;
    }

    public void setHouseParticipants(Integer houseParticipants) {
        this.houseParticipants = houseParticipants;
    }

    public Integer getHouseMaxParticipants() {
        return houseMaxParticipants;
    }

    public void setHouseMaxParticipants(Integer houseMaxParticipants) {
        this.houseMaxParticipants = houseMaxParticipants;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getContBalcony() {
        return contBalcony;
    }

    public void setContBalcony(Integer contBalcony) {
        this.contBalcony = contBalcony;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
