package in.askdial.mrr;

/**
 * Created by cgani on 08-Oct-16.
 */

public class Content {
    String Title, Description, departmenthead, departmentcontent, eventcontent;
    int img, departmentimgs, galleryimgs, eventimgs;

    public Content() {
    }

    public Content(String title, String description) {
        Title = title;
        Description = description;
    }

    public Content(String departmenthead, /*String departmentcontent,*/ int departmentimgs) {
        this.departmenthead = departmenthead;
        /*this.departmentcontent = departmentcontent;*/
        this.departmentimgs = departmentimgs;
    }

    public Content(int eventimgs, String eventcontent) {
        this.eventimgs = eventimgs;
        this.eventcontent = eventcontent;
    }

    public Content(int galleryimgs) {
        this.galleryimgs = galleryimgs;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return this.img;
    }

    public String getDepartmenthead() {
        return departmenthead;
    }

    public void setDepartmenthead(String departmenthead) {
        this.departmenthead = departmenthead;
    }

    public String getDepartmentcontent() {
        return departmentcontent;
    }

    public void setDepartmentcontent(String departmentcontent) {
        this.departmentcontent = departmentcontent;
    }

    public int getDepartmentimgs() {
        return departmentimgs;
    }

    public void setDepartmentimgs(int departmentimgs) {
        this.departmentimgs = departmentimgs;
    }

    public int getGalleryimgs() {
        return galleryimgs;
    }

    public void setGalleryimgs(int galleryimgs) {
        this.galleryimgs = galleryimgs;
    }

    public String getEventcontent() {
        return eventcontent;
    }

    public int getEventimgs() {
        return eventimgs;
    }
}
