package classes;

public class Buildings {

    private Integer buildingId;
    private Integer ownerId;
    private String buildingName;
    private String location;
    private Integer floorsNum;
    private String ownerName;
    private Integer contactNum;


    public Buildings() {
    }

    public Buildings(Integer buildingId, Integer ownerId, String buildingName, String location, Integer floorsNum, String ownerName, Integer contactNum) {
        this.buildingId = buildingId;
        this.ownerId = ownerId;
        this.buildingName = buildingName;
        this.location = location;
        this.floorsNum = floorsNum;
        this.ownerName = ownerName;
        this.contactNum = contactNum;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getFloorsNum() {
        return floorsNum;
    }

    public void setFloorsNum(Integer floorsNum) {
        this.floorsNum = floorsNum;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getContactNum() {
        return contactNum;
    }

    public void setContactNum(Integer contactNum) {
        this.contactNum = contactNum;
    }
}
