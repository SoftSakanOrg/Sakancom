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
    private String houseOwnerName;


    private Integer houseOwnerContact;



    public Floors() {
    }

    public Floors(Integer houseId, String houseName, String avalibilty, Integer housePrice, String houseLocation, String houseServices, Integer houseParticipants, Integer houseMaxParticipants, String houseOwnerName, Integer houseOwnerContact) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.avalibilty = avalibilty;
        this.housePrice = housePrice;
        this.houseLocation = houseLocation;
        this.houseServices = houseServices;
        this.houseParticipants = houseParticipants;
        this.houseMaxParticipants = houseMaxParticipants;
        this.houseOwnerName = houseOwnerName;
        this.houseOwnerContact = houseOwnerContact;
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

    public String getHouseOwnerName() {
        return houseOwnerName;
    }

    public void setHouseOwnerName(String houseOwnerName) {
        this.houseOwnerName = houseOwnerName;
    }

    public Integer getHouseOwnerContact() {
        return houseOwnerContact;
    }

    public void setHouseOwnerContact(Integer houseOwnerContact) {
        this.houseOwnerContact = houseOwnerContact;
    }

    //    public Houses(houseId,houseName,) {
//    }
}
