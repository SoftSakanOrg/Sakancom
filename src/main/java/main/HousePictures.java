package main;

public class HousePictures {

  private Integer housePicId;
  private Integer houseId;
  private String housePicture;

    public HousePictures() {
    }

    public HousePictures(Integer housePicId, Integer houseId, String housePicture) {
        this.housePicId = housePicId;
        this.houseId = houseId;
        this.housePicture = housePicture;
    }

    public Integer getHousePicId() {
        return housePicId;
    }

    public void setHousePicId(Integer housePicId) {
        this.housePicId = housePicId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getHousePicture() {
        return housePicture;
    }

    public void setHousePicture(String housePicture) {
        this.housePicture = housePicture;
    }
}
