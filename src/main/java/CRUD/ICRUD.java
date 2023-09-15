package CRUD;

public interface ICRUD {
    public Object add();
    public void updateItem();
    public void deleteItem();
    public void selectOne(int id);
    public void saveFile();
}
