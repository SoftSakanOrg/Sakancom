package main;

public class HouseParticipants {

    private Integer partId;
    private Integer houseId;
    private String partName;
    private Integer partAge;
    private String partMajor;
    private String partGender;

    public HouseParticipants() {
    }

    public HouseParticipants(Integer partId, Integer houseId, String partName, Integer partAge, String partMajor, String partGender) {
        this.partId = partId;
        this.houseId = houseId;
        this.partName = partName;
        this.partAge = partAge;
        this.partMajor = partMajor;
        this.partGender = partGender;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getPartAge() {
        return partAge;
    }

    public void setPartAge(Integer partAge) {
        this.partAge = partAge;
    }

    public String getPartMajor() {
        return partMajor;
    }

    public void setPartMajor(String partMajor) {
        this.partMajor = partMajor;
    }

    public String getPartGender() {
        return partGender;
    }

    public void setPartGender(String partGender) {
        this.partGender = partGender;
    }
}
