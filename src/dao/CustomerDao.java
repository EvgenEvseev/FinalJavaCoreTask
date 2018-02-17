package dao;

import model.Customer;
import model.Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class CustomerDao extends DaoDraft {
    private String path = "./src/customer.txt";

    public void safe(Customer customer) {
        try (FileWriter wr = new FileWriter(path, true)) {
            String customerStr = customer.getId() + "," + customer.getName() + "," + customer.getLastName() +
                    "," + customer.getAddress() + "," + "/" + customer.getIds();
            wr.write(customerStr);
            wr.write("\r\n");
            System.out.println("Клиент успешно добавлен :");
            System.out.println(customer);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public Customer getById(long id) {
        Customer customer = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            ProjectDao pd = new ProjectDao();
            boolean rly=true;
            while ((line = br.readLine()) != null) {
                if (Objects.equals(line.split(",")[0], Long.toString(id))) {
                    rly = false;
                    long ID = Long.valueOf(line.split(",")[0]);
                    String firstname = line.split(",")[1];
                    String secondname = line.split(",")[2];
                    String address = line.split(",")[3];
                    String allIdOfProjects = line.split("/")[1];
                    HashSet<Project> projects= new HashSet<>();
                    for(int i =0;i<allIdOfProjects.split(",").length;i++){
                        Project project = pd.getById(Long.valueOf(allIdOfProjects.split(",")[i]));
                        projects.add(project);
                    }
                    customer = new Customer(ID,firstname,secondname,address,projects);
                }
            }
            if(rly)System.out.println("Разработчик с ID "+id+" не найден. ");
        } catch (IOException e) {
            System.out.println("Ошибка"+e);
        }
        return customer;
    }

    public ArrayList<Customer> getAll() {
        ArrayList<Customer>AL = new ArrayList<>();
        ArrayList<Long>ids = getIds(path);
        for(long i:ids){
            Customer customer = getById(i);
            String Customers = "\n"+customer.getId() + "," + customer.getName() + "," + customer.getLastName() +
                    "," + customer.getAddress()+".";
            System.out.println(Customers);
            customer.getProjects();
            AL.add(customer);
        }
        return AL;
    }

    public void remove(long id){
        remove(id,path);
    }
}
