package classes;


public class AdvertismentRequests {
    private int reqId;
    private String buildingName;
    private String ownerName;
    private int contactNumber;

    private int price;

    private int floorId;
    public AdvertismentRequests(){

    }


    public AdvertismentRequests(int reqId, String buildingName, String ownerName, int contactNumber, int price, int floorId) {
        this.reqId = reqId;
        this.buildingName = buildingName;
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
        this.price = price;
        this.floorId= floorId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}
