package classes;

public class Furniture {


    private Integer furnitureID;
    private Integer tenantID;
    private Integer furniturePrice;
    private String furnitureDescription;
    private String furnitureStatus;

    public Furniture() {

    }

    public Furniture(Integer furnitureID, Integer tenantID, Integer furniturePrice, String furnitureDescription, String furnitureStatus) {
        this.furnitureID = furnitureID;
        this.tenantID = tenantID;
        this.furniturePrice = furniturePrice;
        this.furnitureDescription = furnitureDescription;
        this.furnitureStatus = furnitureStatus;
    }

    public Integer getFurnitureID() {
        return furnitureID;
    }

    public void setFurnitureID(Integer furnitureID) {
        this.furnitureID = furnitureID;
    }

    public Integer getTenantID() {
        return tenantID;
    }

    public void setTenantID(Integer tenantID) {
        this.tenantID = tenantID;
    }

    public Integer getFurniturePrice() {
        return furniturePrice;
    }

    public void setFurniturePrice(Integer furniturePrice) {
        this.furniturePrice = furniturePrice;
    }

    public String getFurnitureDescription() {
        return furnitureDescription;
    }

    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }

    public String getFurnitureStatus() {
        return furnitureStatus;
    }

    public void setFurnitureStatus(String furnitureStatus) {
        this.furnitureStatus = furnitureStatus;
    }
}
