import java.util.Objects;
import java.util.TreeMap;
import java.util.Map;

/**
 * Write a class TelephoneNumber having 2 fields: countryCode and localNumber which implements the Comparable interface.
 * Create an abstract class TelephoneEntry and then inherit from this class classes Person and Company.
 * Class TelephoneEntry has an abstract method "description", which describes the entry.
 * Perhaps it also has also other abstract or non-abstract methods, as needed.
 * Class Person has to contain information about the name, last name, and address (including TelephoneNumber).
 * Class Company has to have the name and address (including TelephoneNumber).
 * Create additional classes if you find them useful according to OOP principles and (if created) use them appropriately.
 * Compose a few objects of class Person and a few objects of class Company and place them in a container TreeMap, using as a key TelephoneNumber.
 * Then, list a created directory using Iterator.
 */
public class Contacts {
    public static void main(String[] args) {
        try {
            Contacts instance = new Contacts();
            TreeMap<TelephoneNumber, TelephoneEntry> contacts = instance.populateTreeMap();
            for (Map.Entry<TelephoneNumber, TelephoneEntry> entry : contacts.entrySet()) {
                System.out.println(entry.getValue().decription() + "\n");
            }
        }
        catch(Exception e) {
            System.out.println("Oh no ! Something went wrong :(\n Here is some clue :\n" + e);
          //  Block of code to handle errors
        }
    }

    private class TelephoneNumber implements Comparable<TelephoneNumber>{
        private final Integer countryCode;
        private final Integer localNumber;

        public Integer getLocalNumber() {
            return localNumber;
        }

        public Integer getCountryCode() {
            return countryCode;
        }

        public TelephoneNumber(Integer localNumber){
            this(38, localNumber);
        }
        public TelephoneNumber(Integer countryCode, Integer localNumber){
             this.countryCode = countryCode;
             this.localNumber = localNumber;
        }

        /**
         * @param o the object to be compared.
         * @return 0 if the two objects have the same countryCode. -1 if the first object has a smaller country code. 1 otherwise.
         */
        @Override
        public int compareTo(TelephoneNumber o) {
            if (this.countryCode.compareTo(o.countryCode) == -1)
                return -1;
            if (this.countryCode.compareTo(o.countryCode) == 1)
                return 1;
            if (this.localNumber.compareTo(o.localNumber) == -1)
                return -1;
            if (this.localNumber.compareTo(o.localNumber) == 1)
                return 1;
            return 0;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TelephoneNumber that)) return false;
            return countryCode.equals(that.countryCode) && localNumber.equals(that.localNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(countryCode, localNumber);
        }

        @Override
        public String toString() {
            return "TelephoneNumber : (" +
                    "countryCode=" + countryCode +
                    ", localNumber=" + localNumber + ")";
        }
    }

    private class Address {
        private final String city;
        private final String street;
        private final Integer number;

        public Integer getNumber() {
            return number;
        }

        public String getCity() {
            return city;
        }

        public String getStreet() {
            return street;
        }

        protected Address(String city, String street, Integer number){
            this.city = city;
            this.street = street;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Address : (" +
                    "city='" + city + '\'' +
                    ", street='" + street + '\'' +
                    ", number=" + number +
                    ')';
        }
    }

    abstract private class TelephoneEntry{
        protected String name;
        protected TelephoneNumber telephoneNumber;
        protected Address address;

        public TelephoneNumber getTelephoneNumber(){
            return telephoneNumber;
        }

        public Address getAddress(){ return address; }

        public String getName(){ return name; }
        public TelephoneEntry(String name, TelephoneNumber telephoneNumber, Address address) {
            this.name = name;
            this.telephoneNumber = telephoneNumber;
            this.address = address;
        }

        abstract public String decription();

        @Override
        public String toString() {
            return "TelephoneEntry{" +
                    "name='" + name + '\'' +
                    ", telephoneNumber=" + telephoneNumber +
                    ", address=" + address +
                    '}';
        }
    }

    private class Person extends TelephoneEntry{
        String lastName;

        public Person(String name, String lastName, TelephoneNumber telephoneNumber, Address address) {
            super(name, telephoneNumber, address);
            this.lastName = lastName;
        }

        /**
         * @return
         */
        @Override
        public String decription() {
            return "This is a Person : " +
                    "lastName='" + lastName + '\'' +
                    ", name='" + name + "\', " +
                    address + ", " +
                    telephoneNumber +
                    ')';
        }
    }

    private class Company extends TelephoneEntry{

        public Company(String name, TelephoneNumber telephoneNumber, Address address) {
            super(name, telephoneNumber, address);
        }

        /**
         * @return a description. Equivalent to "toString"
         */
        @Override
        public String decription() {
            return "This is a Company :" +
                    " name='" + name + ", " +
                    address + ", " +
                    telephoneNumber;
        }
    }

    private TreeMap<TelephoneNumber, TelephoneEntry> populateTreeMap(){
        TreeMap<TelephoneNumber, TelephoneEntry> map = new TreeMap<TelephoneNumber, TelephoneEntry>();

        TelephoneEntry a = contactFactory("Jean", "Pierre", 33, 610101010, "Paris", "Avenue des Champs-Elysées", 59);
        TelephoneEntry b = contactFactory("Marie", "Curry", 33, 320555555, "Paris", "Boulevard De Gaulle", 1945);
        TelephoneEntry c = contactFactory("Tefal",  33, 320191817, "Evry", "Rue Eugène", 1);
        TelephoneEntry d = contactFactory("Piotr", "Krafohfsk", 123456789, "Warszawa", "aleja Solidarności", 155);
        TelephoneEntry e = contactFactory("Zabka", 987654321, "Łódź", "aleja Rembielińskiego", 155);
        TelephoneEntry f = contactFactory("Charles", "De Gaulle", 33, 619764837, "Paris", "Avenue Montaigne", 9);
        TelephoneEntry g = contactFactory("Poczta Polska", 426844324, "Łódź", "Ciasna", 19);
        TelephoneEntry h = contactFactory("Lewis", "Hamilton", 44,467219754, "London", "Queen Elizabeth II avenue", 7);
        TelephoneEntry i = contactFactory("Blini", 30,102030405, "Athena", "Zeus avenue", 78690);
        map.put(a.getTelephoneNumber(), a);
        map.put(b.getTelephoneNumber(), b);
        map.put(c.getTelephoneNumber(), c);
        map.put(d.getTelephoneNumber(), d);
        map.put(e.getTelephoneNumber(), e);
        map.put(f.getTelephoneNumber(), f);
        map.put(g.getTelephoneNumber(), g);
        map.put(h.getTelephoneNumber(), h);
        map.put(i.getTelephoneNumber(), i);

        return map;
    }

    /**
     * @param name
     * @param lastname
     * @param countryCode
     * @param localNumber
     * @param city
     * @param street
     * @param number
     * @return
     */
    protected TelephoneEntry contactFactory(String name, String lastname, Integer countryCode, Integer localNumber, String city, String street, Integer number) {
        TelephoneEntry result;
        // We create address object
        Address address = new Address(city, street, number);

        // We create telephoneNumber object, with default or none default country code
        TelephoneNumber telephoneNumber = (countryCode == null) ? new TelephoneNumber(localNumber) : new TelephoneNumber(countryCode, localNumber);
        if (lastname == null) {
            result = new Company(name, telephoneNumber, address);
        } else {
            result = new Person(name, lastname, telephoneNumber, address);
        }
        return result;
    }

    protected TelephoneEntry contactFactory(String name, Integer localNumber, String city, String street, Integer number) {
        return contactFactory(name, null, null, localNumber, city, street, number);
    }

    protected TelephoneEntry contactFactory(String name, Integer countryCode, Integer localNumber, String city, String street, Integer number) {
        return contactFactory(name, null, countryCode, localNumber, city, street, number);
    }

    protected TelephoneEntry contactFactory(String name, String lastname, Integer localNumber, String city, String street, Integer number) {
        return contactFactory(name, lastname, null, localNumber, city, street, number);
    }
}
