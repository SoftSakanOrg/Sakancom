package main;




public class bookingInfo {


    private int bookingInfoID;
    private String tenantName;
    private String ownerName;
    private int contactInfo;
    private String rentDate;

    private int tenantId;

    public bookingInfo(){

    }

    public bookingInfo(int bookingInfoID, String tenantName, String ownerName, int contactInfo, String rentDate,int tenantId) {
        this.bookingInfoID = bookingInfoID;
        this.tenantName = tenantName;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.rentDate = rentDate;
        this.tenantId=tenantId;
    }

    public int getBookingInfoID() {
        return bookingInfoID;
    }

    public void setBookingInfoID(int bookingInfoID) {
        this.bookingInfoID = bookingInfoID;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
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

    public String getRentDate() {
        return rentDate;
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
