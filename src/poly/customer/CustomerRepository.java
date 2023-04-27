package poly.customer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    private List<AbstractCustomer> customers = new ArrayList<>();


    public void readCustomersFromFileAndAddToCustomers() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(FILE_PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // for commit
        for (String line : lines) {
            String[] parts = line.split(";");

            if (parts[0].equals("REGULAR")) {
                AbstractCustomer newCustomer = new RegularCustomer(parts[1], parts[2], Integer.parseInt(parts[3]), LocalDate.parse(parts[4]));
                customers.add(newCustomer);
            } else if (parts[0].equals("GOLD")) {
                AbstractCustomer newCustomer = new GoldCustomer(parts[1], parts[2], Integer.parseInt(parts[3]));
                customers.add(newCustomer);
            }
        }
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {

        readCustomersFromFileAndAddToCustomers();

        int indexOfCustomer = 0;
        for (AbstractCustomer customer : customers) {
            if (customer.id.equals(id)) {
                return Optional.ofNullable(customers.get(indexOfCustomer));
            }
            indexOfCustomer++;
        }
        return Optional.empty();
    }

    public void remove(String id) {
        int removeIndex = 0;
        readCustomersFromFileAndAddToCustomers();
        for (AbstractCustomer customer : customers) {
            if (customer.id.equals(id)) {
                customers.remove(removeIndex);
                break;
            }
            removeIndex++;
        }
        clearFile();
        addCustomersFromCustomersList();
    }

    public void turnObjToStringRep(AbstractCustomer customer) {
        if (customer.getClass().getSimpleName().equals("RegularCustomer")) {
            String customerValuesAsString = "REGULAR" + ";" + customer.asString();
            writeCustomerIntoFile(customerValuesAsString);
        }
        else if (customer.getClass().getSimpleName().equals("GoldCustomer")) {
            String customerValuesAsString = "GOLD" + ";" + customer.asString();
            writeCustomerIntoFile(customerValuesAsString);
        }
    }


    public void addCustomersFromCustomersList() {
        for (AbstractCustomer customer : customers) {
            turnObjToStringRep(customer);
        }
    }

    public void save(AbstractCustomer customer) {
        boolean customerInListChecker = false;
        customers.clear();
        readCustomersFromFileAndAddToCustomers();
        System.out.println(customers);
        for (AbstractCustomer abstractCustomer : customers) {
            if (abstractCustomer.id.equals(customer.id)) {
                customerInListChecker = true;
                abstractCustomer.bonusPoints = customer.bonusPoints;
            }
        }
        if (!customerInListChecker) {
            customers.add(customer);
        }
        clearFile();
        addCustomersFromCustomersList();
    }

        public int getCustomerCount() {
            List<String> lines;
            try {
                lines = Files.readAllLines(Path.of(FILE_PATH), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return lines.size();
        }

        public void writeCustomerIntoFile(String customer) {

            try {
                FileWriter csvWriter = new FileWriter(FILE_PATH, true);

                csvWriter.write(customer + "\n");
                csvWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void clearFile() {
            try {
                FileWriter csvWriter = new FileWriter(FILE_PATH, false);

                csvWriter.write("");
                csvWriter.flush();
                csvWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
