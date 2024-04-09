class ImageUpdater{
    private String imagesPath = "C:\\Users\\abdul\\IdeaProjects\\Music player\\src\\assets\\songImages\\song";
    private String Imagepath;
    ImageUpdater(int indx){
        Imagepath = imagesPath+Integer.toString(indx+1)+".jpg";
    }
    public String getImagepath() {
        return Imagepath;
    }
}