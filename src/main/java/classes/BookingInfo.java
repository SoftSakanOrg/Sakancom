package classes;




public class BookingInfo {


    private int bookingInfoID;
    private String tenantName;
    private String ownerName;
    private int contactInfo;
    private String rentDate;

    private int tenantId;

    public BookingInfo(){

    }



    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }



    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }
}
