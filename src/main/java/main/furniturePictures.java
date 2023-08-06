package main;
@SuppressWarnings("java:S1118")
public class furniturePictures {


    private Integer furniturePictureId;
    private Integer furnitureId;
    private String furniturePicture;

    public furniturePictures() {
    }

    public furniturePictures(Integer furniturePictureId, Integer furnitureId, String furniturePicture) {
        this.furniturePictureId = furniturePictureId;
        this.furnitureId = furnitureId;
        this.furniturePicture = furniturePicture;
    }

    public Integer getFurniturePictureId() {
        return furniturePictureId;
    }

    public void setFurniturePictureId(Integer furniturePictureId) {
        this.furniturePictureId = furniturePictureId;
    }

    public Integer getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Integer furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getFurniturePicture() {
        return furniturePicture;
    }

    public void setFurniturePicture(String furniturePicture) {
        this.furniturePicture = furniturePicture;
    }
}
