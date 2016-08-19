package androidcourse.simplelistview;

/**
 * Created by Juan on 19/08/2016.
 */
public class Person {
    private String name;
    private String imageName;

    public Person(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }
}
